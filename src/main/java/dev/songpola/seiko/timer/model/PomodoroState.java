package dev.songpola.seiko.timer.model;

public class PomodoroState {
    public static final PomodoroState WORK = new PomodoroState(25 * 60, false);
    public static final PomodoroState SHORT_BREAK = new PomodoroState(5 * 60, true);
    public static final PomodoroState LONG_BREAK = new PomodoroState(15 * 60, true);

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

    public static PomodoroState fromDuration(int duration) {
        if (duration == WORK.getDuration()) {
            return WORK;
        } else if (duration == SHORT_BREAK.getDuration()) {
            return SHORT_BREAK;
        } else if (duration == LONG_BREAK.getDuration()) {
            return LONG_BREAK;
        } else {
            throw new IllegalArgumentException("Invalid duration: " + duration);
        }
    }
}
