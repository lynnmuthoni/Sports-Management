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

 class Stores {
     private JSpinner spinner1 = new JSpinner();
     private static int itemid = 1;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("MY STORE DETAILS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("View Stock", createViewStockPanel());
        tabbedPane.addTab("Update Stock", createUpdateStockPanel());

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 182, 193)); // Light Pink

        // Create a DefaultTableModel with column names
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Create a JScrollPane for the table with custom styling
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add the JScrollPane to the content panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a "plus sign" button
        JLabel addLabel = new JLabel();
        addLabel.setPreferredSize(new Dimension(60, 50));
        addLabel.setIcon(new ImageIcon(Stores.class.getResource("/images/add.png")));
        addLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addLabel);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Database connection and loading data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM projectmaringo.shop;");

            // Add data to the DefaultTableModel
            model.addColumn("ID");
            model.addColumn("NAME");
            model.addColumn("PRICE");
            model.addColumn("MAX STOCK");
            model.addColumn("CURRENT STOCK");

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("itemid"),
                        resultSet.getString("Itemname"),
                        resultSet.getInt("Itemprice"),
                        resultSet.getInt("maxstock"),
                        resultSet.getInt("currentstock"),
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
        table.setForeground(Color.BLACK);

        // Center-align table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Add button functionality
        addLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your code for handling the "Add" button click
                // ...

                // For demonstration, show a simple form
                JPanel formPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);

                gbc.gridx = 0;
                gbc.gridy = 0;
                formPanel.add(new JLabel("Add Items"), gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                formPanel.add(new JLabel("Name:"), gbc);
                gbc.gridx = 1;
                JTextField nameField = new JTextField(10);
                formPanel.add(nameField, gbc);

                // ... (add other form components)

                int result = JOptionPane.showConfirmDialog(panel, formPanel, "Add Item", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    // Get the entered data
                    String name = nameField.getText();

                    // For demonstration, print the entered data
                    System.out.println("Name: " + name);
                }
            }
        });

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