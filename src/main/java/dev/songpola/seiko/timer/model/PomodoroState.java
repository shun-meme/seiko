package dev.songpola.seiko.timer.model;

public class PomodoroState {
    public static final PomodoroState WORK = new PomodoroState(25 * 60, false);           // Work time in seconds
    public static final PomodoroState SHORT_BREAK = new PomodoroState(5 * 60, true);      // Short break time in seconds
    public static final PomodoroState LONG_BREAK = new PomodoroState(15 * 60, true);      // Long break time in seconds

    private int duration;
    private final boolean isBreak;

    private PomodoroState(int duration, boolean isBreak) {
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
        this.duration = duration;
    }
}
