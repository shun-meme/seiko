package dev.songpola.seiko.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskInput extends JPanel {
    private final TaskList taskList;
    private JTextField taskField;

    public TaskInput(TaskList taskList) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.taskList = taskList;
        addTaskField();
        addButtonAddTask();
        addButtonRemoveTask();
        addButtonMarkTask();
    }

    private void addTaskField() {
        taskField = new JTextField();
        add(taskField);
    }

    private void addButtonAddTask() {
        add(makeButton("Add Task", e -> {
            String task = taskField.getText();
            // TODO: disable button if task is empty
            if (!task.isEmpty()) {
                taskList.addTask(task);
                taskField.setText("");
            }
        }));
    }

    private void addButtonRemoveTask() {
        add(makeButton("Remove Task", e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) taskList.removeTask(selectedIndex);
        }));
    }

    private void addButtonMarkTask() {
        add(makeButton("Mark as Completed", e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) taskList.markTaskAsCompleted(selectedIndex);
        }));
    }

    private JButton makeButton(String text, ActionListener callback) {
        var button = new JButton(text);
        button.addActionListener(callback);
        return button;
    }
}
