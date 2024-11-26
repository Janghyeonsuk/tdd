package com.ll;

import com.ll.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTest {
    public static String run(String input) {
        input = input.stripIndent().trim() + "\n종료";

        // 테스트용 스캐너 생성
        Scanner sc = TestUtil.genScanner(input);

        // System.out의 출력을 스트림으로 받기
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        App app = new App(sc);
        app.run();

        //outputStream에 저장된 string을 반환
        String output = outputStream.toString();

        // setOutToByteArray 함수의 사용을 완료한 후 정리하는 함수, 출력을 다시 정상화 하는 함수
        TestUtil.clearSetOutToByteArray(outputStream);

        return output;
    }
}