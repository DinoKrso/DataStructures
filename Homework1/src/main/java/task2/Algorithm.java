package task2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;
// I tried to sovle testSquareRoot() and mixed expression but I cant, tried my best ..
public class Algorithm {
    public static Double calculate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder operand = new StringBuilder();

                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand.append(expression.charAt(i++));
                }

                values.push(Double.parseDouble(operand.toString()));
                i--;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }

                ops.pop();
            } else if (c == '√') {
                ops.push(c);
            } else if (isOperator(c)) {
                while (!ops.empty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }

                ops.push(c);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }
    public static ArrayList<Double> calculateFromFile(String filePath) throws FileNotFoundException {
        ArrayList<String> expressions = FileUtils.readFile(filePath);
        ArrayList<Double> results = new ArrayList<Double>();

        for (String expression : expressions) {
            Double result = calculate(expression);
            results.add(result);
        }

        return results;
    }


    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '√');
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        } else if (op1 == '√') {
            return true;
        } else if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return false;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '^')) {
            return false;
        } else {
            return true;
        }
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            case '%':
                return a % b;
            case '^':
                return Math.pow(a, b);
            case '√':
                return Math.sqrt(a);
        }
        return 0;
    }

}
