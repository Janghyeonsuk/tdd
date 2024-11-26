package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCalculatorTest {

    @Test
    @DisplayName("더하기")
    public void testPlus() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.plus(1, 3);

        //then
        assertEquals(4, rs);
    }

    @Test
    @DisplayName("빼기")
    void testMinus() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.minus(1, 3);

        //then
        assertEquals(-2, rs);

    }

    @Test
    @DisplayName("곱하기")
    void testMultiply() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.multiply(1, 3);

        //then
        assertEquals(3, rs);

    }

    @Test
    @DisplayName("나누기")
    void testDivide() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.divide(1, 3);

        //then
        assertEquals(0, rs);

    }

    @Test
    @DisplayName("나머지")
    void testReminder() throws Exception {
        //given
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        //when
        int rs = simpleCalculator.reminder(1, 3);

        //then
        assertEquals(1, rs);

    }
}
