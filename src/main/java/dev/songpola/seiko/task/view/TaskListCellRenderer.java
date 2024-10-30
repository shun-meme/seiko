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
        setText(value.isCompleted() ? strikeThrough(title) : title);
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

    private String strikeThrough(String text) {
        return "<html><s>" + text + "</s></html>";
    }
}
