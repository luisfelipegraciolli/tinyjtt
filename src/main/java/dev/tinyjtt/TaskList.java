package dev.tinyjtt;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;

public class TaskList {
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

    public void saveToFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), tasks);
            System.out.println("Arquivo JSON salvo com sucesso em: " + new File(filePath).getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo JSON: " + e.getMessage());
        }
    }

    public void loadFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class));
                System.out.println("Tarefas carregadas de: " + filePath);
            } catch (IOException e) {
                System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo não encontrado. Criando nova lista.");
            tasks = new ArrayList<>();
        }
    }
}
