package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerControlPanel;
import dev.songpola.seiko.timer.view.TimerDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer extends JPanel implements ActionListener {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private TimerDisplayPanel timerDisplayPanel;
    private Timer timer;
    private PomodoroState currentState = PomodoroState.WORK; // Start with work time
    private int remainingTime;
    private int cycleCount;

    public PomodoroTimer() {
        setup();
        setupTimer();
        setupDisplayPanel();
        setupControlPanel();
    }

    private void setup() {
        setLayout(new BorderLayout());
    }

    private void setupTimer() {
        timer = new Timer(1000, this);
        remainingTime = currentState.getDuration();
    }

    private void setupDisplayPanel() {
        timerDisplayPanel = new TimerDisplayPanel(remainingTime);
        add(timerDisplayPanel, BorderLayout.CENTER);
    }

    private void setupControlPanel() {
        var controls = new TimerControlPanel(
            (e) ->  timer.start(),
            (e) -> timer.stop(),
            (e) -> {
                timer.stop();
                resetTimer();
            }
        );
        add(controls, BorderLayout.SOUTH);
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
        timerDisplayPanel.update(remainingTime);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

    private void updateTimer() {
        remainingTime--;
        timerDisplayPanel.update(remainingTime);
        if (remainingTime <= 0) {
            timer.stop();
            onTimerEnd();
        }
    }
}
