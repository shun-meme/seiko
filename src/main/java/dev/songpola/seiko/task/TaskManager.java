package dev.songpola.seiko.task;

import javax.swing.*;
import java.awt.*;

public class TaskManager extends JPanel {
    private TaskList taskList;

    public TaskManager() {
        super(new BorderLayout());
        addTaskList();
        addTaskInput();
        // TODO: load tasks from file
        // TODO: save tasks to file
    }

    private void addTaskList() {
        taskList = new TaskList();
        add(taskList, BorderLayout.CENTER);
    }

    private void addTaskInput() {
        TaskInput taskInput = new TaskInput(taskList);
        add(taskInput, BorderLayout.SOUTH);
    }
}
