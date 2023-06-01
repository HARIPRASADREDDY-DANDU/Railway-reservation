import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("*****************************************************************************************************");
        System.out.println("                             WELCOME TO RAILWAY RESERVATION SYSTEM                                   ");
        System.out.println("*****************************************************************************************************");
        System.out.println("\n1.Book Ticket\n2.Print Stored Values\n3.Exit\n");
        System.out.println("Enter your choice: ");
        int n = s.nextInt();

        Bookings b = new Bookings();

        while (n != 3) {
            switch (n) {
                case 1:
                    System.out.println("Enter the train name");
                    s.nextLine();
                    String tname = s.nextLine();
                    b.tdetails(tname);

                    System.out.println("Enter the number of tickets you want to book: ");
                    int nt = s.nextInt();
                    s.nextLine();

                    for (int i = 0; i < nt; i++) {
                        System.out.println("Enter passenger name");
                        String passengerName = s.nextLine();
                        System.out.println("Enter passenger age");
                        int passengerAge = s.nextInt();
                        s.nextLine();
                        Passenger passenger = new Passenger(passengerName, passengerAge);
                        b.addPassenger(passenger);
                    }
                    b.details(nt);
                    break;

                case 2:
                    b.printStoredValues();
                    break;
            }

            System.out.println("\n1.Book Ticket\n2.Print Stored Values\n3.Exit\n");
            System.out.println("Enter your choice: ");
            n = s.nextInt();
        }

        System.out.println("Thanks for choosing our service. See you again");
        s.close();
    }
}

interface Train {
    String TrainName = "Express";
    void tdetails(String tname);
    int details(int nt);
}

class Bookings implements Train {
    static int totalSeats = 50;
    int currentSeat = 1;
    static LinkedHashSet<Passenger> passengerDetails = new LinkedHashSet<>();
    static LinkedHashMap<String, Integer> seatNumbers = new LinkedHashMap<>();

    public void tdetails(String tname) {
        if (tname.equals(TrainName)) {
            System.out.print("Train is available ");
            System.out.println("with: " + totalSeats + " seats.");
        } else {
            System.out.println("Sorry, the train is not available");
            System.out.println("Available trains are \"" + TrainName + "\" with " + totalSeats + " seats.");
        }
    }

    public void addPassenger(Passenger passenger) {
        boolean isDuplicate = false;
        for (Passenger p : passengerDetails) {
            if (p.getName().equals(passenger.getName())) {
                isDuplicate = true;
                break;
            }
        }
        if (isDuplicate) {
            System.out.println("Warning: Passenger '" + passenger.getName() + "' already exists. The duplicate entry has been ignored.");
        } else {
            passengerDetails.add(passenger);
            seatNumbers.put(passenger.getName(), currentSeat);
            currentSeat++;
        }
    }
    public int details(int nt) {
        System.out.println("*****************************************************************************************************");
        System.out.println("                                      Your Ticket Details                                            ");
        System.out.println("*****************************************************************************************************");
        System.out.println("                                      Train name: Express                                            ");
        System.out.println("*****************************************************************************************************");
        System.out.println("                                       Passenger Details                                             ");
        System.out.println("*****************************************************************************************************");
        System.out.println("Name\t Age\t Seat-Number");
        int count = 1;
        int registeredPassengers = 0;
        for (Passenger passenger : passengerDetails) {
            if (count>50-totalSeats) {
                System.out.println(passenger.getName() + "\t " + passenger.getAge() + "\t " + seatNumbers.get(passenger.getName()));
                registeredPassengers++;
            }
                count++;
            // } else {
            //     break;
            // }
        }
        System.out.println("*****************************************************************************************************");
        System.out.println("Registration Successful");
        totalSeats -= registeredPassengers;
        System.out.println("Total number of seats remaining: " + totalSeats);
        System.out.println("*****************************************************************************************************");
        System.out.println("Thanks for choosing our service. See you again");
        return totalSeats;
    }

    public void printStoredValues() {
        System.out.println("*****************************************************************************************************");
        System.out.println("                              Stored Passenger Details                                            ");
        System.out.println("*****************************************************************************************************");
        System.out.println("Name\t Age\t Seat-Number");

        for (Passenger passenger : passengerDetails) {
            Integer seatNumber = seatNumbers.get(passenger.getName());
            System.out.println(passenger.getName() + "\t " + passenger.getAge() + "\t " + seatNumber);
        }
        // int seatcount = seatNumber;
        System.out.println("*****************************************************************************************************");
        // return seatNumber;
    }
}


class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int hashCode() {
        return Objects.hash(name, age);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) obj;
        return age == passenger.age && Objects.equals(name, passenger.name);
    }
}
