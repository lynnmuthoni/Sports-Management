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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableCellRenderer;


public class patron  {
    public  patron(){
            JFrame frame = new JFrame("patron Table");
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
                ResultSet resultSet = statement.executeQuery("SELECT * FROM projectmaringo.patrons;");

                // Add data to the DefaultTableModel
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Sport");
                model.addColumn("Gender");
                model.addColumn("Email");
                model.addColumn("phonenumber");
               

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                        resultSet.getInt("idpatrons"),
                        resultSet.getString("name"),
                        resultSet.getString("sport"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getInt("phonenumber"),
                 
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
            DeletePatron deletepatron = new DeletePatron();
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
                    gbc.gridy = 8;
                    formPanel.add(new JLabel("Sport:"), gbc);
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
                    gbc.gridy = 2;
                    formPanel.add(new JLabel("Gender:"), gbc);
                    gbc.gridx = 1;
                    String[] genders = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
                        formPanel.add(genderComboBox, gbc);

                     gbc.gridx = 0;
                    gbc.gridy = 5;
                    formPanel.add(new JLabel("Email:"), gbc);
                    gbc.gridx = 1;
                    JTextField EmailField = new JTextField(10);
                    formPanel.add(EmailField, gbc);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    formPanel.add(new JLabel("Phone Number:"), gbc);
                    gbc.gridx = 1;
                    JTextField phoneField = new JTextField(10);
                    formPanel.add(phoneField, gbc);
                    
                   
                    

                    int result = JOptionPane.showConfirmDialog(frame, formPanel, "Add User", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                              try {                    
                 System.out.println("rrrrrrrrr"); 
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                 java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                   String query="insert into patrons (`name`, `sport`,`gender`, `email`, `phonenumber`) values(?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                     System.out.println("Register button clicked");
                     
                    String fullName = nameField.getText();
                    String gender = genderComboBox.getSelectedItem().toString();
                  
                    String phoneNumber = phoneField.getText();
                    String email = EmailField.getText();
                    
                    // Handle registration logic here
                  String selectedGame = gamesComboBox.getSelectedItem() != null ? gamesComboBox.getSelectedItem().toString() : "No game selected";

                  // Rest of the registrat
                 
                    preparedStatement.setString(1, fullName);
                    preparedStatement.setString(2, selectedGame);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, phoneNumber);
                    
              
                     
                     int i= preparedStatement.executeUpdate();
                     System.out.println("Sucessful");
//                     JOptionPane.showMessageDialog(gamesComboBox, "Successful");
//                     JOptionPane.showMessageDialog(this, i+ "Record added successfully");
                     preparedStatement.close();
                     conn.close();
                     
                     nameField.setText("");                     
                      phoneField.setText("");
                     EmailField.setText("");


                
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        SwingUtilities.invokeLater(() -> new patron());
    }
     
    }
 class DeletePatron {
    private JFrame frame;
    private JPanel mainPanel;
    private PIdInputPanel idInputPanel;
    private JTextField nameField;

    public DeletePatron() {
        frame = new JFrame("Delete user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(800,500);

        mainPanel = new JPanel(new BorderLayout());
        idInputPanel = new PIdInputPanel();
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
               
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");       
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
            String query = "DELETE FROM patrons WHERE idmembers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(DeletePatron::new);
//    }
}
  class PIdInputPanel {
    private JPanel panel;
    private JTextField idField;

    public PIdInputPanel() {
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


    

