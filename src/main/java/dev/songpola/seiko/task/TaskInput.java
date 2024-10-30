package dev.songpola.seiko.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TaskInput extends JPanel {
    private final TaskList taskList;
    private final DefaultListModel<Task> taskListModel;
    private JTextField taskField;

    public TaskInput(TaskList taskList, DefaultListModel<Task> taskListModel) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.taskList = taskList;
        this.taskListModel = taskListModel;
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
            String taskDescription = taskField.getText();
            if (!taskDescription.isEmpty()) {
                Task task = new Task(taskDescription, false);
                taskList.addTask(task);
                taskListModel.addElement(task);
                taskField.setText("");
            }
        }));
    }

    private void addButtonRemoveTask() {
        add(makeButton("Remove Task", e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskList.removeTask(selectedIndex);
                taskListModel.remove(selectedIndex);
            }
        }));
    }

    private void addButtonMarkTask() {
        add(makeButton("Mark as Completed", e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskList.markTaskAsCompleted(selectedIndex);
                taskListModel.set(selectedIndex, taskList.getTasks().get(selectedIndex));
            }
        }));
    }

    private JButton makeButton(String text, ActionListener callback) {
        var button = new JButton(text);
        button.addActionListener(callback);
        return button;
    }
}