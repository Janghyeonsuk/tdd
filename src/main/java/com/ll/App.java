package com.ll;

import com.ll.domain.system.SystemController;
import com.ll.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final SystemController systemController;
    private final WiseSayingController wiseSayingController;

    public App(Scanner scanner) {
        this.sc = scanner;
        this.systemController = new SystemController();
        this.wiseSayingController = new WiseSayingController(sc);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        boolean excute = true;
        while (excute) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            switch (cmd) {
                case "종료" -> {
                    systemController.actionExit();
                    excute = false;
                }
                case "등록" -> wiseSayingController.actionAdd(sc);
                case "목록" -> wiseSayingController.actionList();
            }
        }

    }
}