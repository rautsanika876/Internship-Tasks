import java.util.Scanner;
import java.util.Random;

public class CabBookingChatbot {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[] cabTypes = {"Auto", "Sedan", "SUV"};
        int[] rates = {12, 18, 25};

        String[] sources = {
            "Pune Station",
            "Swargate",
            "Hinjawadi"
        };

        String[] destinations = {
            "Shivajinagar",
            "Kothrud",
            "Wakad"
        };

        int[] distances = {5, 8, 10};

        while (true) {
            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            if (choice == 2) {
                System.out.println("Thank you for using Cab Booking Chatbot!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }

            // Choose cab type
            System.out.println("\nChoose Cab Type:");
            System.out.println("1. Auto - Rs.12/km");
            System.out.println("2. Sedan - Rs.18/km");
            System.out.println("3. SUV - Rs.25/km");
            System.out.print("Enter your choice: ");

            int cabChoice = readInt();

            if (cabChoice < 1 || cabChoice > 3) {
                System.out.println("Invalid cab choice!");
                continue;
            }

            int cabIndex = cabChoice - 1;

            // Choose route
            System.out.println("\nChoose Route:");

            for (int i = 0; i < sources.length; i++) {
                System.out.println(
                    (i + 1) + ". " + sources[i] + " to "
                    + destinations[i] + " - "
                    + distances[i] + " km"
                );
            }

            System.out.print("Enter route number: ");
            int routeChoice = readInt();

            if (routeChoice < 1 || routeChoice > 3) {
                System.out.println("Invalid route choice!");
                continue;
            }

            int routeIndex = routeChoice - 1;

            // Calculate fare
            int distance = distances[routeIndex];
            int rate = rates[cabIndex];
            int fare = distance * rate;

            System.out.println("\n===== RIDE DETAILS =====");
            System.out.println("Cab Type: " + cabTypes[cabIndex]);
            System.out.println("Source: " + sources[routeIndex]);
            System.out.println("Destination: " + destinations[routeIndex]);
            System.out.println("Distance: " + distance + " km");
            System.out.println("Rate: Rs." + rate + "/km");
            System.out.println("Total Fare: Rs." + fare);

            // Confirm or cancel before OTP
            System.out.println("\n1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter your choice: ");

            int confirmChoice = readInt();

            if (confirmChoice == 2) {
                System.out.println("Ride cancelled successfully.");
                System.out.println("No OTP has been shared.");
                continue;
            }

            if (confirmChoice != 1) {
                System.out.println("Invalid choice! Ride not confirmed.");
                System.out.println("Returning to main menu.");
                continue;
            }

            // Generate and share a 4-digit OTP
            Random random = new Random();
            int otp = 1000 + random.nextInt(9000);

            System.out.println("\n===== BOOKING CONFIRMED =====");
            System.out.println("Your OTP is: " + otp);
            System.out.println("OTP shared successfully.");

            // Cancellation is not allowed after OTP sharing
            System.out.println(
                "Note: Ride cancellation is not allowed "
                + "after OTP sharing."
            );

            System.out.println("Thank you for booking with us!");
        }

        sc.close();
    }

    // Read integer input safely
    static int readInt() {
        while (true) {
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
    

