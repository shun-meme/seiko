package dev.songpola.seiko.task.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class TaskInputPanel extends JPanel {

    private final JTextField taskField;
    private final Consumer<String> onAddTask;
    private final Runnable onRemoveTask;
    private final Runnable onMarkTaskAsCompleted;

    public TaskInputPanel(
        Consumer<String> onAddTask,
        Runnable onRemoveTask,
        Runnable onMarkTaskAsCompleted
    ) {
        this.onAddTask = onAddTask;
        this.onRemoveTask = onRemoveTask;
        this.onMarkTaskAsCompleted = onMarkTaskAsCompleted;
        taskField = new JTextField();
        setup();
    }

    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(taskField);
        add(makeButton("Add Task", this::checkTaskField));
        add(makeButton("Remove Task", e -> onRemoveTask.run()));
        add(makeButton("Mark as Completed", e -> onMarkTaskAsCompleted.run()));
    }

    private void checkTaskField(ActionEvent e) {
        var task = taskField.getText();
        // TODO: disable button if task is empty
        if (!task.isEmpty()) {
            onAddTask.accept(task);
            taskField.setText("");
        }
    }

    private JButton makeButton(String text, ActionListener callback) {
        var button = new JButton(text);
        button.addActionListener(callback);
        return button;
    }
}
