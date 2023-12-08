/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author LINET
 */
public class ShoppingPage  extends JFrame{
    private JLabel titlelabel,hockeyLabel, shortsLabel, tshirtLabel, wrapperLabel,bloomerLabel,shoeLabel, socksLabel, tracksuitLabel;
    private JSpinner hockeySpinner, shortsSpinner, tshirtSpinner, wrapperSpinner, bloomerSpinner, socksSpinner, shoeSpinner, tracksuitSpinner;
    private JLabel hockeyL, shortsL, tshirtL, wrapperL,bloomerL,shoeL, socksL, tracksuitL;
    private JButton BtnCheckout;

    private List<JSpinner> spinners = new ArrayList<>();
    private List<ShopIt> shopItems = new ArrayList<>();
    private int mem_id;
            
    public ShoppingPage(){
       
        setSize(800,1000);
        setLayout(new MigLayout("wrap","60[]30[]30[]","60"));
         getContentPane().setBackground(new Color(255, 0, 127));

        
        Font labelFont = new Font("Arial", Font.PLAIN, 20);

//        panel.add(new JLabel("Enter ID:")).setFont(labelFont);
        titlelabel=new JLabel("MY SHOP");
        titlelabel.setFont(labelFont);
        titlelabel.setForeground(Color.CYAN);
        
        hockeyLabel = new JLabel();
        hockeyLabel.setPreferredSize(new Dimension(60,60));   
         hockeyLabel.setIcon(new ImageIcon(getClass().getResource("/images/hockey (1).png")));
         
        shortsLabel = new JLabel();
        shortsLabel.setPreferredSize(new Dimension(60,60));
         shortsLabel.setIcon(new ImageIcon(getClass().getResource("/images/shorts.jpeg")));
         
        tshirtLabel = new JLabel();
        tshirtLabel.setPreferredSize(new Dimension(60,60));
         tshirtLabel.setIcon(new ImageIcon(getClass().getResource("/images/tshirt.jpeg")));
       

        wrapperLabel = new JLabel(); 
        wrapperLabel.setPreferredSize(new Dimension(60,60));
        wrapperLabel.setIcon(new ImageIcon(getClass().getResource("/images/wrapper.jpeg")));
        
        bloomerLabel = new JLabel();
        bloomerLabel.setPreferredSize(new Dimension(60,60));
       bloomerLabel.setIcon(new ImageIcon(getClass().getResource("/images/bloomer.jpeg")));
       
        shoeLabel = new JLabel();
        shoeLabel.setPreferredSize(new Dimension(60,60));
        shoeLabel.setIcon(new ImageIcon(getClass().getResource("/images/shoes (1).jpeg")));
        
        socksLabel = new JLabel();
        socksLabel.setPreferredSize(new Dimension(60,60));
        socksLabel.setIcon(new ImageIcon(getClass().getResource("/images/socks.jpeg")));
        
        tracksuitLabel = new JLabel();
        tracksuitLabel.setPreferredSize(new Dimension(60,60));
        tracksuitLabel.setIcon(new ImageIcon(getClass().getResource("/images/track.jpeg")));
        
        hockeyL = new JLabel("Hockey stick @ Ksh.2000");
        shortsL = new JLabel("Shorts @ Ksh.750"); 
        tshirtL = new JLabel("T-shirt @ Ksh. 800");
        wrapperL = new JLabel("Wrapper @ Ksh. 450"); 
        bloomerL = new JLabel("Bloomer @ Ksh. 250");
        shoeL = new JLabel("Shoes @ Ksh. 3000");
        socksL = new JLabel("Socks @ Ksh. 350");
        tracksuitL = new JLabel("Tracksuit @ Ksh. 2000");
        
        hockeySpinner = new JSpinner();
        shortsSpinner = new JSpinner();
        tshirtSpinner = new JSpinner();
        wrapperSpinner = new JSpinner();
        bloomerSpinner = new JSpinner();
        socksSpinner = new JSpinner();
        shoeSpinner= new JSpinner();
        tracksuitSpinner = new JSpinner();
        
        
       spinners.add(hockeySpinner);
    spinners.add(shortsSpinner);
    spinners.add(tshirtSpinner);
    spinners.add(wrapperSpinner);
    spinners.add(bloomerSpinner);
    spinners.add(shoeSpinner);
    spinners.add(socksSpinner);
    spinners.add(tracksuitSpinner);

    shopItems.add(new ShopIt("Hockey Stick", 2000.0));
    shopItems.add(new ShopIt("Shorts", 750.0));
    shopItems.add(new ShopIt("T-shirt", 800.0));
    shopItems.add(new ShopIt("Wrapper", 450.0));
    shopItems.add(new ShopIt("Bloomer", 250.0));
    shopItems.add(new ShopIt("Shoes", 3000.0));
    shopItems.add(new ShopIt("Socks", 350.0));
    shopItems.add(new ShopIt("Tracksuit", 2000.0));
        BtnCheckout = new JButton("Checkout");
        

      BtnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReceiptAndSaveToDatabase();
            }
        });
      
      
      
        add(titlelabel, "wrap");
        
    add(hockeyLabel); add(hockeyL);  add(hockeySpinner, "wrap");
    
    add(shortsLabel); add(shortsL); add(shortsSpinner, "wrap");

    add(tshirtLabel); add(tshirtL); add(tshirtSpinner, "wrap");
    
    add(wrapperLabel); add(wrapperL); add(wrapperSpinner, "wrap");
    
    add(bloomerLabel);add(bloomerL);add(bloomerSpinner, "wrap");
    
    add(shoeLabel);add(shoeL);add(shoeSpinner, "wrap");
    
    add(socksLabel);add(socksL);add(socksSpinner, "wrap");
    
    add(tracksuitLabel);add(tracksuitL);add(tracksuitSpinner, "wrap");
    
    add(BtnCheckout , "wrap");
    
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    
    //   updateStock("hockey stick", spinner.getValue());
    
    }
    
     private void showReceiptAndSaveToDatabase() {
        double totalCost = 0.0;

        // StringBuilder to store the receipt text
        StringBuilder receiptText = new StringBuilder("Receipt\n\n");
        receiptText.append("Date: ").append(getCurrentDate()).append("\n");

        // Iterate through the items to calculate total cost and build the receipt text
        for (int i = 0; i < shopItems.size(); i++) {
            int quantity = ((Integer) spinners.get(i).getValue());
            double itemPrice = shopItems.get(i).getItemPrice();
            double subtotal = quantity * itemPrice;

            if (quantity > 0) {
                // If the quantity is greater than 0, include the item in the receipt
                receiptText.append(shopItems.get(i).getItemName()).append(" x").append(quantity).append(" - KSh ").append(subtotal).append("\n");
            }

            // Add the subtotal to the total cost
            totalCost += subtotal;
        }

        // Apply discount if total cost exceeds KSh 10,000
        double discount = (totalCost > 10000) ? 0.05 * totalCost : 0.0;
        double discountedTotal = totalCost - discount;

        // Display the total cost, discount, and discounted total in the receipt
        receiptText.append("\nTotal Cost: KSh ").append(totalCost);
        receiptText.append("\nDiscount: KSh ").append(discount);
        receiptText.append("\nDiscounted Total: KSh ").append(discountedTotal);

        // Display receipt in a JOptionPane
        int option = JOptionPane.showConfirmDialog(this, receiptText.toString(), "Purchase Receipt", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // Save purchase details to the database
            savePurchaseToDatabase();
        }
    }
    private void savePurchaseToDatabase() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
        String insertQuery = "INSERT INTO projectmaringo.purchase (date, items, quantity, totals) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        // Iterate through the items to save each item's details
        for (int i = 0; i < shopItems.size(); i++) {
            int quantity = ((Integer) spinners.get(i).getValue());
            double itemPrice = shopItems.get(i).getItemPrice();
            double subtotal = quantity * itemPrice;

            // Set parameters for the prepared statement
            preparedStatement.setString(2, shopItems.get(i).getItemName());
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, subtotal);
            preparedStatement.setString(1, getCurrentDate());

            // Execute the SQL statement
            preparedStatement.executeUpdate();

            // Update stock levels in the database
            updateStock(shopItems.get(i).getItemName(), quantity);

