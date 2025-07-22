package org.alanjam.jdbc;

import javax.swing.*;
import java.awt.*;

public record UserInterface (){
    static final String ALLSTUDENTS = "Find All Students";
    static final String FINDBYID = "Find By ID";
    static final String FINDBYDEGREE = "Find By Degree and Major";
    static final int extraWindowWidth = 100;

    public UserInterface{
        createGUI();
    }

    private void createGUI() {
        JFrame screen = new JFrame("Student Finder");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentToPane(screen.getContentPane());

        screen.pack();
        screen.setVisible(true);
    }

    private void addComponentToPane(Container pane) {
        JTabbedPane tabbedPanel = new JTabbedPane();
        JPanel allStudents = new JPanel() {
            public Dimension getPrefferedSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        JButton findAllStudents = new JButton(ALLSTUDENTS);
        JTextArea allStudentResults = new JTextArea("", 10, 20);
        findAllStudents.addActionListener(_ -> allStudentResults.setText(FetchStudents.fetchAll(Main.URL)));
        allStudents.add(findAllStudents);
        allStudents.add(allStudentResults);
        JPanel findByID = new JPanel() {
            public Dimension getPrefferedSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        JTextField findByIdField = new JTextField(FINDBYID, 20);
        JTextArea findByIdResults = new JTextArea("", 10, 20);
        findByIdField.addActionListener(_ -> findByIdResults.setText(FetchStudents.fetchById(Main.URL, findByIdField.getText())));
        findByID.add(findByIdField);
        findByID.add(findByIdResults);
        JPanel findByDegree = new JPanel() {
            public Dimension getPrefferedSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        JTextArea findByDegreeResults = new JTextArea("", 10, 20);
        JTextField findByDegreeField = new JTextField("Degree", 20);
        JTextField findByMajorField = new JTextField("Major", 20);
        findByDegreeField.addActionListener(_ -> findByDegreeResults.setText(FetchStudents.fetchByDegreeAndMajor(Main.URL, findByDegreeField.getText(), findByMajorField.getText())));
        findByMajorField.addActionListener(_ -> findByDegreeResults.setText(FetchStudents.fetchByDegreeAndMajor(Main.URL, findByDegreeField.getText(), findByMajorField.getText())));
        findByDegree.add(findByDegreeField);
        findByDegree.add(findByMajorField);
        findByDegree.add(findByDegreeResults);

        tabbedPanel.addTab(ALLSTUDENTS, allStudents);
        tabbedPanel.addTab(FINDBYID, findByID);
        tabbedPanel.addTab(FINDBYDEGREE, findByDegree);
        pane.add(tabbedPanel);
    }
}
