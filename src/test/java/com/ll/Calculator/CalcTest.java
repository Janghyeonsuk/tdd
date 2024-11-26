package com.ll.Calculator;

import com.ll.calculator.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {
    @Test
    @DisplayName("Calc Test")
    void testComplexCalculation() {
        // given
        String exp = "((3 + 5) * 5 + -10) * 10 / 5";

        // when
        int result = Calc.run(exp);

        // then
        assertThat(result).isEqualTo(60);
    }
}
