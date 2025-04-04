import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    private static final String FILE_PATH = "expenses.txt";
    private static ArrayList<String> expenses = new ArrayList<>();

    public static void main(String[] args) {
        loadExpenses(); // Load saved expenses
        Scanner scanner = new Scanner(System.in); // Takes user input

        // Keep program running until the user exists
        while (true) {
            System.out.println("\n 🪙Expense Tracker🪙");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Expenses");
            System.out.println("4. Filter by Category");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpense();
                    break;
                case 3:
                    calculateTotal();
                    break;
                case 4:
                    filterByCategory(scanner);
                    break;
                case 5:
                    saveExpenses();
                    System.out.println("Expenses saved. Goodbye!.👋");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }

        }
    }

    // Add a new expense
    private static void addExpense(Scanner scanner) {
        System.out.println("Enter description (e.g., Lunch): ");
        String description = scanner.nextLine();
        System.out.println("Enter amount (e.g., 10.50): ");
        String amount = scanner.nextLine();
        System.out.println("Enter category (e.g., Food): ");
        String category = scanner.nextLine();

        expenses.add(description + " | $" + amount + " | " + category);
        System.out.println("Expense added successfully.");
    }

    // View all expenses
    private static void viewExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No Expenses yet!");
        } else {
            System.out.println("--- Your Expenses ---");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
        }
    }

    private static void calculateTotal() {
        double total = 0;
        for (String expense : expenses) {
            String[] parts = expense.split(" \\| ");
            String amountStr = parts[1].substring(2); // Remove "$"
            total += Double.parseDouble(amountStr);
        }
        System.out.printf("Total Spending: $%.2f\n", total);
    }

    // Filter expenses by category
    private static void filterByCategory(Scanner scanner) {
        System.out.println("Enter category to filter (e.g., Food): ");
        String category = scanner.nextLine();
        boolean found = false;

        System.out.println("--- " + category + " Expenses ---");
        for (String expense : expenses) {
            if (expense.toLowerCase().endsWith(category.toLowerCase())) {

                System.out.println(expense);
                found = true;
            }
        }
        if (!found) System.out.println("No expenses found for " + category);
    }

    // Save expenses to file
    private static void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (String expense : expenses) {
                writer.println(expense);
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Load expenses from file
    private static void loadExpenses() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    expenses.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error loading expenses: " + e.getMessage());
            }
        }
    }
}
