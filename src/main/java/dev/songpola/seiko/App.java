    package dev.songpola.seiko;

    import com.formdev.flatlaf.IntelliJTheme;
    import dev.songpola.seiko.task.TaskManager;
    import dev.songpola.seiko.timer.PomodoroTimer;

    import javax.swing.*;

    public class App extends JFrame {
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
            setSize(640, 480);      // pack();
            setLocationRelativeTo(null);        // setLocationByPlatform(true);
        }

        private void addTabs() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("Tasks", new TaskManager());
            tabs.addTab("Timer", new PomodoroTimer());
            tabs.setSelectedIndex(1);
            add(tabs);
        }
    }
