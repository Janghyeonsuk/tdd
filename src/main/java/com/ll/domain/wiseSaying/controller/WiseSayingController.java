package com.ll.domain.wiseSaying.controller;

import com.ll.global.app.Command;
import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        this.wiseSayingService = new WiseSayingService();
    }

    //샘플데이터
    public void initSampleData() {
        wiseSayingService.add("나의 죽음을 적들에게 알리지 말라.", "이순신 장군");
        wiseSayingService.add("삶이 있는 한 희망은 있다.", "키케로");
    }

    public void actionAdd() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.add(content, author);

        System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        List<WiseSaying> wiseSayings = wiseSayingService.findAll();

        if (wiseSayings.isEmpty()) {
            System.out.println("목록이 비어있습니다.");
            return;
        }
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println(wiseSaying);
        }
    }

    public void actionDelete(Command command) {
        int id = command.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요,");
            return;
        }

        boolean removed = wiseSayingService.deleteById(id);

        if (!removed)
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        else
            System.out.println(id + "번 명령이 삭제되었습니다.");
    }

    public void actionModify(Command command) {
        int id = command.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id(숫자)를 입력해주세요,");
            return;
        }

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);

        if (opWiseSaying.isEmpty()) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = opWiseSaying.get();
        System.out.println("명언(기존) : " + wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine();
        wiseSayingService.modify(wiseSaying, content, author);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }
}
