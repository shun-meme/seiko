package dev.songpola.seiko;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var app = new App("Seiko");
            app.setVisible(true);
        });
    }
}