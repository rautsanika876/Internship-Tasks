import java.util.Scanner;

abstract class Passenger {
    String name;
    int age;

    Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract double getDiscount();
}

class RegularPassenger extends Passenger {
    RegularPassenger(String name, int age) {
        super(name, age);
    }

    double getDiscount() {
        return 0;
    }
}

class StudentPassenger extends Passenger {
    StudentPassenger(String name, int age) {
        super(name, age);
    }

    double getDiscount() {
        return 10;
    }
}

class Flight {
    String code, airline, source, destination;
    int seats;
    double fare;

    Flight(String code, String airline, String source,
           String destination, int seats, double fare) {
        this.code = code;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    void display() {
        System.out.println(code + " | " + airline + " | "
                + source + " to " + destination
                + " | Seats: " + seats + " | Fare: Rs." + fare);
    }
}

class Booking {
    String bookingId;
    Flight flight;
    Passenger passenger;
    int tickets;
    double subtotal, discount, total;

    Booking(String bookingId, Flight flight,
            Passenger passenger, int tickets) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;

        subtotal = flight.fare * tickets;
        discount = subtotal * passenger.getDiscount() / 100;
        total = subtotal - discount;
    }

    void display() {
        System.out.println("\nBooking confirmed!");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight: " + flight.code);
        System.out.println("Route: " + flight.source
                + " to " + flight.destination);
        System.out.println("Passenger: " + passenger.name);
        System.out.println("Age: " + passenger.age);
        System.out.println("Passenger Type: "
                + passenger.getClass().getSimpleName());
        System.out.println("Tickets: " + tickets);
        System.out.println("Base Fare per ticket: Rs." + flight.fare);
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Discount: Rs." + discount);
        System.out.println("Final Amount: Rs." + total);
    }
}

public class FlightBooking {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Flight[] flights = {
            new Flight("PN101", "Air India", "Pune",
                    "Delhi", 20, 4000),
            new Flight("MB102", "IndiGo", "Mumbai",
                    "Bengaluru", 15, 3500),
            new Flight("PN103", "Air India", "Pune",
                    "Hyderabad", 10, 3000)
        };

        Booking lastBooking = null;
        int bookingNumber = 1001;

        while (true) {
            System.out.println("\nWelcome to Sanika Air Booking");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                for (Flight f : flights) {
                    f.display();
                }
            }

            else if (choice == 2) {
                System.out.print("Enter flight code: ");
                String code = sc.nextLine();

                Flight selected = null;

                for (Flight f : flights) {
                    if (f.code.equalsIgnoreCase(code)) {
                        selected = f;
                        break;
                    }
                }

                if (selected == null) {
                    System.out.println("Invalid flight code.");
                    continue;
                }

                System.out.print("Enter passenger name: ");
                String name = sc.nextLine().trim();

                System.out.print("Enter age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Passenger type (Regular/Student): ");
                String type = sc.nextLine();

                System.out.print("Enter number of tickets: ");
                int tickets = sc.nextInt();
                sc.nextLine();

                if (name.isEmpty() || age <= 0 || tickets <= 0) {
                    System.out.println("Invalid booking details.");
                    continue;
                }

                if (tickets > selected.seats) {
                    System.out.println(
                            "Booking failed: Not enough seats available.");
                    continue;
                }

                Passenger passenger;

                if (type.equalsIgnoreCase("Student")) {
                    passenger = new StudentPassenger(name, age);
                } else if (type.equalsIgnoreCase("Regular")) {
                    passenger = new RegularPassenger(name, age);
                } else {
                    System.out.println("Invalid passenger type.");
                    continue;
                }

                lastBooking = new Booking(
                        "BK" + bookingNumber++,
                        selected, passenger, tickets);

                selected.seats -= tickets;

                lastBooking.display();
            }

            else if (choice == 3) {
                if (lastBooking == null) {
                    System.out.println("No booking found.");
                } else {
                    lastBooking.display();
                }
            }

            else if (choice == 4) {
                System.out.println(
                        "Thank you for using Kiran Air Booking!");
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
