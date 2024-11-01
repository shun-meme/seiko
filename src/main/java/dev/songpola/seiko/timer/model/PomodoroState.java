package dev.songpola.seiko.timer.model;

public class PomodoroState {
    public static final PomodoroState WORK = new PomodoroState(25 * 60, false);           // Work time in seconds
    public static final PomodoroState SHORT_BREAK = new PomodoroState(5 * 60, true);      // Short break time in seconds
    public static final PomodoroState LONG_BREAK = new PomodoroState(15 * 60, true);      // Long break time in seconds

    private int duration;
    private final boolean isBreak;

    // Public constructor to allow instantiation with any duration
    public PomodoroState(int duration, boolean isBreak) {
        this.duration = duration;
        this.isBreak = isBreak;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setDuration(int duration) {
        this.duration = duration; // Allow changing to any duration
    }

    // Static factory methods for default states
    public static PomodoroState getDefaultWork() {
        return WORK;
    }

    public static PomodoroState getDefaultShortBreak() {
        return SHORT_BREAK;
    }

    public static PomodoroState getDefaultLongBreak() {
        return LONG_BREAK;
    }

    // Method to check if a duration is valid (if you want to keep predefined validation)
    public static boolean isValidDuration(int duration) {
        return duration > 0; // Allow any positive duration
    }

    public static PomodoroState fromDuration(int duration) {
        // Create a new PomodoroState with the specified duration
        if (isValidDuration(duration)) {
            return new PomodoroState(duration, duration == SHORT_BREAK.getDuration() || duration == LONG_BREAK.getDuration());
        }
        throw new IllegalArgumentException("Invalid duration: " + duration);
    }

    @Override
    public String toString() {
        return String.format("PomodoroState{duration=%d, isBreak=%s}", duration, isBreak);
    }
}
