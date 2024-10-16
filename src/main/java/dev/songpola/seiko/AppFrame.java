package dev.songpola.seiko;

import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.*;

public class AppFrame extends JFrame {
    public AppFrame(String title) {
        super(title);
        setup();
        add(new JLabel("Hello, Seiko!"));
    }

    private void setup() {
        // Setup theme
        IntelliJTheme.setup(
            Main.class.getClassLoader().getResourceAsStream(
                "themes/latte.theme.json"
            )
        );
        // Exit on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        setSize(640, 480); // pack();
        // Center the window
        setLocationRelativeTo(null); // setLocationByPlatform(true);
    }
}