//             Check if the current stock level is less than 20%
            if (isStockBelowThreshold(shopItems.get(i).getItemName())) {
                // Alert the admin (replace this with your actual alert mechanism)
                JOptionPane.showMessageDialog(this, "Alert: Stock level for " + shopItems.get(i).getItemName() + " is below 20%!", "Stock Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Close the database connection
        preparedStatement.close();
        conn.close();

        // Display a success message
        JOptionPane.showMessageDialog(this, "Purchase details saved to the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving purchase details to the database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Method to update stock levels in the database
private void updateStock(String item, int itemQty) {
    String query = "UPDATE shop SET currentstock = currentstock - ? WHERE itemname = ?";

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemQty);
        ps.setString(2, item);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Stock levels updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        else {
//            JOptionPane.showMessageDialog(this, "Failed to update stock levels.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating stock levels.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Method to check if the current stock level is below 20%
private boolean isStockBelowThreshold(String item) {
    String query = "SELECT (currentstock / maxstock) * 100 AS stockPercentage FROM shop WHERE itemname = ?";

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, item);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            double stockPercentage = rs.getDouble("stockPercentage");
            if (stockPercentage < 20.0) {
//                JOptionPane.showMessageDialog(this, "Alert: Stock level for " + item + " is below 20%!", "Stock Alert", JOptionPane.WARNING_MESSAGE);
            }
            return stockPercentage < 20.0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
//     public static void main(String[] args) {
//        // Create an instance of AdminDash
//        SwingUtilities.invokeLater(() -> new ShoppingPage());
//    }
     
      

}

class ShopIte {
    private String itemName;
    private double itemPrice;

    public ShopIte(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
    

