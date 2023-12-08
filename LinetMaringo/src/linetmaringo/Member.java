/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author LINET
 */
public class Member {
    
    public void MemberRegistration(){
        
        JFrame frame = new JFrame("Member Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(1000, 800);

        JPanel panel = new JPanel(new GridLayout(17, 2, 8, 8));

        // Create and add a title label
        JLabel titleLabel = new JLabel("Member Registration");
        JLabel blank =new JLabel("");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titleLabel);
        panel.add(blank);


        // Create and add labels and input fields for each field
        panel.add(new JLabel("Full Name:"));
        JTextField fullNameField = new JTextField();
        panel.add(fullNameField);

        panel.add(new JLabel("Gender:"));
        String[] genders = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        panel.add(genderComboBox);

        panel.add(new JLabel("Date of Birth:"));
        JTextField dobField = new JTextField();
        panel.add(dobField);

        panel.add(new JLabel("Phone Number:"));
        JTextField phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Subcounty:"));
        JTextField subcountyField = new JTextField();
        panel.add(subcountyField);

        panel.add(new JLabel("School/College:"));
        JTextField schoolCollegeField = new JTextField();
        panel.add(schoolCollegeField);

       panel.add(new JLabel("Games of Interest:"));
        String[] games = {"Swimming", "Hockey", "Lawn Tennis", "Table Tennis", "Dart", "Badminton",
                "Volleyball", "Basketball", "Netball", "Football", "Baseball", "Rugby", "Chess", "Draft"};
        JComboBox<String> gamesComboBox = new JComboBox<>(games);
        gamesComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value != null) {
                    value = " " + value.toString(); // Add a space for better alignment
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        gamesComboBox.setSelectedIndex(-1); // Allow no initial selection
        panel.add(gamesComboBox);

        panel.add(new JLabel("Weight (kg):"));
        JTextField weightField = new JTextField();
        panel.add(weightField);

        panel.add(new JLabel("Height (cm):"));
        JTextField heightField = new JTextField();
        panel.add(heightField);

        panel.add(new JLabel("Special Needs:"));
        String[] specialNeedsOptions = {"Yes", "No"};
        JComboBox<String> specialNeedsComboBox = new JComboBox<>(specialNeedsOptions);
        panel.add(specialNeedsComboBox);
        
         panel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField();
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try {                    
                 System.out.println("rrrrrrrrr"); 
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                 java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                   String query="insert into members (`name`, `gender`, `DOB`, `phonenumber`, `email`, `subcounty`, `school`, `games`, `weight`, `height`, `specialeeds`, `password`) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                     System.out.println("Register button clicked");
                     
                    String fullName = fullNameField.getText();
                    String gender = genderComboBox.getSelectedItem().toString();
                    String dob = dobField.getText();
                    String phoneNumber = phoneNumberField.getText();
                    String email = emailField.getText();
                    String subcounty = subcountyField.getText();
                    String schoolCollege = schoolCollegeField.getText();
                    // Handle registration logic here
                  String selectedGame = gamesComboBox.getSelectedItem() != null ? gamesComboBox.getSelectedItem().toString() : "No game selected";

                  // Rest of the registrat
                  String weight = weightField.getText();
                  String height = heightField.getText();
                  String password= passwordField.getText();
                  String specialNeeds = specialNeedsComboBox.getSelectedItem().toString();

                    preparedStatement.setString(1, fullName);
                    preparedStatement.setString(2, gender);
                    preparedStatement.setString(3, dob);
                    preparedStatement.setString(4, phoneNumber);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, subcounty);
                    preparedStatement.setString(7, schoolCollege);
                    preparedStatement.setString(8, selectedGame);
                    preparedStatement.setString(9, weight);
                    preparedStatement.setString(10, height);
                    preparedStatement.setString(11, specialNeeds);
                     preparedStatement.setString(12, password);
                     
                     int i= preparedStatement.executeUpdate();
                     JOptionPane.showMessageDialog(registerButton, i+ "Record added successfully");
                     preparedStatement.close();
                     conn.close();
                     
                     fullNameField.setText("");

                     dobField.setText("");
                      phoneNumberField.setText("");
                     emailField.setText("");
                     subcountyField.setText("");
                    schoolCollegeField.setText("");

                     weightField.setText("");
                     heightField.setText("");
                     passwordField.setText("");

                
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }
        
    
    
     public void MemberLogin() {
         JFrame adminframe = new JFrame("Member Login Page");
        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminframe.setLocationRelativeTo(null);
//        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminframe.setSize(400, 300);
        adminframe.setLayout(new BorderLayout());

        // Create a colorful background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create a panel for the login form
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add email label and field
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 0, 10);
        loginPanel.add(new JLabel("Email:"), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextField emailField = new JTextField(20);
        loginPanel.add(emailField, c);

        // Add password label and field
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        loginPanel.add(new JLabel("Password:"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, c);

        // Add login button
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton, c);

        // Add "Already have an account" link
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        JLabel signUpLabel = new JLabel("<html><u>Don't have an account register now</u></html>");
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.setForeground(Color.RED);
        loginPanel.add(signUpLabel, c);

        // Add action for the sign-up link
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MemberRegistration();
            }
        });

        // Add action for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  MemberDash memberdash= new MemberDash();
                
                 String email = emailField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
                System.out.println(email + password);

                if (authenticateUser(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful.");
                }
             
                
            }
        });
        
        ///////////////
        // Add login panel to the background panel
        backgroundPanel.add(loginPanel);

        // Add the background panel to the frame
        adminframe.add(backgroundPanel, BorderLayout.CENTER);

        adminframe.setVisible(true);   

//        adminFrame.setDefaultCloseOperation(JFrame.DIS 400, 300);

       
        
    }
      private boolean authenticateUser(String email, String password) {
        try {
            System.out.println("rrrrrrrrr"); 
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("heererrrr"); 
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
            String query = "SELECT * FROM members WHERE email = ? AND password = ?";
            String queryID = "SELECT idMembers WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean authenticated = resultSet.next();
            
            PreparedStatement preparedStatement2 = conn.prepareStatement(queryID);
            preparedStatement2.setString(1, email);
            preparedStatement2.setString(2, password);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            conn.close();
          
            
            
            
            return authenticated;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
     }
//       public int getMemberID(String email, String password) {
//        try {
//            System.out.println("rrrrrrrrr"); 
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("heererrrr"); 
//            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
//            String queryID = "SELECT idmembers WHERE email = ? AND password = ?";
//
//            
//            PreparedStatement preparedStatement2 = conn.prepareStatement(queryID);
//            preparedStatement2.setString(1, email);
//            preparedStatement2.setString(2, password);
//            ResultSet rset = preparedStatement2.executeQuery();
//            conn.close();
////            MemberDash();
//            int member_id =0;
//            if (rset.next()){
//                member_id = rset.getInt("idmembers");
//            }
//            
//            
//            return member_id;
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return 0;
//        } 
//        catch (ClassNotFoundException ex) {
//            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0 ;
//     }
//     
      
    
}
