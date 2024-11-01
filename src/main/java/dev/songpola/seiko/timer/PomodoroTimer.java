package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;
import dev.songpola.seiko.timer.model.TimerModel;
import dev.songpola.seiko.timer.view.TimerView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PomodoroTimer {
    private TimerModel timerModel;
    private TimerView timerView;
    private Timer timer;

    public PomodoroTimer() {
        timerModel = new TimerModel(PomodoroState.WORK);
        timerView = new TimerView(this::startTimer, this::resetTimer);
        timer = new Timer(1000, this::onTimerTick);
        timerView.setVisible(true);
    }

    private void startTimer(ActionEvent e) {
        if (!timer.isRunning()) {
            timer.start();
            timerView.setStartButtonText("Stop");
        } else {
            timer.stop();
            timerView.setStartButtonText("Start");
        }
    }

    private void resetTimer(ActionEvent e) {
        timer.stop();
        timerModel.reset();
        timerView.updateTimerDisplay(timerModel.getRemainingTime());
        timerView.setStartButtonText("Start");
    }

    private void onTimerTick(ActionEvent e) {
        timerModel.tick();
        timerView.updateTimerDisplay(timerModel.getRemainingTime());

        if (timerModel.getRemainingTime() <= 0) {
            handleTimerEnd();
        }
    }

    private void handleTimerEnd() {
        if (timerModel.getCurrentState().isBreak()) {
            JOptionPane.showMessageDialog(timerView, "Break is over! Time to work.");
            timerModel.updateState(PomodoroState.WORK);
        } else {
            JOptionPane.showMessageDialog(timerView, "Work session is over! Time for a break.");
            timerModel.updateState(PomodoroState.SHORT_BREAK);
        }
        timerModel.reset();
    }

    public TimerView getView() {
        return timerView;
    }

    public TimerModel getModel() {
        return timerModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PomodoroTimer::new);
    }
}
