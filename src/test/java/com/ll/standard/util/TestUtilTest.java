package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtilTest {
    @Test
    @DisplayName("Testutil.getScanner()")
    public void t1() throws Exception {
        Scanner sc = TestUtil.getScanner("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                """.stripIndent().trim());

        String cmd = sc.nextLine();
        String content = sc.nextLine();
        String author = sc.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
        assertThat(author).isEqualTo("이순신");
    }

    @Test
    @DisplayName("Test.setOutToByteArray()")
    void t2() throws Exception {
        //given
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        System.out.println("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");
        //when
        String out = output.toString().trim();
        TestUtil.clearSetOutToByteArray(output);

        //then
        assertThat(out).isEqualTo("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");
    }
}
