package com.ll.calculator;

import java.util.Stack;

public class Calc {
    public static int run(String expression) {
        // 1단계: 수식에서 공백 제거
        expression = expression.replaceAll("\\s+", "");

        // 2단계: 중위 표기법 수식을 후위 표기법으로 변환
        String postfix = infixToPostfix(expression);

        // 3단계: 후위 표기법 수식을 평가하여 결과 반환
        return evaluatePostfix(postfix);
    }

    private static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '-') {
                // 숫자 또는 음수 처리
                if (ch == '-' && (i == 0 || !Character.isDigit(expression.charAt(i - 1)))) {
                    postfix.append(ch);
                } else {
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        postfix.append(expression.charAt(i));
                        i++;
                    }
                    postfix.append(' ');
                    i--; // 반복문의 인덱스 조정
                }
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                // '('를 만날 때까지 연산자 팝
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // '(' 제거
                } else {
                    throw new IllegalArgumentException("수식에 괄호가 맞지 않습니다.");
                }
            } else if (isOperator(ch)) {
                // 우선순위가 높은 연산자 팝
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(ch);
            } else {
                throw new IllegalArgumentException("수식에 유효하지 않은 문자: " + ch);
            }
        }

        // 남은 연산자 팝
        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (top == '(' || top == ')') {
                throw new IllegalArgumentException("수식에 괄호가 맞지 않습니다.");
            }
            postfix.append(top).append(' ');
        }

        return postfix.toString();
    }

    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } else if (isOperator(token.charAt(0))) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("유효하지 않은 후위 표기식입니다.");
                }
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOperator(a, b, token.charAt(0)));
            } else {
                throw new IllegalArgumentException("후위 표기식에 유효하지 않은 토큰: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("유효하지 않은 후위 표기식입니다.");
        }

        return stack.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int applyOperator(int a, int b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("유효하지 않은 연산자: " + operator);
        };
    }
}
