package dev.songpola.seiko.timer.model;

public class TimerModel {
    private int remainingTime;
    private PomodoroState currentState;

    public TimerModel(PomodoroState initialState) {
        this.currentState = initialState;
        this.remainingTime = currentState.getDuration();
    }

    // Resets the remaining time to the current state's duration
    public void reset() {
        remainingTime = currentState.getDuration();
    }

    // Updates the current state and resets the timer
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

    // Decreases the remaining time by one second
    public void tick() {
        if (remainingTime > 0) {
            remainingTime--;
        }
    }

    // Updates the current state based on a new duration
    public void updateDuration(int newDuration) {
        // Only update if the duration is valid or reset to the default
        if (PomodoroState.isValidDuration(newDuration)) {
            currentState = PomodoroState.fromDuration(newDuration);
        } else {
            System.out.println("Invalid duration provided: " + newDuration + ". Resetting to default work duration.");
            currentState = PomodoroState.WORK; // Use WORK instead of DEFAULT_WORK
        }
        reset(); // Reset remaining time to the new current state's duration
    }
}
