package dev.songpola.seiko.task.view;

import dev.songpola.seiko.task.model.TaskModel;

import javax.swing.*;
import java.awt.*;

public class TaskListCellRenderer extends JLabel implements ListCellRenderer<TaskModel> {
    @Override
    public Component getListCellRendererComponent(
        JList<? extends TaskModel> list,
        TaskModel value,
        int index,
        boolean isSelected,
        boolean cellHasFocus
    ) {
        var title = value.getTitle();
        var timeSpent = value.getTimeSpent();
        var text = withTimeSpent(title, timeSpent);
        setText(value.isCompleted() ? strikeThrough(text) : text);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setOpaque(true);
        return this;
    }

    private String withTimeSpent(String title, int timeSpent) {
        return timeSpent > 0 ? title + " (" + timeSpent + "s)" : title;
    }

    private String strikeThrough(String text) {
        return "<html><s>" + text + "</s></html>";
    }
}
