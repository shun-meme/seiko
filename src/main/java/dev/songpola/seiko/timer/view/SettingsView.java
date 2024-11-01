package dev.songpola.seiko.timer.view;

import dev.songpola.seiko.timer.model.PomodoroState;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class SettingsView extends JFrame {
    private JSlider workSlider;
    private JSlider shortBreakSlider;
    private JSlider longBreakSlider;

    public SettingsView(PomodoroState state, Consumer<Integer> updateMainTimer) {
        setTitle("Timer Setting");
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Work timer button and slider
        JButton workButton = new JButton("WORK " + (state.getDuration() / 60) + " min");
        workSlider = createSlider(state.getDuration(), text -> workButton.setText("WORK " + text));
        workButton.addActionListener(e -> updateMainTimer.accept(workSlider.getValue()));
        add(workButton);
        add(workSlider);

        // Short break timer button and slider
        JButton shortBreakButton = new JButton("SHORT BREAK " + (PomodoroState.SHORT_BREAK.getDuration() / 60) + " min");
        shortBreakSlider = createSlider(PomodoroState.SHORT_BREAK.getDuration(), text -> shortBreakButton.setText("SHORT BREAK " + text));
        shortBreakButton.addActionListener(e -> updateMainTimer.accept(shortBreakSlider.getValue()));
        add(shortBreakButton);
        add(shortBreakSlider);

        // Long break timer button and slider
        JButton longBreakButton = new JButton("LONG BREAK " + (PomodoroState.LONG_BREAK.getDuration() / 60) + " min");
        longBreakSlider = createSlider(PomodoroState.LONG_BREAK.getDuration(), text -> longBreakButton.setText("LONG BREAK " + text));
        longBreakButton.addActionListener(e -> updateMainTimer.accept(longBreakSlider.getValue()));
        add(longBreakButton);
        add(longBreakSlider);

        // Back button to return to main interface
        JButton backButton = new JButton("Back to Main");
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
