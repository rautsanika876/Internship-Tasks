import java.util.Scanner;

abstract class Passenger {
    private String name;
    private int age;

    Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscount();
}

class RegularPassenger extends Passenger {
    RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}

class StudentPassenger extends Passenger {
    StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscount() {
        return 10;
    }
}

class Flight {
    private String code;
    private String airline;
    private String source;
    private String destination;
    private int seats;
    private double fare;

    Flight(String code, String airline, String source,
           String destination, int seats, double fare) {
        this.code = code;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    public String getCode() {
        return code;
    }

    public int getSeats() {
        return seats;
    }

    public double getFare() {
        return fare;
    }

    public void bookSeats(int tickets) {
        seats -= tickets;
    }

    public void displayFlight() {
        System.out.println(code + " | " + airline + " | "
                + source + " to " + destination
                + " | Seats: " + seats
                + " | Fare: Rs." + fare);
    }
}

class Booking {
    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;

    Booking(String bookingId, Flight flight,
            Passenger passenger, int tickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;
    }

    public double calculateFare() {
        double subtotal = flight.getFare() * tickets;
        double discount =
                subtotal * passenger.getDiscount() / 100;

        return subtotal - discount;
    }

    public void displayBooking() {
        double subtotal = flight.getFare() * tickets;
        double discount =
                subtotal * passenger.getDiscount() / 100;

        System.out.println("\n--- Booking Summary ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Flight Code: " + flight.getCode());
        System.out.println("Tickets: " + tickets);
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Discount: Rs." + discount);
        System.out.println("Final Fare: Rs." + calculateFare());
    }
}

public class FlightBooking {

    static Scanner sc = new Scanner(System.in);

    static Flight[] flights = {
        new Flight("F101", "Air India",
                "Pune", "Delhi", 20, 4000),
        new Flight("F102", "IndiGo",
                "Mumbai", "Bengaluru", 15, 3500),
        new Flight("F103", "Air India",
                "Pune", "Hyderabad", 10, 3000)
    };

    static Booking lastBooking;
    static int bookingNumber = 1001;

    static void displayFlights() {
        System.out.println("\nAvailable Flights:");

        for (Flight flight : flights) {
            flight.displayFlight();
        }
    }

    static Flight findFlight(String code) {
        for (Flight flight : flights) {
            if (flight.getCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }

        return null;
    }

    static void bookFlight() {
        System.out.print("Enter Flight Code: ");
        String code = sc.nextLine().trim();

        Flight flight = findFlight(code);

        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Age: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid age.");
            sc.nextLine();
            return;
        }

        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Passenger Type (Student/Regular): ");
        String type = sc.nextLine().trim();

        System.out.print("Enter Number of Tickets: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ticket quantity.");
            sc.nextLine();
            return;
        }

        int tickets = sc.nextInt();
        sc.nextLine();

        if (name.isEmpty() || age <= 0 || tickets <= 0) {
            System.out.println("Invalid booking details.");
            return;
        }

        if (tickets > flight.getSeats()) {
            System.out.println("Not enough seats available.");
            return;
        }

        Passenger passenger;

        if (type.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else if (type.equalsIgnoreCase("Regular")) {
            passenger = new RegularPassenger(name, age);
        } else {
            System.out.println("Invalid passenger type.");
            return;
        }

        lastBooking = new Booking(
                "BK" + bookingNumber++,
                flight,
                passenger,
                tickets
        );

        flight.bookSeats(tickets);

        System.out.println("Booking successful!");
        lastBooking.displayBooking();
    }

    static void viewBooking() {
        if (lastBooking == null) {
            System.out.println("No booking found.");
        } else {
            lastBooking.displayBooking();
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===Flight Booking System ===");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayFlights();
                    break;

                case 2:
                    bookFlight();
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
