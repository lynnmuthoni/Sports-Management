package linetmaringo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import net.miginfocom.swing.MigLayout;

 public class events {
     private JTabbedPane tabbedPane;
    private JPanel addEventsPanel;
    private JTextField sportField, currentSchoolField, competingSchoolField, dateField, studentsField, locationField;
    private JButton addEventButton;
    private JTable eventsTable;
    private DefaultTableModel tableModel;

    private JPanel displayAmountPanel;
    private JTable amountTable;
    private DefaultTableModel amountTableModel;
//     private JSpinner spinner1 = new JSpinner();
//     private static int itemid = 1;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("EVENTS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Events", createViewStockPanel());
//        tabbedPane.addTab("View Events", createUpdateStockPanel());

        // Set custom colors
        tabbedPane.setBackground(new Color(255, 182, 193)); // Light Pink
        tabbedPane.setForeground(new Color(0, 0, 0)); // White

        frame.getContentPane().setBackground(new Color(255, 255, 255)); // Black
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createViewStockPanel() {
       JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

       JTextField sportField = new JTextField();
      JTextField  currentSchoolField = new JTextField();
      JTextField  competingSchoolField = new JTextField();
       JTextField dateField = new JTextField();
       JTextField studentsField = new JTextField();
       JTextField locationField = new JTextField();

       JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                         try {                    
                 System.out.println("rrrrrrrrr"); 
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                 java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                   String query="insert into events (`idevents`, `currentschool`, `competingschool`, `date`, no of students`, `location`) values(?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                     System.out.println("Register button clicked");
                     
                   // Get data from the fields
        String sport = sportField.getText();
        String currentSchool = currentSchoolField.getText();
        String competingSchool = competingSchoolField.getText();
        String date = dateField.getText();
        String students = studentsField.getText();
        String location = locationField.getText();
        
        
                    preparedStatement.setString(1, sport);
                    preparedStatement.setString(2, currentSchool);
                    preparedStatement.setString(3, competingSchool);
                    preparedStatement.setString(4, date);
                    preparedStatement.setString(5, students);
                    preparedStatement.setString(6, location);
                    
                     
                     int i= preparedStatement.executeUpdate();
                     System.out.println("Sucessful");
//                     JOptionPane.showMessageDialog(gamesComboBox, "Successful");
//                     JOptionPane.showMessageDialog(createViewStockPanel,"Event added successfully");
                     preparedStatement.close();
                     conn.close();
                     new events();
                     
                      // Clear the input fields
                sportField.setText("");
                currentSchoolField.setText("");
                competingSchoolField.setText("");
                dateField.setText("");
                studentsField.setText("");
                locationField.setText("");
                
                } catch (Exception ex) {
                    
                    java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(createViewStockPanel,"Failed to add event");
                }
                
                
                
                
            }
        });

        panel.add(new JLabel("Sport:"));
        panel.add(sportField);
        panel.add(new JLabel("Current School:"));
        panel.add(currentSchoolField);
        panel.add(new JLabel("Competing School:"));
        panel.add(competingSchoolField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("No. of Students:"));
        panel.add(studentsField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(addEventButton);

        return panel;
    }
   

  private static JPanel createUpdateStockPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 4", "[grow][50px][grow][grow]"));
        panel.setBackground(new Color(255, 182, 193)); // Light Pink

        // Load data into the panel with MigLayout
        loadDataWithMigLayout(panel);

        return panel;
    }

    private static void loadData(DefaultTableModel model) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT itemid, Itemname, currentstock FROM projectmaringo.shop;");

            while (resultSet.next()) {
                int itemId = resultSet.getInt("itemid");
                String itemName = resultSet.getString("Itemname");
                int currentStock = resultSet.getInt("currentstock");

                model.addRow(new Object[]{itemId, itemName, currentStock});
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadDataWithMigLayout(JPanel panel) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT itemid, Itemname, currentstock FROM projectmaringo.shop;");

            while (resultSet.next()) {
                int itemId = resultSet.getInt("itemid");
                String itemName = resultSet.getString("Itemname");
                int currentStock = resultSet.getInt("currentstock");

                JLabel nameLabel = new JLabel(itemName);
                panel.add(nameLabel);

                JTextField stockTextField = new JTextField("Current Stock: " + currentStock);
                stockTextField.setEditable(false);
                panel.add(stockTextField);

                JSpinner spinner = new JSpinner();
                panel.add(spinner);

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(e -> {
                    try {
                        int updatedStock = currentStock + (int) spinner.getValue();
                        updateStockInDatabase(itemId, updatedStock);

                        // Display success message
                        JOptionPane.showMessageDialog(panel, "Update successful for " + itemName + ". New stock: " + updatedStock);

                        // Update the text field with the new stock value
                        stockTextField.setText("Current Stock: " + updatedStock);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                panel.add(updateButton, "wrap");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStockInDatabase(int itemId, int updatedStock) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE projectmaringo.shop SET currentstock = ? WHERE itemid = ?");
            preparedStatement.setInt(1, updatedStock);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}