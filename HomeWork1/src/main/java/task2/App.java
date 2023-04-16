package task2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter '1' to enter an expression manually or '2' to provide a file path:");
        int inputChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character left by nextInt()

        if (inputChoice == 1) {
            System.out.println("Enter an arithmetic expression:");
            String expression = scanner.nextLine();
            Double result = Algorithm.calculate(expression);
            System.out.println("Result: " + result);
        } else if (inputChoice == 2) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            try {
                ArrayList<String> expressions = FileUtils.readFile(filePath);
                ArrayList<Double> results = Algorithm.calculateFromFile(filePath);
                System.out.println("Results:");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(expressions.get(i) + " = " + results.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filePath);
            }
        } else {
            System.out.println("Invalid input");
        }
    }

}

