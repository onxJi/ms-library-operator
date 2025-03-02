package net.unir.mslibraryoperator.repository.impl;

import net.unir.mslibraryoperator.domain.BookResponse;
import net.unir.mslibraryoperator.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BookRepositoryImpl implements IBookRepository {

    @Value("${url.searcher}")
    private String urlSearcher;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BookResponse findById(Long bookId) {
        return restTemplate.getForObject(urlSearcher + "/books/" + bookId, BookResponse.class);
    }

    @Override
    public void updatePatch(Long bookId, BookResponse bookResponse) {
        restTemplate.put(urlSearcher + "/books/" + bookId + "/patch", bookResponse);
    }
}
