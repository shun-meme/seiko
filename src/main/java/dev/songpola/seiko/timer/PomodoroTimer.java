package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerDisplay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class PomodoroTimer extends JPanel implements ActionListener {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private TimerDisplay timerDisplay;
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

        // Create a panel for the timer display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create a panel for small circles
        JPanel circlesPanel = new JPanel();
        circlesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Horizontal alignment with spacing

        // Adjusted sizes for aa and bb circles
        int aaCircleSize = 18; // Size for 'aa' circle
        int bbCircleSize = 12; // Size for 'bb' circle

        // Add small circles (aa and bb) to the circles panel
        for (int i = 0; i < 4; i++) { // Repeat for aa bb pattern
            circlesPanel.add(createCircle(aaCircleSize, new Color(0x9B7EDE))); // Add 'aa' circle
            circlesPanel.add(createCircle(bbCircleSize, new Color(0xF1DAC4))); // Add 'bb' circle
        }

        // Add circlesPanel to the display panel
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        displayPanel.add(circlesPanel, gbc);

        // Add timer display to the display panel
        gbc.gridy = 1; // Move to the next row for the timer display
        displayPanel.add(timerDisplay, gbc);

        add(displayPanel, BorderLayout.CENTER); // Position it in the center
    }

    private JButton createCircle(int size, Color color) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                // Override to make button circular
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color); // Use provided color
                g2.fillOval(0, 0, size, size); // Use specified size for the circle
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(size, size); // Set size for circular button
            }
        };
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        return button;
    }

    private void setupControls() {
        // Create a panel for controls
        var controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5)); // Add horizontal gap of 20 and move up 5 pixels

        // Combine Start and Stop into one toggle button
        toggleButton = createRoundedButton("Start", new Color(148, 0, 211)); // Dark purple color
        toggleButton.addActionListener(e -> toggleTimer());
        controls.add(toggleButton);

        var resetButton = createRoundedButton("Reset", new Color(148, 0, 211)); // Dark purple color
        resetButton.addActionListener(e -> {
            timer.stop();
            resetTimer();
            toggleButton.setText("Start"); // Reset button also changes text
        });
        controls.add(resetButton);

        add(controls, BorderLayout.SOUTH); // Add controls to the bottom
    }

    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                // Override to make button square with rounded edges
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded edges
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80, 40); // Set size for button
            }
        };
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        return button;
    }

    private void setupSettingsButton() {
        settingsButton = new JButton("⚙") { // Use a gear icon or text for settings
            @Override
            protected void paintComponent(Graphics g) {
                // Override to make button square with rounded edges and ensure icon visibility
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(148, 0, 211)); // Stylish background color
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Rounded edges
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50); // Set size for button
            }
        };

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
        JOptionPane.showMessageDialog(
                PomodoroTimer.this,
                "Break over! Time to work."
        );
        updateState(PomodoroState.WORK);
    }

    private void startShortBreak() {
        JOptionPane.showMessageDialog(
                PomodoroTimer.this,
                "Short Break Time!"
        );
        updateState(PomodoroState.SHORT_BREAK);
    }

    private void startLongBreak() {
        JOptionPane.showMessageDialog(
                this,
                "Long Break Time!"
        );
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
}
