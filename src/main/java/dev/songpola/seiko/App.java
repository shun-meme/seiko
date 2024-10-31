package dev.songpola.seiko;

import com.formdev.flatlaf.IntelliJTheme;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dev.songpola.seiko.task.TaskManagerController;
import dev.songpola.seiko.task.model.TaskListModel;
import dev.songpola.seiko.task.model.TaskModel;
import dev.songpola.seiko.timer.PomodoroTimerController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class App extends JFrame {
    private static final String USER_HOME_PATH = System.getProperty("user.home");
    private static final String TASK_DATA_FILE_NAME = "tasks.csv";

    private final TaskListModel taskListModel = new TaskListModel();

    public App(String title) {
        super(title);
        initState();
        setup();
        setupTabs();
    }

    private void initState() {
        var path = Path.of(USER_HOME_PATH, TASK_DATA_FILE_NAME);
        loadTasksFromFile(path);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // Save tasks to file on close
                saveTasksToFile(path);
                e.getWindow().dispose();
            }
        });
    }

    private void loadTasksFromFile(Path path) {
        try (var reader = new CSVReader(new FileReader(path.toString()))) {
            var lines = reader.readAll();
            for (var line : lines) {
                String title = line[0];
                boolean completed = Boolean.parseBoolean(line[1]);
                int timeSpent = Integer.parseInt(line[2]);
                taskListModel.addTask(new TaskModel(title, completed, timeSpent));
            }
        } catch (FileNotFoundException e) {
            System.err.println("No existing tasks file found. Creating new tasks file: " + path);
            saveTasksToFile(path);
        } catch (IOException | CsvException e) {
            // Unable to read tasks from file
            // TODO: Show dialog to user
        }
    }

    private void saveTasksToFile(Path path) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
            var tasks = taskListModel.toArray();
            for (Object task : tasks) {
                var taskModel = (TaskModel) task;
                String[] line = {
                    taskModel.getTitle(),
                    String.valueOf(taskModel.isCompleted()),
                    String.valueOf(taskModel.getTimeSpent())
                };
                writer.writeNext(line);
            }
        } catch (IOException e) {
            // Unable to write tasks to file
            // TODO: Show dialog to user
        }
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

    private void setupTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Tasks", new TaskManagerController(taskListModel));
        tabs.addTab("Timer", new PomodoroTimerController(taskListModel));
        add(tabs);
    }
}
