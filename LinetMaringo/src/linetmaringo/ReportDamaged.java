
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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author LINET
 */
public class ReportDamaged  extends JFrame{
    private JLabel memIdLabel ,titlelabel, footballLabel, netballLabel, basketballLabel, batLabel,raquetLabel,flyingdiscLabel, padsLabel, netLabel,mouthguardLabel;
    private JSpinner footballSpinner, netballSpinner, basketballSpinner, batSpinner, raquetSpinner, flyingdiscSpinner, padsSpinner, netSpinner, mouthguardSpinner;
    private JLabel footballL, netballL, basketballL, batL,raquetL,flyingdiscL, padsL, netL, mouthguardL;
    private JButton BtnReport;
    private JTextField memberIdfield;
//      private List<ShopItem> shopItems;
//    private List<JSpinner> spinners;

    private List<JSpinner> spinners = new ArrayList<>();
    private List<ShopIt> shopItems = new ArrayList<>();
    private int mem_id;
            
    public ReportDamaged(){
        ;
        setSize(800,1000);
        setLayout(new MigLayout("wrap","30[]30[]30[]","60"));
         getContentPane().setBackground(new Color(0, 102, 204));

        
        memIdLabel= new JLabel("Enter MemberId");
        memberIdfield= new JTextField(10);
        
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

//        panel.add(new JLabel("Enter ID:")).setFont(labelFont);
        titlelabel=new JLabel("REPORT DAMAGES");
        titlelabel.setFont(labelFont);
        titlelabel.setForeground(Color.white);
        
        
        
        footballLabel = new JLabel();
        footballLabel.setPreferredSize(new Dimension(60,60));   
         footballLabel.setIcon(new ImageIcon(getClass().getResource("/images/football.jpeg")));
         
        netballLabel = new JLabel();
        netballLabel.setPreferredSize(new Dimension(60,60));
         netballLabel.setIcon(new ImageIcon(getClass().getResource("/images/netball.jpeg")));
         
        basketballLabel = new JLabel();
        basketballLabel.setPreferredSize(new Dimension(60,60));
         basketballLabel.setIcon(new ImageIcon(getClass().getResource("/images/basketball.jpeg")));
       

        batLabel = new JLabel(); 
        batLabel.setPreferredSize(new Dimension(60,60));
        batLabel.setIcon(new ImageIcon(getClass().getResource("/images/bat.jpeg")));
        
        raquetLabel = new JLabel();
        raquetLabel.setPreferredSize(new Dimension(60,60));
       raquetLabel.setIcon(new ImageIcon(getClass().getResource("/images/raquet.jpeg")));
       
        flyingdiscLabel = new JLabel();
        flyingdiscLabel.setPreferredSize(new Dimension(60,60));
        flyingdiscLabel.setIcon(new ImageIcon(getClass().getResource("/images/flyingdisc.jpeg")));
        
        padsLabel = new JLabel();
        padsLabel.setPreferredSize(new Dimension(60,60));
        padsLabel.setIcon(new ImageIcon(getClass().getResource("/images/pads.jpeg")));
        
        netLabel = new JLabel();
        netLabel.setPreferredSize(new Dimension(60,60));
        netLabel.setIcon(new ImageIcon(getClass().getResource("/images/net.jpeg")));
        
         mouthguardLabel = new JLabel();
        mouthguardLabel.setPreferredSize(new Dimension(60,60));
        mouthguardLabel.setIcon(new ImageIcon(getClass().getResource("/images/mouth.jpeg")));
        
        footballL = new JLabel("football @ Ksh.2000");
        netballL = new JLabel("netball @ Ksh.1000"); 
        basketballL = new JLabel("basketball @ Ksh.1750"); 
        batL = new JLabel("bat @ Ksh. 800");
        raquetL = new JLabel("raquet @ Ksh. 850"); 
        flyingdiscL = new JLabel("flyingdisc @ Ksh. 250");
        padsL = new JLabel("pads @ Ksh. 2000");
        netL = new JLabel("net @ Ksh. 1000");
       mouthguardL = new JLabel("mouthguard @ Ksh. 2000");
        
        footballSpinner = new JSpinner();
        netballSpinner = new JSpinner();
       basketballSpinner = new JSpinner();
        batSpinner = new JSpinner();
        raquetSpinner = new JSpinner();
        flyingdiscSpinner = new JSpinner();
       padsSpinner= new JSpinner();
         netSpinner = new JSpinner();
        mouthguardSpinner = new JSpinner();
        
       spinners.add(footballSpinner);
    spinners.add(netballSpinner);
    spinners.add(basketballSpinner);
    spinners.add(batSpinner);
    spinners.add(raquetSpinner);
    spinners.add(flyingdiscSpinner);
    spinners.add(padsSpinner);
    spinners.add(netSpinner);
     spinners.add(mouthguardSpinner);

    shopItems.add(new ShopIt("football", 2000.0));
    shopItems.add(new ShopIt("netball", 1000.0));
    shopItems.add(new ShopIt("basketball", 1750.0));
    shopItems.add(new ShopIt("bat", 800.0));
    shopItems.add(new ShopIt("raquet", 850.0));
    shopItems.add(new ShopIt("flyingdisc", 250.0));
    shopItems.add(new ShopIt("pads", 2000.0));
    shopItems.add(new ShopIt("net", 1000.0));
    shopItems.add(new ShopIt("mouthguard", 2000.0));
        BtnReport = new JButton("Report");
        

      BtnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReceiptAndSaveToDatabase();
            }
        });
      
      
      
        add(titlelabel,"wrap");
        add(memIdLabel); add(memberIdfield, "wrap");
    add(footballLabel); add(footballL);  add(footballSpinner, "wrap");
    
    add(netballLabel); add(netballL); add(netballSpinner, "wrap");

    add(basketballLabel); add(basketballL); add(basketballSpinner, "wrap");
    
    add(batLabel); add(batL); add(batSpinner, "wrap");
    
    add(raquetLabel);add(raquetL);add(raquetSpinner, "wrap");
    
    add(flyingdiscLabel);add(flyingdiscL);add(flyingdiscSpinner, "wrap");
    
    add(padsLabel);add(padsL);add(padsSpinner, "wrap");
    
    add(netLabel);add(netL);add(netSpinner, "wrap");
    
    add(mouthguardLabel);add(mouthguardL);add(mouthguardSpinner, "wrap");
    
    add(BtnReport , "wrap");
    
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    
    //   updateStock("hockey stick", spinner.getValue());
    
    }
    public void updateStock(String item, int itemQty){
        String query = "UPDATE equipment SET currentstock = currentstock - ? WHERE equipmentname = ?";
        
        try{            
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, itemQty);
            ps.setString(2, item);
            
            int rs = ps.executeUpdate();
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        }
    private String fetchNameFromDatabase(int memberId) {
        String name = "";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");                
                System.out.println("heererrrr"); 
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
           System.out.println("heererrrr");
           
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
            String query = "SELECT name FROM members WHERE idmembers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memberId);

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
        private void showReceiptAndSaveToDatabase() {
        double totalCost = 0.0;

               
        // Get the member ID from the entered text field
        int memberId = Integer.parseInt(memberIdfield.getText());

        // Retrieve the member's name using the new method
//        String memberName = getMemberInfo(memberId);
    String name = fetchNameFromDatabase(memberId);

        // StringBuilder to store the receipt text
        StringBuilder receiptText = new StringBuilder("Damage Report\n\n");
        receiptText.append("Member ID: ").append(memberId).append("\n");
        receiptText.append("Member Name: ").append(name).append("\n");
        receiptText.append("Date: ").append(getCurrentDate()).append("\n");
        
        // Iterate through the items to calculate total cost and build the receipt text
        for (int i = 0; i < shopItems.size(); i++) {
            int quantity = ((Integer) spinners.get(i).getValue());
            double itemPrice = shopItems.get(i).getItemPrice();
            double subtotal = quantity * itemPrice*0.1;

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
        receiptText.append("\n Damages cost you:  ").append(totalCost);


        // Display receipt in a JOptionPane
        int option = JOptionPane.showConfirmDialog(this, receiptText.toString(), "Damage Report", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // Save purchase details to the database
            savePurchaseToDatabase();
        }
    }



   private void savePurchaseToDatabase() {
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo", "root", "Sleepylynn@1");
            String insertQuery = "INSERT INTO projectmaringo.damaged (memberId,date, item, quantity, totals) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            System.out.println("button clicked");
        // Get the member ID of the logged-in user (replace this with your actual logic to get the member ID)
//        int memberId = getMemberId();

        
        // Iterate through the items to save each item's details
        for (int i = 0; i < shopItems.size(); i++) {
            int memberId = Integer.parseInt(memberIdfield.getText());
           
            int quantity = ((Integer) spinners.get(i).getValue());
            double itemPrice = shopItems.get(i).getItemPrice();
            double subtotal = quantity * itemPrice;
            
            // Set parameters for the prepared statement
            preparedStatement.setInt(1, memberId);
            preparedStatement.setString(3, shopItems.get(i).getItemName());
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, subtotal);
            preparedStatement.setString(2, getCurrentDate());
//             preparedStatement.setInt(5, discount);

            // Execute the SQL statement
            preparedStatement.executeUpdate();
        }

        // Close the database connection
        preparedStatement.close();
        conn.close();

        // Display a success message
        JOptionPane.showMessageDialog(this, "Damage Reports saved to the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving damage report to the database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
     public static void main(String[] args) {
        // Create an instance of AdminDash
        SwingUtilities.invokeLater(() -> new ReportDamaged());
    }
     
      

}

class ShopIt {
    private String itemName;
    private double itemPrice;

    public ShopIt(String itemName, double itemPrice) {
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
    



