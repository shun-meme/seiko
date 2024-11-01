package dev.songpola.seiko.timer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TimerControlPanel extends JPanel {
    private JButton startButton;
    private JButton resetButton;

    public TimerControlPanel(ActionListener startAction, ActionListener resetAction) {
        setLayout(new FlowLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(startAction);
        add(startButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(resetAction);
        add(resetButton);
    }

    public void setStartButtonText(String text) {
        startButton.setText(text);
    }
}
