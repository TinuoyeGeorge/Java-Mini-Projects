import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    //List to store tasks
    private static ArrayList<String> tasks = new ArrayList<>();
    //Scanner tohandle user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n=== TODO LIST MENU");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            // Read user choice
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the new line
            }else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            // perform actions based on the choice
            switch (choice){
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the Todo List!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");

            }

        }  while (choice != 4);
    }
    //Method to add a new task
    private static void addTask(){
        System.out.println("Enter a Task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added sucessfully!");
    }
    //Method to remove an existing task
    private static void removeTask(){
        if(tasks.isEmpty()){
            System.out.println("There's no task to remove!");
            return;
        }
        displayTasks();
        System.out.println("Enter a task number to remove:  ");
        if (scanner.hasNextInt()){
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 1 && index <= tasks.size()){
                tasks.remove(index -1);
                System.out.println("Task removed successfully!");
            }else {
                System.out.println("Invald task number.");
                scanner.nextLine();
            }
        }else {
            System.out.println("Please enter a valid number.");
            scanner.nextLine(); //clear invalid input
        }
    }

    // Method to display all tasks
    private static void displayTasks(){
        if (tasks.isEmpty()){
            System.out.println("No tasks in the list.");
        }else {
            System.out.println("Your Tasks: ");
            for (String task : tasks){
                System.out.println(task);
            }
        }
    }

}
