package dev.songpola.seiko.task.model;

public class TaskModel {
    private String title;
    private boolean completed;
    private int timeSpent;

    public TaskModel(String title) {
        this.title = title;
    }

    public TaskModel(String title, boolean completed, int timeSpent) {
        this.title = title;
        this.completed = completed;
        this.timeSpent = timeSpent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
