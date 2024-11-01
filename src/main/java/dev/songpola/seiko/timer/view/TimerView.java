package dev.songpola.seiko.timer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TimerView extends JPanel {
    private JButton startButton;
    private JButton resetButton;
    private JLabel timerLabel;

    public TimerView(ActionListener startAction, ActionListener resetAction) {
        setLayout(new FlowLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(startAction);
        add(startButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(resetAction);
        add(resetButton);

        timerLabel = new JLabel("25:00"); // Default timer display
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timerLabel);
    }

    public void setStartButtonText(String text) {
        startButton.setText(text);
    }

    public void updateTimerDisplay(int remainingTime) {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
