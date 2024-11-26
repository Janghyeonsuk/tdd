package com.ll.Calculator;

import com.ll.calculator.SimpleCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class SimpleCalculatorTest {

    @Test
    @DisplayName("계산기")
    public void testPlus() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.plus(1, 3);

        //then
        assertThat(rs).isEqualTo(4);
    }

    @Test
    @DisplayName("빼기")
    void testMinus() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.minus(1, 3);

        //then
        assertThat(rs).isEqualTo(-2);
    }

    @Test
    @DisplayName("곱하기")
    void testMultiply() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.multiply(1, 3);

        //then
        assertThat(rs).isEqualTo(3);
    }

    @Test
    @DisplayName("나누기")
    void testDivide() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.divide(1, 3);

        //then
        assertThat(rs).isEqualTo(0);
    }

    @Test
    @DisplayName("나머지")
    void testReminder() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.reminder(1, 3);

        //then
        assertThat(rs).isEqualTo(1);
    }
}
