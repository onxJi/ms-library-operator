package net.unir.mslibraryoperator.repository;

import net.unir.mslibraryoperator.domain.BookResponse;

public interface IBookRepository {

    BookResponse findById(Long bookId);

    void updatePatch(Long bookId, BookResponse bookResponse);
}
