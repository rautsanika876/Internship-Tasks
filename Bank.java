import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000;
        int choice;

        System.out.println("Welcome to Kiran Bank");

        while (true) {

            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Current Balance: " + balance);
            }

            else if (choice == 2) {
                System.out.print("Enter deposit amount: ");
                double amount = sc.nextDouble();

                if (amount > 0) {
                    balance = balance + amount;
                    System.out.println("Deposit successful.");
                    System.out.println("Updated Balance: " + balance);
                } else {
                    System.out.println("Invalid amount.");
                }
            }

            else if (choice == 3) {
                System.out.print("Enter withdrawal amount: ");
                double amount = sc.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount.");
                } else if (amount <= balance) {
                    balance = balance - amount;
                    System.out.println("Withdrawal successful.");
                    System.out.println("Updated Balance: " + balance);
                } else {
                    System.out.println("Transaction failed: Insufficient balance.");
                    System.out.println("Current Balance: " + balance);
                }
            }

            else if (choice == 4) {
                System.out.println("Thank you for banking with us. Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
