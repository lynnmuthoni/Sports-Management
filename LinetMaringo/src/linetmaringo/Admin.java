/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author LINET
 */
public class Admin {
     private JTextField emailField;
    private JPasswordField passwordField;
    private String email, password;
    private int member_id;
     public void AdminLogin() {
         JFrame adminframe = new JFrame("Admin Login Page");
        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminframe.setLocationRelativeTo(null);
        adminframe.setSize(400, 300);
        adminframe.setLayout(new BorderLayout());

        // Create a colorful background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.CYAN);
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
        emailField = new JTextField(20);
        loginPanel.add(emailField, c);

        // Add password label and field
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        loginPanel.add(new JLabel("Password:"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        passwordField = new JPasswordField(20);
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


        // Add action for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminDash admindash =new AdminDash();
                 String email = emailField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
                System.out.println(email + password);

                if (auth(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
//                    AdminDash admindash =new AdminDash();
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
                }
             
                   
               
            }    
        });   
        
        // Add login panel to the background panel
        backgroundPanel.add(loginPanel);

        // Add the background panel to the frame
        adminframe.add(backgroundPanel, BorderLayout.CENTER);

        adminframe.setVisible(true);
      

//        adminframe.setDefaultCloseOperation(JFrame.DIS (400, 300));  
        
    }
     private boolean auth(String email, String password) {
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");                
            System.out.println("heererrrr"); 
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean auth = resultSet.next();
            conn.close();
            
            AdminDash admindash =new AdminDash();
//            AdminDash admindash =new AdminDash();
            return auth;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MainDash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
     }
    
}
