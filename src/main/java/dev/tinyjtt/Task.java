package dev.tinyjtt;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID uuid;
    private String description;
    private TaskStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public Task() {
        this.uuid = UUID.randomUUID();
        this.description = "";
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = TaskStatus.Pending;
    }

    public Task(String description, TaskStatus status) {
        this.description = description;
        this.status = status;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.uuid = UUID.randomUUID();
    }

    public Task(String description){
        this.description = description;
        this.status = TaskStatus.Pending;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.uuid = UUID.randomUUID();

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void printTask() {
        System.out.println("Description: " + description + "\nStatus: " + status.toString() + "\n");
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid +
                "\ndescription='" + description + '\'' +
                "\nstatus=" + status +
                "\ncreatedAt=" + createdAt +
                "\nupdatedAt=" + updatedAt +
                '}';
    }
}
