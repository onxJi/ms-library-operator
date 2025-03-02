package net.unir.mslibraryoperator.service;

import net.unir.mslibraryoperator.domain.RentalRequest;
import net.unir.mslibraryoperator.domain.RentalResponse;

import java.util.List;

public interface IRentalService {

    List<RentalResponse> findAll();

    RentalResponse findById(Long rentalId);

    RentalResponse save(RentalRequest rentalRequest);

    RentalResponse update(Long rentalId, RentalRequest rentalRequest);

    boolean deleteById(Long rentalId);
}
