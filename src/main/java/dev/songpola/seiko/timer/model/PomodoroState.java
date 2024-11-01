package dev.songpola.seiko.timer.model;

public enum PomodoroState {
    WORK(25 * 60, false),           // Work time in seconds
    SHORT_BREAK(5 * 60, true),      // Short break time in seconds
    LONG_BREAK(15 * 60, true);      // Long break time in seconds

    // FOR DEBUGGING PURPOSES
    // WORK(1, false),           // Work time in seconds
    // SHORT_BREAK(2, true),     // Short break time in seconds
    // LONG_BREAK(3, true);      // Long break time in seconds

    public static final int CYCLES_BEFORE_LONG_BREAK = 4;

    private final int duration;
    private final boolean isBreak;

    PomodoroState(int duration, boolean isBreak) {
        this.duration = duration;
        this.isBreak = isBreak;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isBreak() {
        return isBreak;
    }
}
