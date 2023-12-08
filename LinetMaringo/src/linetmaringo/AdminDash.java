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

/**
 *
 * @author LINET
 */
public class AdminDash extends JFrame {
    private JLabel usersLabel, gamesLabel, shopLabel, eventsLabel, titleLabel, patronLabel ,damageLabel;

    public AdminDash() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
        setSize(1200, 1000);
        getContentPane().setBackground(Color.BLACK);

       titleLabel = new JLabel("ADMIN DASHBOARD");
          titleLabel.setPreferredSize(new Dimension(1280,50)); 
       usersLabel = new JLabel();
       usersLabel.setPreferredSize(new Dimension(290,280));   
       usersLabel.setIcon(new ImageIcon(getClass().getResource("/images/1.png")));
       
        gamesLabel = new JLabel();
       gamesLabel.setPreferredSize(new Dimension(290,280));   
       gamesLabel.setIcon(new ImageIcon(getClass().getResource("/images/4.png")));
       
        shopLabel = new JLabel();
       shopLabel.setPreferredSize(new Dimension(290,280));   
       shopLabel.setIcon(new ImageIcon(getClass().getResource("/images/2.png")));
       
        eventsLabel = new JLabel();
       eventsLabel.setPreferredSize(new Dimension(290,280));   
       eventsLabel.setIcon(new ImageIcon(getClass().getResource("/images/3.png")));

        patronLabel = new JLabel();
       patronLabel.setPreferredSize(new Dimension(290,280));   
       patronLabel.setIcon(new ImageIcon(getClass().getResource("/images/5.png")));

        damageLabel = new JLabel();
       damageLabel.setPreferredSize(new Dimension(290,280));   
      damageLabel.setIcon(new ImageIcon(getClass().getResource("/images/6.png")));

        usersLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                Users users =new Users();
            }
                });
        
         gamesLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                Sports sports =new Sports();
            }
                });
          shopLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                Stores stores =new Stores();
                stores.createAndShowGUI();
            }
                });
           eventsLabel.addMouseListener(new MouseAdapter() {           
            @Override                
            public void mouseClicked(MouseEvent e) 
            {
                   events myev =new events();
                   myev.createAndShowGUI();
            }
                });   
        
        
        patronLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                patron pat = new patron();
            }
        });
        damageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReportDamaged report = new ReportDamaged();
            }
        });



        // Similar mouse event listeners for other labels

        JPanel mainPanel = new JPanel(new FlowLayout()); // Use FlowLayout for main content
        mainPanel.setBackground(Color.BLACK);

        // Add labels to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(usersLabel);
        mainPanel.add(gamesLabel);
        mainPanel.add(shopLabel);
        mainPanel.add(eventsLabel);
        mainPanel.add(patronLabel);
        mainPanel.add(damageLabel);

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of AdminDash
        SwingUtilities.invokeLater(() -> new AdminDash());
    }
     
      
       
}
  
 

