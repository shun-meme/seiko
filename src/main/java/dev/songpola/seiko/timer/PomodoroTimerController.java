package dev.songpola.seiko.timer;

import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.view.TimerControlPanel;
import dev.songpola.seiko.timer.view.TimerDisplayPanel;

import javax.swing.*;
import java.awt.*;

import static dev.songpola.seiko.timer.model.PomodoroState.CYCLES_BEFORE_LONG_BREAK;

public class PomodoroTimerController extends JPanel {
    private final TaskListModel taskListModel;

    private PomodoroState currentState = PomodoroState.WORK; // Start with work time
    private int cycleCount = 1;
    private int remainingTime;

    private TimerDisplayPanel timerDisplayPanel;
    private Timer timer;

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
        timerDisplayPanel = new TimerDisplayPanel(remainingTime, cycleCount);
        add(timerDisplayPanel, BorderLayout.CENTER);
    }

    private void setupControlPanel() {
        var controls = new TimerControlPanel(
            (e) -> timer.start(),
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
            // Break is over, time to work
            startWork();
            increaseCycle();
        } else {
            // Work is over, time for a break
            startBreak();
        }
    }

    private void startWork() {
        JOptionPane.showMessageDialog(this, "Break over! Time to work.");
        updateState(PomodoroState.WORK);
    }

    private void increaseCycle() {
        if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
            cycleCount = 1;
        } else {
            cycleCount++;
        }
        timerDisplayPanel.updateCycle(cycleCount);
    }

    private void startBreak() {
        if (cycleCount % CYCLES_BEFORE_LONG_BREAK == 0) {
            startLongBreak(); // Long break after some work cycles
        } else {
            startShortBreak();
        }
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
        timerDisplayPanel.updateTime(remainingTime);
    }

    private void onUpdateTimer() {
        remainingTime--;
        timerDisplayPanel.updateTime(remainingTime);
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
