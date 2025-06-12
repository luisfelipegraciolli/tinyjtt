package dev.tinyjtt;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;

public class TaskList {
    private static final String DEFAULT_FILE_PATH = "tasks.json";
    @JsonProperty("tasks")
    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DEFAULT_FILE_PATH), tasks);
            System.out.println("Tasks saved to: " + DEFAULT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error while saving: " + e.getMessage());
        }
    }

    public static TaskList loadFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(DEFAULT_FILE_PATH);
        TaskList taskList = new TaskList();
        if (file.exists()) {
            try {
                taskList.tasks = objectMapper.readValue(
                        file,
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class)
                );
            } catch (IOException e) {
                System.err.println("Error while loading: " + e.getMessage());
            }
        } else {
            try {
                if (file.createNewFile()) {
                    // Salva lista vazia para inicializar o arquivo
                    taskList.saveToFile();
                    System.out.println("Arquivo criado: " + DEFAULT_FILE_PATH);
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
            }
        }
        return taskList;
    }
}
