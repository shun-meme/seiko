package dev.songpola.seiko.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private int selectedIndex;

    public TaskList() {
        tasks = new ArrayList<>();
        selectedIndex = -1;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            selectedIndex = index;
        } else {
            selectedIndex = -1;
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            if (selectedIndex == index) {
                selectedIndex = -1;
            }
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}