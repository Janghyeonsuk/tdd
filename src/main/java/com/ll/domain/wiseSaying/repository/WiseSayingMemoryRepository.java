package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingMemoryRepository{
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingMemoryRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 1;
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        if (!wiseSaying.isNew()) {
            return wiseSaying;
        }

        wiseSaying.setId(lastId++);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public boolean deleteById(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }
}
