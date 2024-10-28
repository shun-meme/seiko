package dev.songpola.seiko;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var app = new AppFrame("Seiko");
            app.setVisible(true);
        });
    }
}