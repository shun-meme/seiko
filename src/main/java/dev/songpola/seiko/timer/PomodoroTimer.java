package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer extends JPanel implements ActionListener {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;
    private TimerDisplay timerDisplay;
    private Timer timer;
    private PomodoroState currentState = PomodoroState.WORK; // Start with work time
    private int remainingTime;
    private int cycleCount = 0;
    private JLabel cycleLabel;
    private JButton startButton, stopButton, resetButton;

    public PomodoroTimer() {
        setup();
        setupTimer();
        setupDisplay();
        setupControls();
    }

    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void setupTimer() {
        timer = new Timer(1000, this);
        remainingTime = currentState.getDuration();
    }

    private void setupDisplay() {
        timerDisplay = new TimerDisplay(remainingTime);
        cycleLabel = new JLabel("Cycle: " + cycleCount);
        add(timerDisplay);
        add(cycleLabel);
    }

    private void setupControls() {
        var controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        controls.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start");
        startButton.addActionListener(e -> timer.start());
        controls.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> timer.stop());
        controls.add(stopButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            timer.stop();
            resetTimer();
        });
        controls.add(resetButton);

        add(controls);
    }

    private void incrementCycle() {
        cycleCount++;
        cycleLabel.setText("Cycle: " + ((cycleCount - 1) % CYCLES_BEFORE_LONG_BREAK + 1));
        if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
            JOptionPane.showMessageDialog(this, "Long break time! Total cycles completed: " + (cycleCount / CYCLES_BEFORE_LONG_BREAK));
        }
    }

    private void onTimerEnd() {
        if (currentState == PomodoroState.WORK) {
            incrementCycle();
            if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
                startLongBreak();
            } else {
                startShortBreak();
            }
        } else {
            startWork();
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

    private void updateTimer() {
        remainingTime--;
        timerDisplay.update(remainingTime);
        if (remainingTime <= 0) {
            timer.stop();
            onTimerEnd();
        }
    }
}
