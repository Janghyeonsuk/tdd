package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.dto.Pageable;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);

    List<WiseSaying> findAll();

    boolean deleteById(int id);

    Optional<WiseSaying> findById(int id);

    void archive(String archivePath);

    List<WiseSaying> findByKeyword(String keywordType, String keyword);

    void makeSampleData(int items);
    
    int count();

    int count(String keywordType, String keyword);

    Pageable<WiseSaying> pageable(String keywordType, String keyword, int itemsPerPage, int page);

    Pageable<WiseSaying> pageableAll(int itemsPerPage, int page);
}
