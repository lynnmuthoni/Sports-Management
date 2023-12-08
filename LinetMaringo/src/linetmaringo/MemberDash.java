/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author LINET
 */
public class MemberDash extends JFrame {
    private JLabel accountLabel, shopLabel, titleLabel;

    public MemberDash() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
        setSize(1200, 1000);
        getContentPane().setBackground(Color.BLACK);

       titleLabel = new JLabel("                                           MEMBER DASHBOARD");
       titleLabel.setPreferredSize(new Dimension(1290,60)); 
     
         shopLabel = new JLabel();
       shopLabel.setPreferredSize(new Dimension(290,280));   
       shopLabel.setIcon(new ImageIcon(getClass().getResource("/images/7.png")));
       
        accountLabel = new JLabel();
       accountLabel.setPreferredSize(new Dimension(290,280));   
       accountLabel.setIcon(new ImageIcon(getClass().getResource("/images/8.png")));
       
      
          shopLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                ShoppingPage shoppingPage =new ShoppingPage();
               
            }
                });
           accountLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
//                System.out.println("Cllllllllicked");
                AccountPage acc = new AccountPage();
                acc.setVisible(true);
            }
                });   


        // Similar mouse event listeners for other labels

        JPanel mainPanel = new JPanel(new FlowLayout()); // Use FlowLayout for main content
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Add labels to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(shopLabel);
        mainPanel.add(accountLabel);
       

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of AdminDash
        SwingUtilities.invokeLater(() -> new MemberDash());
    }
     
      
       
}
  
 

