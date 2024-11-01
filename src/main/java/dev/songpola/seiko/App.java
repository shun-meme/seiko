package dev.songpola.seiko;

import com.formdev.flatlaf.IntelliJTheme;
import dev.songpola.seiko.task.TaskManagerController;
import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.timer.TimerController;

import javax.swing.*;

public class App extends JFrame {
    private final TaskListModel taskListModel = new TaskListModel();

    public App(String title) {
        super(title);
        setup();
        addTabs();
    }

    private void setup() {
        IntelliJTheme.setup(
                Main.class.getClassLoader().getResourceAsStream(
                        "themes/latte.theme.json"
                )
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    private void addTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Tasks", new TaskManagerController(taskListModel));
        tabs.addTab("Timer", new TimerController()); // Create a TimerController without arguments
        tabs.setSelectedIndex(1);
        add(tabs);
    }
}
