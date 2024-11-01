package dev.songpola.seiko.timer.view;

import javax.swing.*;

public class TimerDisplay extends JLabel {
    public TimerDisplay(int seconds) {
        setHorizontalAlignment(JLabel.CENTER);
        update(seconds);
    }

    public void update(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        setText(String.format("%02d:%02d", m, s));
    }
}