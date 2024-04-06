import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String BASE_URL = "http://short.url/";

    public LinkShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    public String shorten(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return BASE_URL + longToShortMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);
        return BASE_URL + shortUrl;
    }

    public String expand(String shortUrl) {
        String shortKey = shortUrl.substring(BASE_URL.length());
        return shortToLongMap.getOrDefault(shortKey, "Short URL not found.");
    }

    private String generateShortUrl() {
        
        return String.valueOf(System.currentTimeMillis() % 10000);
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    scanner.nextLine(); // Consume newline
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shorten(longUrl);
                    System.out.println("Short URL: " + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter the short URL: ");
                    scanner.nextLine(); // Consume newline
                    String shortInputUrl = scanner.nextLine();
                    String longOutputUrl = linkShortener.expand(shortInputUrl);
                    System.out.println("Long URL: " + longOutputUrl);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
