package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;

import javax.swing.*;
import java.awt.*;

public class TimerController extends JPanel {
    private PomodoroTimer timer;

    public TimerController() {
        timer = new PomodoroTimer();
        setup();
    }

    private void setup() {
        setLayout(new BorderLayout());
        add(timer, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pomodoro Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TimerController());
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}
