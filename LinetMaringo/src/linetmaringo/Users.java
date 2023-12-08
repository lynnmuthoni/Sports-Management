/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

/**
 *
 * @author LINET
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableCellRenderer;


public class Users  {
    public  Users(){
            JFrame frame = new JFrame("All Users Table");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            
                   // Create a DefaultTableModel with column names
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);

            JPanel contentPanel = new JPanel(new BorderLayout());
            // Create a JScrollPane for the table with custom styling
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.getViewport().setBackground(Color.WHITE);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

               // Add the JScrollPane to the content panel
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            
              // Create a "plus sign" button
            JLabel addLabel = new JLabel();
            addLabel.setPreferredSize(new Dimension(60,50));   
            addLabel.setIcon(new ImageIcon(getClass().getResource("/images/addusers.png")));       
            addLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            
            JLabel deleteLabel = new JLabel("Delete");
            deleteLabel.setPreferredSize(new Dimension(60,50));   
            deleteLabel.setIcon(new ImageIcon(getClass().getResource("/images/deregister.png")));
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            buttonPanel.add(addLabel);
            buttonPanel.add(deleteLabel);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);
//            contentPanel.add(buttonsPanel, BorderLayout.WEST);
            
            frame.add(contentPanel);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");                
                System.out.println("heererrrr"); 
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
           System.out.println("heererrrr");
           
//                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM projectmaringo.members;");

                // Add data to the DefaultTableModel
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Gender");
                model.addColumn("DOB");
                model.addColumn("phonenumber");
                model.addColumn("Email");
                model.addColumn("Subcounty");
                model.addColumn("School");
                model.addColumn("Games");
                model.addColumn("Weight");
                model.addColumn("Height");
                model.addColumn("Special needs");
                

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                        resultSet.getInt("idmembers"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getString("DOB"),
                        resultSet.getInt("phonenumber"),
                        resultSet.getString("email"),
                        resultSet.getString("subcounty"),
                        resultSet.getString("school"),
                        resultSet.getString("games"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("height"),
                        resultSet.getString("specialeeds")
                    });
                }

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Style the JTable
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
            table.getTableHeader().setForeground(Color.RED);
            table.setRowHeight(30);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setForeground(Color.WHITE);
            table.setBackground(Color.DARK_GRAY);

            // Center-align table content
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            
            deleteLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            
            {
            DeleteUser del = new DeleteUser();
            }
            
            });
           
            addLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                
                           
              
                     JPanel formPanel = new JPanel(new GridBagLayout());
                     formPanel.setSize(500,800);

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(5, 5, 5, 5);

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    formPanel.add(new JLabel("Add User"), gbc);
//                    gbc.gridx = 1;
//                    JTextField idField = new JTextField(10);
//                    formPanel.add(idField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    formPanel.add(new JLabel("Name:"), gbc);
                    gbc.gridx = 1;
                    JTextField nameField = new JTextField(10);
                    formPanel.add(nameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    formPanel.add(new JLabel("Gender:"), gbc);
                    gbc.gridx = 1;
                    String[] genders = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
                        formPanel.add(genderComboBox, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    formPanel.add(new JLabel("Date of Birth:"), gbc);
                    gbc.gridx = 1;
                    JTextField dobField = new JTextField(10);
                    formPanel.add(dobField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    formPanel.add(new JLabel("Phone Number:"), gbc);
                    gbc.gridx = 1;
                    JTextField phoneField = new JTextField(10);
                    formPanel.add(phoneField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    formPanel.add(new JLabel("Email:"), gbc);
                    gbc.gridx = 1;
                    JTextField EmailField = new JTextField(10);
                    formPanel.add(EmailField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    formPanel.add(new JLabel("Subcounty:"), gbc);
                    gbc.gridx = 1;
                    JTextField subcountyField = new JTextField(10);
                    formPanel.add(subcountyField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    formPanel.add(new JLabel("School:"), gbc);
                    gbc.gridx = 1;
                    JTextField schoolField = new JTextField(10);
                    formPanel.add(schoolField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 8;
                    formPanel.add(new JLabel("Games:"), gbc);
                    gbc.gridx = 1;
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
                    
                    formPanel.add(gamesComboBox, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 9;
                    formPanel.add(new JLabel("Weight:"), gbc);
                    gbc.gridx = 1;
                    JTextField weightField = new JTextField(10);
                    formPanel.add(weightField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    formPanel.add(new JLabel("Height:"), gbc);
                    gbc.gridx = 1;
                    JTextField heightField = new JTextField(10);
                    formPanel.add(heightField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 11;
                    formPanel.add(new JLabel("Special needs:"), gbc);
                    gbc.gridx = 1;
                     String[] specialNeedsOptions = {"Yes", "No"};
        JComboBox<String> specialNeedsComboBox = new JComboBox<>(specialNeedsOptions);
                           formPanel.add(specialNeedsComboBox, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 12;
                    formPanel.add(new JLabel("Password:"), gbc);
                    gbc.gridx = 1;
                    JTextField passwordField = new JTextField(10);
                    formPanel.add(passwordField, gbc);
                    
                    

                    int result = JOptionPane.showConfirmDialog(frame, formPanel, "Add User", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                              try {                    
                 System.out.println("rrrrrrrrr"); 
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                 java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                   String query="insert into members (`name`, `gender`, `DOB`, `phonenumber`, `email`, `subcounty`, `school`, `games`, `weight`, `height`, `specialeeds`, `password`) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                     System.out.println("Register button clicked");
                     
                    String fullName = nameField.getText();
                    String gender = genderComboBox.getSelectedItem().toString();
                    String dob = dobField.getText();
                    String phoneNumber = phoneField.getText();
                    String email = EmailField.getText();
                    String subcounty = subcountyField.getText();
                    String schoolCollege = schoolField.getText();
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
                     System.out.println("Sucessful");
//                     JOptionPane.showMessageDialog(gamesComboBox, "Successful");
                     JOptionPane.showMessageDialog(formPanel,"Record added successfully");
                     preparedStatement.close();
                     conn.close();
                     new Users();
                     
                     nameField.setText("");

                     dobField.setText("");
                      phoneField.setText("");
                     EmailField.setText("");
                     subcountyField.setText("");
                    schoolField.setText("");

                     weightField.setText("");
                     heightField.setText("");
                     passwordField.setText("");

                
                } catch (Exception ex) {
                    
                    java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(formPanel,"Failed to add user");
                }
                      
                    }
            }
            });
               
            
            
            frame.add(contentPanel);
            frame.pack();
            frame.setVisible(true);
        };
    
     public static void main(String[] args) {
        // Create an instance of AdminDash
        SwingUtilities.invokeLater(() -> new Users());
    }
     
    }

    

