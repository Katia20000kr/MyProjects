package ToDoApp;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== To-Do List ===");
            System.out.println("1. Show tasks");
            System.out.println("2. Add task");
            System.out.println("3. Mark task as completed");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number (1-5): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> showTasks();
                case 2 -> addTask();
                case 3 -> completeTask();
                case 4 -> deleteTask();
                case 5 -> System.out.println("Bye!");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        tasks.add(new Task(title));
        System.out.println("Task added!");
    }

    private static void completeTask() {
        showTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to complete: ");
        int index = getValidIndex();
        if (index != -1) {
            tasks.get(index).markAsCompleted();
            System.out.println("Task marked as completed.");
        }
    }

    private static void deleteTask() {
        showTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter task number to delete: ");
        int index = getValidIndex();
        if (index != -1) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        }
    }

    private static int getValidIndex() {
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input.");
            scanner.nextLine(); // clear
            return -1;
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return -1;
        }
        return index;
    }
}
