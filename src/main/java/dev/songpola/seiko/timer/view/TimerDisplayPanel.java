package dev.songpola.seiko.timer.view;

import javax.swing.*;
import java.awt.*;

import static dev.songpola.seiko.timer.model.PomodoroState.CYCLES_BEFORE_LONG_BREAK;

public class TimerDisplayPanel extends JPanel {

    private final JLabel labelRemainingTime;
    private final JLabel labelCycleCount;

    public TimerDisplayPanel(int seconds, int cycleCount) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        // gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        labelRemainingTime = new JLabel();
        labelRemainingTime.setFont(getFont().deriveFont(48f));
        updateTime(seconds);
        add(labelRemainingTime, gbc);

        gbc.gridy = 1;
        labelCycleCount = new JLabel();
        updateCycle(cycleCount);
        add(labelCycleCount, gbc);
    }

    public void updateTime(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        labelRemainingTime.setText(String.format("%02d:%02d", m, s));
    }

    public void updateCycle(int count) {
        labelCycleCount.setText(count + " / " + CYCLES_BEFORE_LONG_BREAK);
    }
}
