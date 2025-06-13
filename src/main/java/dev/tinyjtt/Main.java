package dev.tinyjtt;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Welcome to TinyJTT, A Tiny Java Task Tracker :)\n type 'tinyjtt help' for more information");
            return;
        }
        String command = args[0];
        TaskList taskList = TaskList.loadFromFile();
        String statusCap;
        int taskIndex;

        switch (command) {
            case "help":
                System.out.println("List of commands:");
                System.out.println("add <description>");
                System.out.println("remove <index>");
                System.out.println("update <index> <description>");
                System.out.println("mark-in-progress <index>");
                System.out.println("mark-done <index>");
                System.out.println("mark-pending <index>");
                System.out.println("list");
                System.out.println("list <status>");
                System.out.println("save <path>");
                System.out.println("load <path>");
                break;
            case "add":
                // Allows for descriptions to have sentences
                String desc = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                Task task = new Task(desc);
                TaskList.loadFromFile();
                taskList.addTask(task);
                System.out.println("Task added at index: " + taskList.getTasks().indexOf(task));
                taskList.saveToFile();
                break;
            case "remove":
                TaskList.loadFromFile();
                taskList.removeTask(Integer.parseInt(args[1]));
                taskList.saveToFile();
                System.out.println("Task removed at index: " + args[1]);
                break;
            case "update":
                String updateDescription = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                TaskList.loadFromFile();
                taskList.updateTask(Integer.parseInt(args[1]), updateDescription);
                taskList.saveToFile();
                System.out.println("Task updated at index: " + args[1]);
                break;
            case "mark-in-progress":
                // 1. Check if enough arguments are provided
                if (args.length == 2) {
                    System.out.println("Error: Please provide the task index to mark as in progress.");
                    System.out.println("Usage: tinyjtt mark-in-progress <task_index>");
                    break; // Exit the switch case
                }

                try {
                    // 2. Safely parse the task index
                    taskIndex = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid task index. Please provide a number.");
                    System.out.println("Usage: tinyjtt mark-in-progress <task_index>");
                    break; // Exit the switch case
                }

                TaskList.loadFromFile(); // Load existing tasks

                // 3. (Crucial) Check if the provided task index is valid within the taskList
                // Assuming TaskList has a method to get the number of tasks, e.g., getTaskCount() or similar
                if (taskIndex < 0 || taskIndex >= taskList.tasks.size()) { // Assuming 0-indexed tasks
                    System.out.println("Error: Task index " + taskIndex + " is out of bounds. Please provide a valid index.");
                    System.out.println("Currently, there are " + taskList.tasks.size() + " tasks (indices 0 to " + (taskList.tasks.size() - 1) + ").");
                    break;
                }

                taskList.updateTaskStatus(taskIndex, TaskStatus.InProgress);
                taskList.saveToFile();
                System.out.println("Task marked as in progress at index " + taskIndex);
                break;
            case "mark-done":
                if (args.length == 2) {
                    System.out.println("Error: Please provide the task index to mark as in progress.");
                    System.out.println("Usage: tinyjtt mark-in-progress <task_index>");
                    break;
                }

                try {
                    taskIndex = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid task index. Please provide a number.");
                    System.out.println("Usage: yourProgramName mark-in-progress <task_index>");
                    break;
                }

                TaskList.loadFromFile();

                if (taskIndex < 0 || taskIndex >= taskList.tasks.size()) {
                    System.out.println("Error: Task index " + taskIndex + " is out of bounds. Please provide a valid index.");
                    System.out.println("Currently, there are " + taskList.tasks.size() + " tasks (indices 0 to " + (taskList.tasks.size() - 1) + ").");
                    break;
                }

                taskList.updateTaskStatus(Integer.parseInt(args[1]), TaskStatus.Done);
                taskList.saveToFile();
                System.out.println("Task marked as done at index " + args[1]);
                break;
            case "mark-pending":
                if (args.length == 2) {
                    System.out.println("Error: Please provide the task index to mark as in progress.");
                    System.out.println("Usage: tinyjtt mark-in-progress <task_index>");
                    break;
                }

                try {
                    taskIndex = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid task index. Please provide a number.");
                    System.out.println("Usage: yourProgramName mark-in-progress <task_index>");
                    break;
                }

                TaskList.loadFromFile();

                if (taskIndex < 0 || taskIndex >= taskList.tasks.size()) {
                    System.out.println("Error: Task index " + taskIndex + " is out of bounds. Please provide a valid index.");
                    System.out.println("Currently, there are " + taskList.tasks.size() + " tasks (indices 0 to " + (taskList.tasks.size() - 1) + ").");
                    break;
                }

                taskList.updateTaskStatus(Integer.parseInt(args[1]), TaskStatus.Pending);
                taskList.saveToFile();
                System.out.println("Task marked as done at index " + args[1]);
                break;
            case "list":
                TaskList.loadFromFile();

                if (args.length == 2) {
                    //Capitalizes String to match Done and Pending Enum standards
                    statusCap = Character.toTitleCase(args[1].charAt(0)) + args[1].substring(1);

                    // I can't add in-progress as an Enum nor inprogess to be capitalized (for loop to cap the I and P)
                    // when marking the task as In Progress.
                    // if the user types either list in-progress or inprogress or In-Progress
                    // should mark the task as In Progress :)
                    String userInput = args[1].toLowerCase().replace("-", "");

                    if (userInput.equals("inprogress")) {
                        taskList.displayTasks(TaskStatus.InProgress);
                        break;
                    }

                    taskList.displayTasks(TaskStatus.valueOf(statusCap));
                    break;
                } else {
                    taskList.displayTasks();
                }
                break;
            default:
                System.out.println("Invalid command, Type 'tinyjtt help' for more information");
        }
    }
}