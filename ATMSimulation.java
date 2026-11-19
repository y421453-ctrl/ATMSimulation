/*

name : Yaser Saad
Roll Number: 202510460

*/

import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String CORRECT_PIN = "1234";
        final int MAX_ATTEMPTS = 3;
        double balance = 1000.0;

        String[] transactions = new String[100];
        int transactionCount = 0;

        int attempts = 0;
        boolean isAuthenticated = false;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            attempts++;

            if (enteredPin.equals(CORRECT_PIN)) {
                isAuthenticated = true;
                System.out.println("Login successful!\n");
                break;
            } else {
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                if (remainingAttempts > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remainingAttempts);
                } else {
                    System.out.println("Maximum number of attempts reached. Exiting program.");
                }
            }
        }

        if (!isAuthenticated) {
            scanner.close();
            return;
        }

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n=== ATM Main Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.printf("Current Balance: $%.2f%n", balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        balance += depositAmount;
                        String record = String.format("Deposit: +$%.2f | Balance: $%.2f", depositAmount, balance);
                        if (transactionCount < transactions.length) {
                            transactions[transactionCount] = record;
                            transactionCount++;
                        }
                        System.out.println("Amount deposited successfully.");
                    } else {
                        System.out.println("Error: Deposit amount must be greater than zero.");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount <= 0) {
                        System.out.println("Error: Withdrawal amount must be greater than zero.");
                    } else if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        String record = String.format("Withdrawal: -$%.2f | Balance: $%.2f", withdrawAmount, balance);
                        if (transactionCount < transactions.length) {
                            transactions[transactionCount] = record;
                            transactionCount++;
                        }
                        System.out.println("Amount withdrawn successfully.");
                    } else {
                        System.out.println("Error: Insufficient balance.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Transaction History ---");
                    if (transactionCount == 0) {
                        System.out.println("No transactions found.");
                    } else {
                        for (int i = 0; i < transactionCount; i++) {
                            System.out.println((i + 1) + ". " + transactions[i]);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select between 1 and 5.");
            }
        }

        scanner.close();
    }
}