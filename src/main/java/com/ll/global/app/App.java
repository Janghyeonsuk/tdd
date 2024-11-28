package com.ll.global.app;

import com.ll.domain.system.controller.SystemController;
import com.ll.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner sc;
    private final SystemController systemController;
    private final WiseSayingController wiseSayingController;

    public App(Scanner sc) {
        this.sc = sc;
        this.systemController = new SystemController();
        this.wiseSayingController = new WiseSayingController(sc);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

//        wiseSayingController.initSampleData();

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Command command = new Command(cmd);

            switch (command.getActionName()) {
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                case "등록" -> wiseSayingController.actionAdd();
                case "목록" -> wiseSayingController.actionList(command);
                case "삭제" -> wiseSayingController.actionDelete(command);
                case "수정" -> wiseSayingController.actionModify(command);
                case "빌드" -> wiseSayingController.actionBuild();
                case "파일삭제" -> wiseSayingController.actionDirDelete();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }

    }
}