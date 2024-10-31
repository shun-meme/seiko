package dev.songpola.seiko.timer.view;

import javax.swing.*;

public class TimerDisplayPanel extends JLabel {
    public TimerDisplayPanel(int seconds) {
        setHorizontalAlignment(JLabel.CENTER);
        setFont(getFont().deriveFont(48f));
        update(seconds);
    }

    public void update(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        setText(String.format("%02d:%02d", m, s));
    }
}
