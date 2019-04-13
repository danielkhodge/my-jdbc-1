package com.hodge.daniel.jdbctutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class PersonForm {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JFormattedTextField dateOfBirthField;
    private JEditorPane commentField;
    private JButton saveButton;
    private JPanel mainPanel;
    private JButton selectAllButton;
    private JButton findButton;
    private AtomicLong idGenerator = new AtomicLong(Math.round(Math.random() * 1000000000));

    public PersonForm() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("this is a test");
                createPerson();
            }
        });
        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAll();
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                find();
            }
        });
    }

    public void createPerson() {
        try {
            Person p = new Person();
            p.setFirstName(firstNameField.getText());
            p.setLastName(lastNameField.getText());
            p.setComment(commentField.getText());
            p.setDob(new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirthField.getText()));
            p.setId(BigInteger.valueOf(idGenerator.getAndIncrement()));
            new PersonRepository().savePerson(p);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel,
                    "Please provide a date in the following format 'mm/dd/yyyy",
                    "Error Parsing Date...",
                    TrayIcon.MessageType.ERROR.ordinal());
        }
    }

    public void selectAll() {
        new PersonRepository().printPerson();
    }

    public void find() {
        new PersonRepository().find(this.firstNameField.getText());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My first Swing app");
        frame.setContentPane(new PersonForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        dateOfBirthField = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));
    }
}
