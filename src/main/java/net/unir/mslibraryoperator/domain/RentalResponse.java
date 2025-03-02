package net.unir.mslibraryoperator.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponse {

    private Long rentalId;
    private Long bookId;
    private String title;
    private String startDate;
    private String endDate;
    private String deliveryDate;
    private BookStatus status;
}
