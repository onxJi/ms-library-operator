package net.unir.mslibraryoperator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.unir.mslibraryoperator.repository.IBookRepository;
import net.unir.mslibraryoperator.repository.IRentalRepository;
import net.unir.mslibraryoperator.domain.*;
import net.unir.mslibraryoperator.service.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<RentalResponse> findAll() {
        return rentalRepository.findAll().stream()
                .map(rental -> objectMapper.convertValue(rental, RentalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public RentalResponse findById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .map(rental -> objectMapper.convertValue(rental, RentalResponse.class))
                .orElse(null);
    }

    @Override
    public RentalResponse save(RentalRequest rentalRequest) {
        BookResponse bookResponse = bookRepository.findById(rentalRequest.getBookId());
        if (Objects.nonNull(bookResponse) && bookResponse.getStatus().equals(BookStatus.OPEN)) {
            bookResponse.setStatus(BookStatus.RENTED);
            bookRepository.updatePatch(bookResponse.getBookId(), bookResponse);

            Rental rental = objectMapper.convertValue(rentalRequest, Rental.class);
            Rental savedRental = rentalRepository.save(rental);
            return objectMapper.convertValue(savedRental, RentalResponse.class);
        }
        return null;
    }

    @Override
    public RentalResponse update(Long rentalId, RentalRequest rentalRequest) {
        BookResponse bookResponse = bookRepository.findById(rentalRequest.getBookId());
        if (Objects.nonNull(bookResponse) && rentalRepository.existsById(rentalId)) {
            bookResponse.setStatus(rentalRequest.getStatus());
            if (rentalRequest.getStatus().equals(BookStatus.DELIVERED))
                bookResponse.setStatus(BookStatus.OPEN);
            bookRepository.updatePatch(bookResponse.getBookId(), bookResponse);

            Rental rental = objectMapper.convertValue(rentalRequest, Rental.class);
            rental.setRentalId(rentalId);
            Rental savedRental = rentalRepository.save(rental);
            return objectMapper.convertValue(savedRental, RentalResponse.class);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long rentalId) {
        if (rentalRepository.existsById(rentalId)) {
            rentalRepository.deleteById(rentalId);
            return true;
        }
        return false;
    }
}
