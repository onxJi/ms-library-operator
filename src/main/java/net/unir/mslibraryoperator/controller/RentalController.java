package net.unir.mslibraryoperator.controller;

import jakarta.validation.Valid;
import net.unir.mslibraryoperator.domain.RentalRequest;
import net.unir.mslibraryoperator.domain.RentalResponse;
import net.unir.mslibraryoperator.service.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalResponse>> findAll() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<RentalResponse> findById(@PathVariable Long rentalId) {
        return ResponseEntity.ok(rentalService.findById(rentalId));
    }

    @PostMapping
    public ResponseEntity<RentalResponse> save(@Valid @RequestBody RentalRequest rentalRequest) {
        RentalResponse rentalResponse = rentalService.save(rentalRequest);
        if (Objects.nonNull(rentalResponse))
            return new ResponseEntity<>(rentalResponse, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<RentalResponse> update(@PathVariable Long rentalId, @Valid @RequestBody RentalRequest rentalRequest) {
        RentalResponse rentalResponse = rentalService.update(rentalId, rentalRequest);
        if (Objects.nonNull(rentalResponse))
            return ResponseEntity.ok(rentalResponse);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long rentalId) {
        boolean deleted = rentalService.deleteById(rentalId);
        if (deleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
