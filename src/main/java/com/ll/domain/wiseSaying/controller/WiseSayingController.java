package com.ll.domain.wiseSaying.controller;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc;
    private final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        this.wiseSayings = new ArrayList<>();
        this.lastId = 1;
    }

    public void actionAdd(Scanner sc) {
        System.out.println("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = new WiseSaying(lastId++, content, author);
        wiseSayings.add(wiseSaying);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호  /  작가  /  명언");
        System.out.println("--------------------");

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println(wiseSaying.getId() + "  /  " + wiseSaying.getAuthor() + "  /  " + wiseSaying.getContent());
        }

    }
}
