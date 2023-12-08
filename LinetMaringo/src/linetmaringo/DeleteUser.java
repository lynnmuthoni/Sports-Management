/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteUser {
    private JFrame frame;
    private JPanel mainPanel;
    private IdInputPanel idInputPanel;
    private JTextField nameField;

    public DeleteUser() {
        frame = new JFrame("Delete user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(800,500);

        mainPanel = new JPanel(new BorderLayout());
        idInputPanel = new IdInputPanel();
        nameField = new JTextField(40);
        nameField.setSize(300, 50);

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the Check button is clicked, fetch the name from the database
                int id = idInputPanel.getId();
                String name = fetchNameFromDatabase(id);

                // Display the name in the nameField
                nameField.setText(name);
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the Delete button is clicked, delete the record with the entered ID
                int id = idInputPanel.getId();
                deleteRecordFromDatabase(id);

                // Clear the nameField
                nameField.setText("");
            }
        });
        mainPanel.setSize(500,400);
        mainPanel.add(idInputPanel.getPanel(), BorderLayout.NORTH);
        mainPanel.add(checkButton, BorderLayout.WEST);
        mainPanel.add(deleteButton, BorderLayout.EAST);
        mainPanel.add(nameField, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String fetchNameFromDatabase(int id) {
        String name = "";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");                
                System.out.println("heererrrr"); 
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
           System.out.println("heererrrr");
           
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
            String query = "SELECT name FROM members WHERE idmembers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void deleteRecordFromDatabase(int id) {
        // TODO: Implement database connection and query to delete the record
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");                
                System.out.println("heererrrr"); 
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
           System.out.println("heererrrr");
           
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
            String query = "DELETE FROM members WHERE idmembers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(DeleteUser::new);
//    }
}
  class IdInputPanel {
    private JPanel panel;
    private JTextField idField;

    public IdInputPanel() {
        panel = new JPanel(new FlowLayout());
        panel.setSize(300,200);
        idField = new JTextField(10);

        panel.add(new JLabel("Enter ID:"));
        panel.add(idField);
    }

    public int getId() {
        try {
            return Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            return -1; // Return a default value or handle the error as needed
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
