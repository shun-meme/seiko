package dev.songpola.seiko.task.model;

import javax.swing.*;

public class TaskListModel extends DefaultListModel<TaskModel> {
    public void addTask(TaskModel task) {
        addElement(task);
    }

    public void removeTaskAt(int index) {
        remove(index);
    }

    public void markTaskAsCompletedAt(int index) {
        TaskModel task = get(index);
        task.setCompleted(true);
        set(index, task);
    }

    public void addTimeToFirstTask(int seconds) {
        if (isEmpty()) return;
        TaskModel task = get(0);
        task.setTimeSpent(task.getTimeSpent() + seconds);
        set(0, task);
    }
}
