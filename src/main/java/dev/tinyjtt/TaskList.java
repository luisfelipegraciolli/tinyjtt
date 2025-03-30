package dev.tinyjtt;

import java.util.ArrayList;
import java.util.Date;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void updateTask(int index, String descriptionUpdated) {
        Task task = tasks.get(index);
        task.setDescription(descriptionUpdated);
        task.setUpdatedAt(new Date());
        tasks.set(index, task);
    }

    public void updateTaskStatus(int index, TaskStatus status) {
        Task task = tasks.get(index);
        task.setStatus(status);
        task.setUpdatedAt(new Date());
        tasks.set(index, task);
    }

    public void displayTasks() {
        for (Task task : tasks) {
            System.out.println(tasks.indexOf(task));
            task.printTask();
        }
    }

    public void displayTasks(TaskStatus status) {
        for (Task task : tasks) {
            if (status.equals(task.getStatus())) {
                task.printTask();
            }
        }
    }
}
