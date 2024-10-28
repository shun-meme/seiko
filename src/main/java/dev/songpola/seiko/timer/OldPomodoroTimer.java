package dev.songpola.seiko.timer;

import javax.swing.*;

public class OldPomodoroTimer extends JPanel {

    // Timer fields
    // private Timer timer;
    // private int remainingWorkTime; // In seconds
    // private int remainingBreakTime; // In seconds
    // private int cycleCount = 0;
    // private int workDuration; // Store original work duration
    // private int breakDuration; // Store original break duration
    // private JLabel timerDisplay; // For displaying countdown in seconds
    // private JButton startPauseButton;
    // private JButton restartButton; // New button
    // private JButton workButton;
    // private JButton breakButton;
    // private boolean isTimerRunning = false;
    // private boolean isBreakTime = false;
    //
    // // Preferences for saving settings
    // private Preferences prefs;
    //

    //
    // public void createAndShowGUI() {
    //     prefs = Preferences.userNodeForPackage(PomodoroTimer.class);
    //     loadPreferences();
    //
    //     JFrame frame = new JFrame("Pomodoro Timer");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setLayout(new GridLayout(7, 1)); // Adjusted layout rows
    //
    //     // Create components
    //     timerDisplay = new JLabel(String.format("%02d:00", workDuration), SwingConstants.CENTER);
    //     JLabel cycleCounterLabel = new JLabel("Cycles Completed: 0", SwingConstants.CENTER);
    //     startPauseButton = new JButton("Start");
    //     restartButton = new JButton("Restart");
    //
    //     // Create work and break input buttons
    //     workButton = new JButton("Work: " + workDuration + " min");
    //     breakButton = new JButton("Break: " + breakDuration + " min");
    //
    //     // Create adjustment buttons
    //     JButton doubleUpButton = new JButton("⬆⬆");
    //     JButton singleUpButton = new JButton("⬆");
    //     JButton singleDownButton = new JButton("⬇");
    //     JButton doubleDownButton = new JButton("⬇⬇");
    //
    //     // Add hover effects
    //     addHoverEffect(workButton);
    //     addHoverEffect(breakButton);
    //     addHoverEffect(startPauseButton);
    //     addHoverEffect(restartButton);
    //     addHoverEffect(doubleUpButton);
    //     addHoverEffect(singleUpButton);
    //     addHoverEffect(singleDownButton);
    //     addHoverEffect(doubleDownButton);
    //
    //     // Create panels for layout
    //     JPanel timerPanel = new JPanel();
    //     timerPanel.add(workButton);
    //     timerPanel.add(breakButton);
    //
    //     JPanel adjustmentPanel = new JPanel();
    //     adjustmentPanel.add(doubleUpButton);
    //     adjustmentPanel.add(singleUpButton);
    //     adjustmentPanel.add(singleDownButton);
    //     adjustmentPanel.add(doubleDownButton);
    //
    //     JPanel buttonPanel = new JPanel();
    //     buttonPanel.add(startPauseButton);
    //     buttonPanel.add(restartButton);
    //
    //     // Add components to the frame
    //     frame.add(timerDisplay);
    //     frame.add(timerPanel); // Add the timer panel
    //     frame.add(cycleCounterLabel);
    //     frame.add(adjustmentPanel); // Add the adjustment panel
    //     frame.add(buttonPanel); // Add the button panel
    //
    //     // Add action listeners
    //     startPauseButton.addActionListener(e -> {
    //         if (!isTimerRunning) {
    //             if (isBreakTime) {
    //                 startBreakTimer();
    //             } else {
    //                 startWorkTimer();
    //             }
    //             startPauseButton.setText("Pause");
    //             isTimerRunning = true;
    //         } else {
    //             pauseTimer();
    //             startPauseButton.setText("Start");
    //             isTimerRunning = false;
    //         }
    //     });
    //
    //     restartButton.addActionListener(e -> resetTimer());
    //
    //     workButton.addActionListener(e -> {
    //         isBreakTime = false;
    //         updateTimerDisplay(); // Update display to show work timer
    //         setButtonSelected(workButton);
    //     });
    //
    //     breakButton.addActionListener(e -> {
    //         isBreakTime = true;
    //         updateTimerDisplay(); // Update display to show break timer
    //         setButtonSelected(breakButton);
    //     });
    //
    //     // Adjustment button actions
    //     doubleUpButton.addActionListener(e -> adjustTimer(10));
    //     singleUpButton.addActionListener(e -> adjustTimer(1));
    //     singleDownButton.addActionListener(e -> adjustTimer(-1));
    //     doubleDownButton.addActionListener(e -> adjustTimer(-10));
    //
    //     frame.pack();
    //     frame.setVisible(true);
    // }
    //
    // private void addHoverEffect(JButton button) {
    //     button.addMouseListener(new MouseAdapter() {
    //         Color originalColor = button.getBackground();
    //
    //         @Override
    //         public void mouseEntered(MouseEvent e) {
    //             button.setBackground(originalColor.brighter());
    //         }
    //
    //         @Override
    //         public void mouseExited(MouseEvent e) {
    //             if (!button.getBackground().equals(new Color(160, 160, 160))) {
    //                 button.setBackground(originalColor);
    //             }
    //         }
    //     });
    // }
    //
    // private void setButtonSelected(JButton button) {
    //     // Reset colors for both buttons
    //     if (workButton != button) {
    //         workButton.setBackground(null);
    //         workButton.setForeground(Color.BLACK);
    //     }
    //     if (breakButton != button) {
    //         breakButton.setBackground(null);
    //         breakButton.setForeground(Color.BLACK);
    //     }
    //
    //     // Set selected button's background and text color
    //     button.setBackground(new Color(160, 160, 160)); // Slightly darker silver
    //     button.setForeground(Color.WHITE); // Change text color to white
    // }
    //
    // private void adjustTimer(int adjustment) {
    //     if (!isBreakTime) { // Work timer adjustments
    //         workDuration += adjustment;
    //         if (workDuration < 1) workDuration = 1; // Prevent negative duration
    //         workButton.setText("Work: " + workDuration + " min");
    //         if (!isTimerRunning) {
    //             updateTimerDisplay(); // Update display if timer is not running
    //         }
    //     } else { // Break timer adjustments
    //         breakDuration += adjustment;
    //         if (breakDuration < 1) breakDuration = 1; // Prevent negative duration
    //         breakButton.setText("Break: " + breakDuration + " min");
    //         if (!isTimerRunning) {
    //             updateTimerDisplay(); // Update display if timer is not running
    //         }
    //     }
    // }
    //
    // private void startWorkTimer() {
    //     if (remainingWorkTime <= 0) {
    //         remainingWorkTime = workDuration * 60; // Set remaining time to original
    //     }
    //     isBreakTime = false;
    //
    //     timer = new Timer(1000, e -> {
    //         if (remainingWorkTime > 0) {
    //             remainingWorkTime--;
    //             updateTimerDisplay(); // Update the display
    //         } else {
    //             timer.stop();
    //             playSound(); // Play sound on completion
    //             completePomodoro(); // Handle completion
    //         }
    //     });
    //
    //     timer.start();
    // }
    //
    // private void startBreakTimer() {
    //     if (remainingBreakTime <= 0) {
    //         remainingBreakTime = breakDuration * 60; // Set remaining time to original
    //     }
    //     isBreakTime = true;
    //
    //     timer = new Timer(1000, e -> {
    //         if (remainingBreakTime > 0) {
    //             remainingBreakTime--;
    //             updateTimerDisplay(); // Update the display
    //         } else {
    //             timer.stop();
    //             playSound(); // Play sound on completion
    //             completePomodoro(); // Handle completion
    //         }
    //     });
    //
    //     timer.start();
    // }
    //
    // private void pauseTimer() {
    //     if (timer != null) {
    //         timer.stop();
    //     }
    // }
    //
    // private void resetTimer() {
    //     if (timer != null) {
    //         timer.stop(); // Stop the timer if it's running
    //     }
    //     isTimerRunning = false;
    //     startPauseButton.setText("Start"); // Reset button text
    //     if (!isBreakTime) {
    //         remainingWorkTime = workDuration * 60; // Reset work time
    //     } else {
    //         remainingBreakTime = breakDuration * 60; // Reset break time
    //     }
    //     updateTimerDisplay(); // Update display to show initial values
    // }
    //
    // private void completePomodoro() {
    //     cycleCount++;
    //     isBreakTime = !isBreakTime; // Toggle between work and break
    //
    //     int option = JOptionPane.showConfirmDialog(null,
    //             "Pomodoro completed! Cycles: " + cycleCount + "\nStart " +
    //                     (isBreakTime ? "break" : "work") + " timer?",
    //             "Next Timer",
    //             JOptionPane.YES_NO_OPTION);
    //
    //     if (option == JOptionPane.YES_OPTION) {
    //         if (isBreakTime) {
    //             startBreakTimer();
    //         } else {
    //             startWorkTimer();
    //         }
    //         startPauseButton.setText("Pause");
    //         isTimerRunning = true;
    //     } else {
    //         startPauseButton.setText("Start");
    //         isTimerRunning = false;
    //     }
    //     savePreferences();
    // }
    //
    // private void updateTimerDisplay() {
    //     int minutes = isBreakTime ? remainingBreakTime / 60 : remainingWorkTime / 60;
    //     int seconds = isBreakTime ? remainingBreakTime % 60 : remainingWorkTime % 60;
    //     timerDisplay.setText(String.format("%02d:%02d", minutes, seconds));
    // }
    //
    // private void playSound() {
    //     // Play a sound notification (for example, a beep)
    //     Toolkit.getDefaultToolkit().beep();
    // }
    //
    // private void loadPreferences() {
    //     workDuration = prefs.getInt("workDuration", 25);
    //     breakDuration = prefs.getInt("breakDuration", 5);
    // }
    //
    // private void savePreferences() {
    //     prefs.putInt("workDuration", workDuration);
    //     prefs.putInt("breakDuration", breakDuration);
    // }
}
