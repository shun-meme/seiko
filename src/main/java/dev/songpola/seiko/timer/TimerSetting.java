package dev.songpola.seiko.timer;

import dev.songpola.seiko.timer.model.PomodoroState;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TimerSetting extends JFrame {
    private JSlider workSlider;
    private JSlider shortBreakSlider;
    private JSlider longBreakSlider;
    private JButton workButton;
    private JButton shortBreakButton;
    private JButton longBreakButton;
    private JButton backButton;

    public TimerSetting(PomodoroState state, Consumer<Integer> updateMainTimer) {
        setTitle("Timer Setting");
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Work timer button and slider
        workButton = new JButton("WORK " + (PomodoroState.WORK.getDuration() / 60) + " min");
        workSlider = createSlider(PomodoroState.WORK.getDuration(), text -> workButton.setText("WORK " + text));
        workButton.addActionListener(e -> updateMainTimer.accept(workSlider.getValue()));
        add(workButton);
        add(workSlider);

        // Short break timer button and slider
        shortBreakButton = new JButton("SHORT BREAK " + (PomodoroState.SHORT_BREAK.getDuration() / 60) + " min");
        shortBreakSlider = createSlider(PomodoroState.SHORT_BREAK.getDuration(), text -> shortBreakButton.setText("SHORT BREAK " + text));
        shortBreakButton.addActionListener(e -> updateMainTimer.accept(shortBreakSlider.getValue()));
        add(shortBreakButton);
        add(shortBreakSlider);

        // Long break timer button and slider
        longBreakButton = new JButton("LONG BREAK " + (PomodoroState.LONG_BREAK.getDuration() / 60) + " min");
        longBreakSlider = createSlider(PomodoroState.LONG_BREAK.getDuration(), text -> longBreakButton.setText("LONG BREAK " + text));
        longBreakButton.addActionListener(e -> updateMainTimer.accept(longBreakSlider.getValue()));
        add(longBreakButton);
        add(longBreakSlider);

        // Back button to return to main interface
        backButton = new JButton("Back to Main") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(148, 0, 211)); // Match reset button color
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded edges
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(150, 40); // Adjust size as needed
            }
        };
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        backButton.addActionListener(e -> dispose());
        add(backButton);
    }

    private JSlider createSlider(int initialDuration, Consumer<String> updateButtonText) {
        JSlider slider = new JSlider(0, 120 * 60, initialDuration); // 0 to 120 minutes in seconds
        slider.setPaintTicks(false); // No tick marks
        slider.setPaintLabels(false); // No labels
        slider.addChangeListener(e -> {
            int totalSeconds = slider.getValue();
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            updateButtonText.accept(minutes + " min " + seconds + " sec");
        });
        return slider;
    }
}
