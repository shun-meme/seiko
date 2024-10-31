package dev.songpola.seiko.timer.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TimerControlPanel extends JPanel {
    public TimerControlPanel(
        ActionListener onStart,
        ActionListener onStop,
        ActionListener onReset
    ) {
        var startButton = new JButton("Start");
        startButton.addActionListener(onStart);
        add(startButton);

        var stopButton = new JButton("Stop");
        stopButton.addActionListener(onStop);
        add(stopButton);

        var resetButton = new JButton("Reset");
        resetButton.addActionListener(onReset);
        add(resetButton);
    }
}
