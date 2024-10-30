package dev.songpola.seiko.timer.model;

public enum PomodoroState {
    WORK(1 * 3, false),           // Work time in seconds
    SHORT_BREAK(1 * 1, true),      // Short break time in seconds
    LONG_BREAK(1 * 2, true);      // Long break time in seconds

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
