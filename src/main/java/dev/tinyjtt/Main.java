package dev.tinyjtt;

public class Main {

    public static void main(String[] args) {
        String command = args[0];
        TaskList taskList = new TaskList();
        if (args.length < 2) {
            System.out.println("Welcome to TinyJTT, A Tiny Java Task Tracker :)\n type 'tinyjtt help' for more information");
            return;
        }


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
                Task task = new Task(args[1]);
                taskList.addTask(task);
                System.out.println("Task added at index: " + taskList.getTasks().indexOf(task));
                break;
            case "remove":
                taskList.removeTask(Integer.parseInt(args[1]));
                System.out.println("Task removed at index: " + args[1]);
                break;
            case "update":
                taskList.updateTask(Integer.parseInt(args[1]), args[2]);
                System.out.println("Task updated at index: " + args[1]);
                break;
            case "mark-in-progress":
                taskList.updateTaskStatus(Integer.parseInt(args[1]), TaskStatus.InProgress);
                break;
            case "mark-done":
                taskList.updateTaskStatus(Integer.parseInt(args[1]), TaskStatus.Done);
            case "mark-pending":
                taskList.updateTaskStatus(Integer.parseInt(args[1]), TaskStatus.Pending);
                break;
            case "list":
                if (args.length == 2) {
                    taskList.displayTasks(TaskStatus.valueOf(args[1]));
                } else {
                    taskList.displayTasks();
                }
                break;
            case "save":
                taskList.saveToFile(args[1]);
                break;
            case "load":
                taskList.loadFromFile(args[1]);
                break;
            default:
                System.out.println("Invalid command, Type 'tinyjtt help' for more information");
        }
    }
}