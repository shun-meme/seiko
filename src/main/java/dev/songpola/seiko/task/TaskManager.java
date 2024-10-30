package dev.songpola.seiko.task;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskManager extends JPanel {
    private TaskList taskList;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskJList;

    public TaskManager() {
        super(new BorderLayout());
        addTaskList();
        addTaskInput();
        loadTasksFromFile("tasks.csv");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveTasksToFile("tasks.csv")));
    }

    private void addTaskList() {
        taskList = new TaskList();
        taskListModel = new DefaultListModel<>();
        taskJList = new JList<>(taskListModel);
        taskJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                taskList.setSelectedIndex(taskJList.getSelectedIndex());
            }
        });
        add(new JScrollPane(taskJList), BorderLayout.CENTER);
    }

    private void addTaskInput() {
        TaskInput taskInput = new TaskInput(taskList, taskListModel);
        add(taskInput, BorderLayout.SOUTH);
    }

    private void loadTasksFromFile(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> lines = reader.readAll();
            for (String[] line : lines) {
                String description = line[0];
                boolean completed = Boolean.parseBoolean(line[1]);
                Task task = new Task(description, completed);
                taskList.addTask(task);
                taskListModel.addElement(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            saveTasksToFile(filePath);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void saveTasksToFile(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            List<Task> tasks = taskList.getTasks();
            for (Task task : tasks) {
                String[] line = {task.getDescription(), String.valueOf(task.isCompleted())};
                writer.writeNext(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}