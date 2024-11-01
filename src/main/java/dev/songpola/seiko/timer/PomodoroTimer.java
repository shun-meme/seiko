package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.model.TimerModel;
import dev.songpola.seiko.timer.view.TimerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer extends JPanel implements ActionListener {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private TimerDisplay timerDisplay;
    private Timer timer;
    private TimerModel timerModel; // Use the TimerModel class
    private JButton toggleButton; // Single button for Start/Stop
    private JButton settingsButton; // Stylish settings button

    public PomodoroTimer() {
        // Initialize with DEFAULT_WORK state
        timerModel = new TimerModel(PomodoroState.getDefaultWork());
        setup();
        setupTimer();
        setupDisplay();
        setupControls();
        setupSettingsButton(); // Setup stylish button in the top-right corner
    }

    private void setup() {
        setLayout(new BorderLayout()); // Change layout to BorderLayout for better positioning
    }

    private void setupTimer() {
        timer = new Timer(1000, this); // Timer updates every 1000 ms (1 second)
    }

    private void setupDisplay() {
        timerDisplay = new TimerDisplay(timerModel.getRemainingTime());
        timerDisplay.setFont(new Font("SansSerif", Font.BOLD, 90)); // Set larger font for timer display
        timerDisplay.setBorder(BorderFactory.createEmptyBorder()); // Remove any border
        add(timerDisplay, BorderLayout.CENTER); // Position it in the center
    }

    private void setupControls() {
        // Create a panel for controls
        var controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5)); // Add horizontal gap of 20 and move up 5 pixels

        // Combine Start and Stop into one toggle button
        toggleButton = new JButton("Start");
        toggleButton.addActionListener(e -> toggleTimer());
        controls.add(toggleButton);

        var resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            timer.stop();
            resetTimer();
            toggleButton.setText("Start"); // Reset button also changes text
        });
        controls.add(resetButton);

        add(controls, BorderLayout.SOUTH); // Add controls to the bottom
    }

    private void setupSettingsButton() {
        settingsButton = new JButton("⚙"); // Use a gear icon or text for settings
        settingsButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFocusPainted(false);
        settingsButton.setContentAreaFilled(true); // Allow background color to show
        settingsButton.setBackground(new Color(150, 50, 150)); // Set a different shade of purple

        // Set rounded edges by creating a rounded border
        settingsButton.setBorder(BorderFactory.createLineBorder(new Color(100, 0, 100), 2)); // Border color and thickness
        settingsButton.setBorderPainted(true);

        // Add action listener to the settings button
        settingsButton.addActionListener(e -> openTimerSettings());

        // Create a panel for the settings button and add it to the top-right corner
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        settingsPanel.add(settingsButton);
        add(settingsPanel, BorderLayout.NORTH); // Position it at the top
    }

    private void openTimerSettings() {
        new TimerSetting(timerModel.getCurrentState(), this::updateMainTimerDisplay).setVisible(true); // Open the TimerSetting window
    }

    private void toggleTimer() {
        if (timer.isRunning()) {
            timer.stop();
            toggleButton.setText("Start"); // Change button text to "Start"
        } else {
            timer.start();
            toggleButton.setText("Stop"); // Change button text to "Stop"
        }
    }

    private void onTimerEnd() {
        if (timerModel.getCurrentState().isBreak()) {
            startWork(); // Break is over, time to work
        } else {
            // Work is over, time for a break
            if ((timerModel.getCurrentState() == PomodoroState.getDefaultWork())) {
                timerModel.updateState(PomodoroState.getDefaultShortBreak());
                startShortBreak(); // Start short break
            } else {
                startLongBreak(); // Long break after some work cycles
            }
        }
        resetTimer(); // Reset timer after state change
    }

    private void startWork() {
        JOptionPane.showMessageDialog(this, "Break over! Time to work.");
        timerModel.updateState(PomodoroState.getDefaultWork());
    }

    private void startShortBreak() {
        JOptionPane.showMessageDialog(this, "Short Break Time!");
        timerModel.updateState(PomodoroState.getDefaultShortBreak());
    }

    private void startLongBreak() {
        JOptionPane.showMessageDialog(this, "Long Break Time!");
        timerModel.updateState(PomodoroState.getDefaultLongBreak());
    }

    private void resetTimer() {
        timerModel.reset();
        timerDisplay.update(timerModel.getRemainingTime());
        repaint(); // Repaint to update the display
    }

    private void updateMainTimerDisplay(int adjustedTime) {
        timerModel.updateDuration(adjustedTime); // Update the model with new duration
        timerDisplay.update(timerModel.getRemainingTime()); // Update the display
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timerModel.tick(); // Tick the model
        if (timerModel.getRemainingTime() <= 0) {
            onTimerEnd(); // Handle timer end
        } else {
            timerDisplay.update(timerModel.getRemainingTime()); // Update the display
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pomodoro Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new PomodoroTimer());
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}
