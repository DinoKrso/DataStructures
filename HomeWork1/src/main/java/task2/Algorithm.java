package task2;
import task2.FileUtils;
import task1.Stack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Algorithm {
    public static Stack<String> operators = new Stack<String>();
    public static Stack<Double> operands = new Stack<Double>();
    public static ArrayList<String> supportedOperators = new ArrayList<String>() {{
        add("+");
        add("-");
        add("*");
        add("/");
        add("%");
        add("^");
        add("√");
    }};

    public static Double calculate(String s1) {
        String[] str1 = s1.split(" ");
        for(int i = 0; i < str1.length; i++) {
            if(str1[i].equals("(")) {
                continue;
            } else if(str1[i].equals(")")) {
                Double operand1 = null;
                Double operand2 = null;
                String operator;

                operand1 = operands.pop();
                operator = operators.pop();

                if(operator.equals("√") == false) {
                    operand2 = operands.pop();
                }

                switch(operator) {
                    case "+":
                        operands.push(operand2 + operand1);
                        break;
                    case "-":
                        operands.push(operand2 - operand1);
                        break;
                    case "*":
                        operands.push(operand2 * operand1);
                        break;
                    case "/":
                        operands.push(operand2 / operand1);
                        break;
                    case "%":
                        operands.push(operand2 % operand1);
                        break;
                    case "^":
                        operands.push(Math.pow(operand2, operand1));
                        break;
                    case "√":
                        operands.push(Math.sqrt(operand1));
                        break;
                }
            } else if(supportedOperators.contains(str1[i])) {
                operators.push(str1[i]);
            } else {
                operands.push(Double.parseDouble(str1[i]));
            }
        }
        return operands.pop();
    }

    public static ArrayList<Double> calculateFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Double> results = new ArrayList<Double>();
        ArrayList<String> expressions  = FileUtils.readFile(filePath);

        for(String expression: expressions) {
            results.add(calculate(expression));
        }

        return results;
    }
}


