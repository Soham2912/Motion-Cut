import java.util.Scanner;

public class TextAdventureGame {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Text Adventure Game!");
        startGame();
    }

    public static void startGame() {
        System.out.println("You wake up in a mysterious room. What do you do?");
        System.out.println("1. Look around");
        System.out.println("2. Open the door");

        int choice = getUserChoice(2);
        switch (choice) {
            case 1:
                lookAround();
                break;
            case 2:
                openDoor();
                break;
        }
    }

    public static void lookAround() {
        System.out.println("You see a key on the table and a window on the wall. What do you do?");
        System.out.println("1. Take the key");
        System.out.println("2. Open the window");
        System.out.println("3. Go back");

        int choice = getUserChoice(3);
        switch (choice) {
            case 1:
                takeKey();
                break;
            case 2:
                openWindow();
                break;
            case 3:
                startGame();
                break;
        }
    }

    public static void openDoor() {
        System.out.println("The door is locked. What do you do?");
        System.out.println("1. Try to pick the lock");
        System.out.println("2. Look for the key");
        System.out.println("3. Go back");

        int choice = getUserChoice(3);
        switch (choice) {
            case 1:
                System.out.println("You don't have any lockpicking skills. The attempt fails.");
                openDoor();
                break;
            case 2:
                lookAround();
                break;
            case 3:
                startGame();
                break;
        }
    }

    public static void takeKey() {
        System.out.println("You take the key. What do you do next?");
        System.out.println("1. Open the door");
        System.out.println("2. Look around");
        System.out.println("3. Go back");

        int choice = getUserChoice(3);
        switch (choice) {
            case 1:
                System.out.println("You use the key to unlock the door and escape. Congratulations, you win!");
                break;
            case 2:
                lookAround();
                break;
            case 3:
                startGame();
                break;
        }
    }

    public static void openWindow() {
        System.out.println("The window is stuck. What do you do?");
        System.out.println("1. Try to force it open");
        System.out.println("2. Look around");
        System.out.println("3. Go back");

        int choice = getUserChoice(3);
        switch (choice) {
            case 1:
                System.out.println("You manage to force the window open and escape. Congratulations, you win!");
                break;
            case 2:
                lookAround();
                break;
            case 3:
                startGame();
                break;
        }
    }

    public static int getUserChoice(int maxChoice) {
        int choice = 0;
        while (true) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxChoice) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number between 1 and " + maxChoice);
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
        return choice;
    }
}
