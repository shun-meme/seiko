package dev.songpola.seiko.timer.model;

public class TimerModel {
    private int remainingTime;
    private PomodoroState currentState;

    public TimerModel(PomodoroState initialState) {
        this.currentState = initialState;
        this.remainingTime = currentState.getDuration();
    }

    public void reset() {
        remainingTime = currentState.getDuration();
    }

    public void updateState(PomodoroState newState) {
        this.currentState = newState;
        reset();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public PomodoroState getCurrentState() {
        return currentState;
    }

    public void tick() {
        if (remainingTime > 0) {
            remainingTime--;
        }
    }

    public void updateDuration(int newDuration) {
        currentState = PomodoroState.fromDuration(newDuration);
        reset();
    }
}
