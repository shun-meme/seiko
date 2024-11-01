package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;

import javax.swing.*;
import java.awt.*;

public class TimerController extends JPanel {
    private PomodoroTimer timer;

    public TimerController() {
        timer = new PomodoroTimer();
        setup();
    }

    private void setup() {
        setLayout(new BorderLayout());
        add(timer.getView(), BorderLayout.CENTER);

        // Add a button or a menu item to open the TimerSetting
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> openSettings()); // Action to open settings
        add(settingsButton, BorderLayout.SOUTH); // Place the button at the bottom
    }

    private void openSettings() {
        // Create the TimerSetting window when the button is clicked
        TimerSetting settingsView = new TimerSetting(timer.getModel().getCurrentState(), this::updateTimerSettings);
        settingsView.setVisible(true); // Show the settings window
    }

    public void updateTimerSettings(int newDuration) {
        timer.getModel().updateDuration(newDuration); // Update the timer model with new duration
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pomodoro Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TimerController());
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}
