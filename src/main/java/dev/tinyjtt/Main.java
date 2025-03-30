package dev.tinyjtt;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        Task myFirstTask = new Task("Make Java Program Work!");

        TaskList list = new TaskList();
        list.addTask(myFirstTask);

        System.out.println(myFirstTask.toString());

        Thread.sleep(5000);

        list.updateTask(0, "Make Kotlin Program");
        list.displayTasks();
        list.updateTaskStatus(0, TaskStatus.Done);
        System.out.println(myFirstTask.toString());

        list.displayTasks();
    }
}