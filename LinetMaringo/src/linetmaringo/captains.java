package linetmaringo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class captains extends JFrame {

    private JList<String> membersList;
    private JButton chooseCaptainButton;
    private JLabel currentCaptainLabel;
    private String patrongame;

    public captains() {
        

        setTitle("Choose Captain");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components initialization
        membersList = new JList<>();
        chooseCaptainButton = new JButton("Choose Captain");
        currentCaptainLabel = new JLabel("The current captain is: ");

        // Layout setup
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(membersList);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(chooseCaptainButton, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(currentCaptainLabel, BorderLayout.SOUTH);

        // Populate membersList with data from the database
        populateMembersList(patrongame);

        // Event handling
        chooseCaptainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChooseCaptainFrame();
            }
        });

        setVisible(true);
    }

   

    private void populateMembersList(String patrongame) {
        List<String> memberDataList = new ArrayList<>();
        String gametosearch= "Dart";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1")) {
            String query = "SELECT idmembers, name FROM members WHERE games = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, gametosearch);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int memberId = resultSet.getInt("idmembers");
                        String memberName = resultSet.getString("name");
                        memberDataList.add("ID: " + memberId + " - " + memberName);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(captains.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] memberDataArray = memberDataList.toArray(new String[0]);
        membersList.setListData(memberDataArray);
    }

    private void showChooseCaptainFrame() {
        JFrame chooseCaptainFrame = new JFrame("Choose Captain");
        chooseCaptainFrame.setSize(300, 150);
        chooseCaptainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chooseCaptainFrame.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameTextField = new JTextField(15);
        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredName = nameTextField.getText();
                currentCaptainLabel.setText("The current captain is: " + enteredName);
                chooseCaptainFrame.dispose();
            }
        });

        chooseCaptainFrame.add(nameLabel);
        chooseCaptainFrame.add(nameTextField);
        chooseCaptainFrame.add(confirmButton);

        chooseCaptainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new captains());
    }
}
