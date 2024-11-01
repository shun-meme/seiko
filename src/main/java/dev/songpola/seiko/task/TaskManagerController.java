package dev.songpola.seiko.task;

import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.task.model.TaskModel;
import dev.songpola.seiko.task.view.TaskInputPanel;
import dev.songpola.seiko.task.view.TaskListPanel;

import javax.swing.*;
import java.awt.*;

public class TaskManagerController extends JPanel {
    private final TaskListModel model;
    private TaskListPanel taskListPanel;

    public TaskManagerController(TaskListModel model) {
        super(new BorderLayout());
        this.model = model;
        setup();
    }

    private void setup() {
        taskListPanel = new TaskListPanel(model);
        add(taskListPanel, BorderLayout.CENTER);

        var taskInputPanel = new TaskInputPanel(
            this::onAddTask,
            this::onRemoveTask,
            this::onMarkTaskAsCompleted
        );
        add(taskInputPanel, BorderLayout.SOUTH);
    }

    private void onAddTask(String task) {
        model.addTask(new TaskModel(task));
    }

    public void onRemoveTask() {
        int selectedIndex = taskListPanel.getSelectedIndex();
        if (selectedIndex != -1) model.removeTaskAt(selectedIndex);
    }

    public void onMarkTaskAsCompleted() {
        int selectedIndex = taskListPanel.getSelectedIndex();
        if (selectedIndex != -1) model.markTaskAsCompletedAt(selectedIndex);
    }
}
