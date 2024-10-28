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
    private int cycleCount;

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
        add(timerDisplay);
    }

    private void setupControls() {
        var controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        controls.setAlignmentX(Component.CENTER_ALIGNMENT);

        var startButton = new JButton("Start");
        startButton.addActionListener(e -> timer.start());
        controls.add(startButton);

        var stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> timer.stop());
        controls.add(stopButton);

        var resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            timer.stop();
            resetTimer();
        });
        controls.add(resetButton);

        add(controls);
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
