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


public class ViewReports  {
    public  ViewReports(){
            JFrame frame = new JFrame("Your Reports");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            
                   // Create a DefaultTableModel with column names
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);

            JPanel contentPanel = new JPanel(new BorderLayout());
            // Create a JScrollPane for the table with custom styling
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.getViewport().setBackground(Color.MAGENTA);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

               // Add the JScrollPane to the content panel
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            
              // Create a "plus sign" button
            JLabel addLabel = new JLabel();
            addLabel.setPreferredSize(new Dimension(60,50));   
            addLabel.setIcon(new ImageIcon(getClass().getResource("/images/addgames.png")));       
            addLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(addLabel);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            frame.add(contentPanel);

                  try {
                Class.forName("com.mysql.cj.jdbc.Driver");           
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                System.out.println("heererrrr");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM projectmaringo.damaged WHERE memberid = 3;");

                // Add data to the DefaultTableModel
                model.addColumn("ID");
                model.addColumn("NAME");
                model.addColumn("DATE");
                model.addColumn("ITEM");
                model.addColumn("QUANTITY");
                model.addColumn("TOTALS");
                
               

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                        resultSet.getInt("iddamaged"),
                        resultSet.getString("date"),
                         resultSet.getString("item"),
                        resultSet.getInt("quantity"),
                         resultSet.getDouble("totals")
//                        resultSet.getString("sportsname"),

                        
                    });
                }

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Style the JTable
            table.getTableHeader().setFont(new Font("TimesNewRoman", Font.BOLD, 16));
            table.getTableHeader().setForeground(Color.BLUE);
            table.setRowHeight(30);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setForeground(Color.getHSBColor(231, 84, 128));
            table.setBackground(Color.black);

            // Center-align table content
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            
            addLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                     JPanel formPanel = new JPanel(new GridBagLayout());

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(5, 5, 5, 5);

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    formPanel.add(new JLabel("Add Sports"), gbc);
//                    gbc.gridx = 1;
//                    JTextField idField = new JTextField(10);
//                    formPanel.add(idField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    formPanel.add(new JLabel("Name:"), gbc);
                    gbc.gridx = 1;
                    JTextField nameField = new JTextField(10);
                    formPanel.add(nameField, gbc);

                   

                    int result = JOptionPane.showConfirmDialog(frame, formPanel, "Add Sport", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                            try {                    
              
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                 java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                   String query="insert into `projectmaringo`.`sports` (`sportsname`) values(?)";
//                   DELETE FROM `projectmaringo`.`sports` WHERE (`idsports` = '15');

                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                     System.out.println("Register button clicked");
                     
                    String fullName = nameField.getText();
                   
                    preparedStatement.setString(1, fullName);
                    
                     int i= preparedStatement.executeUpdate();
//                     JOptionPane.showMessageDialog(registerButton, i+ "Record added successfully");
                     preparedStatement.close();
                     conn.close();
                     
                    nameField.setText("");

                    
                
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
        
        SwingUtilities.invokeLater(() -> new ViewReports());
    }
     
    }

    

