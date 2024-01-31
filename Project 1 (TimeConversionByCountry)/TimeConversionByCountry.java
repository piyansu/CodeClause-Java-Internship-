package normal_project;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TimeConversionByCountry {
    // A map to store city names and corresponding time zone IDs
    private static final Map<String, String> COUNTRY_MAP = createCountryMap();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Get the local time zone ID from the user
            ZoneId localZoneId = getZoneIdFromUser("Enter your local city (e.g. Kolkata, Tashkent, Kuwait): ");

            // Display available cities for timing
            System.out.println("Available cities for timing:");
            COUNTRY_MAP.keySet().forEach(System.out::println);

            // Get the user-selected city and its corresponding time zone ID
            String selectedCity = getCityFromUser("Enter the city: ");
            ZoneId countryZoneId = ZoneId.of(COUNTRY_MAP.get(selectedCity));

            // Get the current local time and convert it to the selected city's time
            LocalDateTime localDateTime = LocalDateTime.now(localZoneId);
            LocalDateTime countryDateTime = convertToTimeZone(localDateTime, localZoneId, countryZoneId);

            // Display the results
            System.out.println("\nResults:");
            displayTime("Your Local Time", localZoneId, localDateTime);
            displayTime("Time in " + selectedCity, countryZoneId, countryDateTime);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to create the initial country map
    private static Map<String, String> createCountryMap() {
        Map<String, String> countryMap = new HashMap<>();
        countryMap.put("Kolkata", "Asia/Kolkata");
        countryMap.put("Tashkent", "Asia/Tashkent");
        countryMap.put("Kuwait", "Asia/Kuwait");
        countryMap.put("New York", "America/New_York");
        countryMap.put("London", "Europe/London");
        countryMap.put("Tokyo", "Asia/Tokyo");
        // Add more cities and corresponding time zone IDs as needed
        return countryMap;
    }

    // Method to get a valid time zone ID from the user
    private static ZoneId getZoneIdFromUser(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String city = sc.nextLine().trim();

        while (!isValidCity(city)) {
            System.out.print("Invalid city. Please enter a valid city: ");
            city = sc.nextLine().trim();
        }

        return ZoneId.of(COUNTRY_MAP.get(city));
    }

    // Method to check if a city is valid
    private static boolean isValidCity(String city) {
        return COUNTRY_MAP.containsKey(city);
    }

    // Method to get a valid city from the user
    private static String getCityFromUser(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String city = sc.nextLine().trim();

        while (!COUNTRY_MAP.containsKey(city)) {
            System.out.print("Invalid city. Please enter a valid city: ");
            city = sc.nextLine().trim();
        }

        return city;
    }

    // Method to convert a local date-time to a different time zone
    private static LocalDateTime convertToTimeZone(LocalDateTime localDateTime, ZoneId fromZone, ZoneId toZone) {
        return localDateTime.atZone(fromZone).withZoneSameInstant(toZone).toLocalDateTime();
    }

    // Method to display the formatted time
    private static void displayTime(String label, ZoneId zoneId, LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = formatter.format(dateTime);
        System.out.println("------------------------------------------------------------");
        System.out.println(label + " (" + zoneId + "): " + formattedTime);
        System.out.println("------------------------------------------------------------");
    }
}
