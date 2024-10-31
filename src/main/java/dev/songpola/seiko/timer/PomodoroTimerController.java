package dev.songpola.seiko.timer;

import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerControlPanel;
import dev.songpola.seiko.timer.view.TimerDisplayPanel;

import javax.swing.*;
import java.awt.*;

public class PomodoroTimerController extends JPanel {
    private static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private final TaskListModel taskListModel;

    private TimerDisplayPanel timerDisplayPanel;
    private Timer timer;
    private PomodoroState currentState = PomodoroState.WORK; // Start with work time
    private int remainingTime;
    private int cycleCount = 0;
    private JLabel cycleLabel;

    public PomodoroTimerController(TaskListModel taskListModel) {
        this.taskListModel = taskListModel;
        setup();
        setupTimer();
        setupDisplayPanel();
        setupControlPanel();
    }

    private void setup() {
        setLayout(new BorderLayout());
    }

    private void setupTimer() {
        timer = new Timer(1000, e -> this.onUpdateTimer());
        remainingTime = currentState.getDuration();
    }

    private void setupDisplayPanel() {
        timerDisplayPanel = new TimerDisplayPanel(remainingTime);
        cycleLabel = new JLabel("Cycle: " + cycleCount);
        add(timerDisplayPanel, BorderLayout.CENTER);
        add(cycleLabel);
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

    private void increaseCycle() {
        cycleCount++;
        cycleLabel.setText("Cycle: " + ((cycleCount - 1) % CYCLES_BEFORE_LONG_BREAK + 1));
        if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
            JOptionPane.showMessageDialog(this, "Long break time! Total cycles completed: " + (cycleCount / CYCLES_BEFORE_LONG_BREAK));
        }
    }

    private void onTimerEnd() {
        if (currentState.isBreak()) {
            startWork(); // Break is over, time to work
        } else {
            // Work is over, time for a break
            increaseCycle();
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
        timerDisplayPanel.update(remainingTime);
    }

    private void onUpdateTimer() {
        remainingTime--;
        timerDisplayPanel.update(remainingTime);

        // If it's a work time
        if (!currentState.isBreak()) {
            // Add work time to the first task in the task list
            taskListModel.addTimeToFirstTask(1);
        }

        if (remainingTime <= 0) {
            timer.stop();
            onTimerEnd();
        }
    }
}
