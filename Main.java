import java.io.*;
import java.util.*;

class User implements Serializable {
    private String username;
    private String password;
    private List<Expense> expenses;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}

class Expense implements Serializable {
    private Date date;
    private String category;
    private double amount;

    public Expense(Date date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

class ExpenseTracker implements Serializable {
    private Map<String, User> users;

    public ExpenseTracker() {
        this.users = new HashMap<>();
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return false;
        }

        users.put(username, new User(username, password));
        System.out.println("User registered successfully.");
        return true;
    }

    public boolean loginUser(String username, String password) {
        if (!users.containsKey(username)) {
            System.out.println("User does not exist.");
            return false;
        }

        User user = users.get(username);
        if (!user.checkPassword(password)) {
            System.out.println("Incorrect password.");
            return false;
        }

        System.out.println("Login successful.");
        return true;
    }

    public void addExpense(String username, Expense expense) {
        User user = users.get(username);
        if (user != null) {
            user.addExpense(expense);
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void listExpenses(String username) {
        User user = users.get(username);
        if (user != null) {
            List<Expense> expenses = user.getExpenses();
            if (!expenses.isEmpty()) {
                System.out.println("Expense List for " + username + ":");
                for (Expense expense : expenses) {
                    System.out.println("Date: " + expense.getDate() + ", Category: " + expense.getCategory() + ", Amount: $" + expense.getAmount());
                }
            } else {
                System.out.println("No expenses found for " + username + ".");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void calculateCategoryWiseTotal(String username) {
        User user = users.get(username);
        if (user != null) {
            Map<String, Double> categoryTotal = new HashMap<>();
            List<Expense> expenses = user.getExpenses();
            for (Expense expense : expenses) {
                String category = expense.getCategory();
                double amount = expense.getAmount();
                categoryTotal.put(category, categoryTotal.getOrDefault(category, 0.0) + amount);
            }

            System.out.println("Category-wise Total Expenses for " + username + ":");
            for (Map.Entry<String, Double> entry : categoryTotal.entrySet()) {
                System.out.println(entry.getKey() + ": $" + entry.getValue());
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void saveDataToFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Data saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExpenseTracker loadDataFromFile(String filename) {
        ExpenseTracker expenseTracker = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            expenseTracker = (ExpenseTracker) objectIn.readObject();
            System.out.println("Data loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return expenseTracker != null ? expenseTracker : new ExpenseTracker();
    }
}

public class Main {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = ExpenseTracker.loadDataFromFile("expense_tracker_data.ser");

        // Register and login user (for testing)
        expenseTracker.registerUser("user1", "password1");
        expenseTracker.loginUser("user1", "password1");

        // Add expenses (for testing)
        expenseTracker.addExpense("user1", new Expense(new Date(), "Food", 50.0));
        expenseTracker.addExpense("user1", new Expense(new Date(), "Transport", 30.0));
        expenseTracker.addExpense("user1", new Expense(new Date(), "Food", 25.0));

        // List expenses (for testing)
        expenseTracker.listExpenses("user1");

        // Calculate category-wise total expenses (for testing)
        expenseTracker.calculateCategoryWiseTotal("user1");

        // Save data to file
        expenseTracker.saveDataToFile("expense_tracker_data.ser");
    }
}
