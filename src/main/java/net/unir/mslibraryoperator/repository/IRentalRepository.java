package net.unir.mslibraryoperator.repository;

import net.unir.mslibraryoperator.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalRepository extends JpaRepository<Rental, Long> {
}
