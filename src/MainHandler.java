import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class MainHandler implements UserInterface {
    String decision;
    Scanner scanner = new Scanner(System.in);
    TravelOffice travelOffice = new TravelOffice();
    int tripID = 0;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MainHandler.logger = logger;
    }

    private static Logger logger = Logger.getLogger("mod12.ex01.to");;
    public MainHandler() {

    }

    private String getAnswerFromUser() {
        return scanner.nextLine();
    }

    private LocalDate stringToDate(String str) {
        Character[] breaks = {'/', '-', ' '};
        int[] date = {0, 0, 0};
        int time = 0;
        int now = 0;
        int pow = 1;
        for (int i = 0, n = str.length(); i < n; i++) {
            char c = str.charAt(i);
            if (Arrays.asList(breaks).contains(c)) {
                time++;
                pow = 1;
                now = 0;
                if (time == 2) {
                    pow = 3;
                }
            } else {
                int kek = Character.getNumericValue(c) * ((int) Math.pow(10, pow));
                now += kek;
                date[time] = now;
                pow--;
            }
        }
        return LocalDate.of(date[2], date[1], date[0]);
    }

    public void menu() {
        switch (getDecision()) {
            case "1": {
                addCustomer();
                break;
            }
            case "2": {
                addTrip();
                break;
            }
            case "3": {
                assignTrip();
                break;
            }
            case "4": {
                removeCustomer();
                break;
            }
            case "5": {
                removeTrip();
                break;
            }
            case "6": {
                showTrips();
                break;
            }
            case "7": {
                showCustomers();
                break;
            }
            default: {
                System.out.println("no such option");
            }
        }
    }

    @Override
    public Customer addCustomer() {
        System.out.println("Street: ");
        String s = getAnswerFromUser();
        System.out.println("zip: ");
        String z = getAnswerFromUser();
        System.out.println("city: ");
        String c = getAnswerFromUser();
        Address address = new Address(s, z, c);
        System.out.println("Name: ");
        String n = getAnswerFromUser();
        Customer customer = new Customer(n);
        customer.setAddress(address);
        customer.setTrip(null);
        travelOffice.addCustomer(customer);
        logger.info("Customer added:" + customer.toString());

        return customer;
    }

    @Override
    public Trip addTrip() {
        System.out.println("choose type of trip \n" +
                "1-abroad\n" +
                "2-domestic\n");
        String tripType = getAnswerFromUser();
        System.out.println("Start date: ");
        LocalDate startLocalDate = stringToDate(getAnswerFromUser());
        System.out.println("End date: ");
        LocalDate endLocalDate = stringToDate(getAnswerFromUser());
        System.out.println("Destination: ");
        String destination = getAnswerFromUser();
        System.out.println("Price: ");
        int price = Integer.parseInt(getAnswerFromUser());
        switch (tripType) {
            case ("1"): {
                AbroadTrip abroadTrip = new AbroadTrip(startLocalDate, endLocalDate, destination, price);
                travelOffice.addTrip(String.valueOf(tripID), abroadTrip);
                System.out.println("Insurance: ");
                abroadTrip.setInsurance(Integer.parseInt(getAnswerFromUser()));
                tripID++;
                logger.info("Abroad trip added" + abroadTrip.toString()+"ID: "+String.valueOf(tripID));
                return abroadTrip;
            }
            case ("2"): {
                DomesticTrip domesticTrip = new DomesticTrip(startLocalDate, endLocalDate, destination, price);
                travelOffice.addTrip(String.valueOf(tripID), domesticTrip);
                System.out.println("Insurance: ");
                domesticTrip.setOwnArrivalDiscount(Integer.parseInt(getAnswerFromUser()));
                tripID++;
                logger.info("Domestic trip added" + domesticTrip.toString()+"ID: "+String.valueOf(tripID));
                return domesticTrip;
            }
            default: {
                tripID++;
                return null;
            }
        }
    }

    @Override
    public void assignTrip() {
        System.out.println("which customer would you like to assign the trip " +
                "to and choose the id of the trip you would like to assign");
        try {
            travelOffice.findCustomerByName(getAnswerFromUser()).setTrip(travelOffice.findTripByID(String.valueOf(getAnswerFromUser())));
        } catch (NoSuchCustomerException | NoSuchTripException nS) {
            System.out.println(nS.getMessage());
            logger.warning("Tried assigning a non-existing trip");
        }
    }

    @Override
    public boolean removeCustomer() {
        System.out.println("which customer would you like to remove");
        try {
            Customer s = travelOffice.findCustomerByName(getAnswerFromUser());
            TravelOffice.getCustomerHashSet().removeIf(c -> c.equals(s));
            return true;
        } catch (NoSuchCustomerException nSC) {
            System.out.println(nSC.getMessage());
            logger.warning("Tried removing a non-existing customer");
            return false;
        }
    }

    @Override
    public boolean removeTrip() {
        System.out.println("which trip would you like to remove");
        try {
            return travelOffice.removeTrip(getAnswerFromUser());
        } catch (NoSuchTripException nST) {
            System.out.println(nST.getMessage());
            logger.warning("Tried removing a non-existing trip");

            return false;
        }
    }

    @Override
    public void showTrips() {
        TravelOffice.getTripMap().values().forEach(x -> System.out.println(x.toString()));
    }

    @Override
    public void showCustomers() {
        TravelOffice.getCustomerHashSet().forEach(x -> System.out.println(x.toString()));
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision() {
        System.out.println("Welcome to your own personal Travel office! \n" +
                "to add a customer to your customer list choose   1 \n" +
                "to add a trip choose   2 \n" +
                "to assign a trip to a customer choose   3\n" +
                "to remove a customer from the customer list choose   4 \n" +
                "to remove a trip from the trip list choose   5 \n" +
                "to list all the trips choose   6\n" +
                "to list all customers choose   7");
        decision = scanner.nextLine();
        menu();
    }

    public static void main(String[] args) {
        MainHandler mainHandler = new MainHandler();
        while (true) {
            mainHandler.setDecision();
        }

    }
}
