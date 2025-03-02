package net.unir.mslibraryoperator.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {

    private Long bookId;
    private BookStatus status;
}
