package net.unir.mslibraryoperator.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequest {

    private Long rentalId;
    private Long bookId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String endDate;
    private String deliveryDate;
    private BookStatus status;
}
