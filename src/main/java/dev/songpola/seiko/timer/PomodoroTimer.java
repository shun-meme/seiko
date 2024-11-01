package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerDisplay; // Changed to TimerDisplay from TimerView
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer extends JPanel implements ActionListener {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private TimerDisplay timerDisplay; // Changed to TimerDisplay
    private Timer timer;
    private PomodoroState currentState = PomodoroState.WORK; // Start with work time
    private int remainingTime;
    private int cycleCount;
    private JButton toggleButton; // Single button for Start/Stop
    private JButton settingsButton; // Stylish settings button

    public PomodoroTimer() {
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
        remainingTime = currentState.getDuration();
    }

    private void setupDisplay() {
        timerDisplay = new TimerDisplay(remainingTime);
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
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorder(BorderFactory.createEmptyBorder()); // Remove default border

        // Add action listener to the settings button
        settingsButton.addActionListener(e -> openTimerSettings());

        // Create a panel for the settings button and add it to the top-right corner
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        settingsPanel.add(settingsButton);
        add(settingsPanel, BorderLayout.NORTH); // Position it at the top
    }

    private void openTimerSettings() {
        new TimerSetting(currentState, this::updateMainTimerDisplay).setVisible(true); // Open the TimerSetting window
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
        if (currentState.isBreak()) {
            startWork(); // Break is over, time to work
        } else {
            // Work is over, time for a break
            cycleCount++;
            if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
                startLongBreak(); // Long break after some work cycles
            } else {
                startShortBreak();
            }
        }
    }

    private void startWork() {
        JOptionPane.showMessageDialog(this, "Break over! Time to work.");
        updateState(PomodoroState.WORK);
    }

    private void startShortBreak() {
        JOptionPane.showMessageDialog(this, "Short Break Time!");
        updateState(PomodoroState.SHORT_BREAK);
    }

    private void startLongBreak() {
        JOptionPane.showMessageDialog(this, "Long Break Time!");
        updateState(PomodoroState.LONG_BREAK);
    }

    private void updateState(PomodoroState newState) {
        currentState = newState;
        resetTimer();
    }

    private void resetTimer() {
        remainingTime = currentState.getDuration();
        timerDisplay.update(remainingTime);
        repaint(); // Repaint to update the display
    }

    private void updateMainTimerDisplay(int adjustedTime) {
        timerDisplay.update(adjustedTime); // Update the main timer display when adjusted time changes
        remainingTime = adjustedTime; // Update remaining time for consistency
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (remainingTime > 0) {
            remainingTime--;
            timerDisplay.update(remainingTime);
        } else {
            onTimerEnd();
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
