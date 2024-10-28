package dev.songpola.seiko.task.model;

import javax.swing.*;

public class TaskModel extends DefaultListModel<String> {
    public void addTask(String task) {
        addElement(task);
    }

    public void removeTask(int index) {
        remove(index);
    }

    public void markTaskAsCompleted(int index) {
        String task = getElementAt(index);
        set(index, task + " (Completed)");
    }
}
