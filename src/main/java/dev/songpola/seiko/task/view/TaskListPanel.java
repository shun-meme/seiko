package dev.songpola.seiko.task.view;

import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.task.model.TaskModel;

import javax.swing.*;
import java.awt.*;

public class TaskListPanel extends JPanel {
    private final JList<TaskModel> taskList;

    public TaskListPanel(TaskListModel model) {
        super(new BorderLayout());
        taskList = new JList<>(model);
        setup();
    }

    private void setup() {
        taskList.setCellRenderer(new TaskListCellRenderer());
        taskList.setFocusable(false);
        
        var scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public int getSelectedIndex() {
        return taskList.getSelectedIndex();
    }
}
