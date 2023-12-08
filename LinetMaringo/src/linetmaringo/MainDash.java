/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


/**
 *
 * @author LINET
 */
public class MainDash {
        private JTextField emailField;
    private JPasswordField passwordField;
    private String email, password;
    private int member_id;
    
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Main Dash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 150);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        JLabel title=new JLabel("ACCESS THE SYSTEM AS:");
        
        
        JButton adminButton = new JButton("Admin");
        JButton patronButton = new JButton("Patron");
        JButton memberButton = new JButton("Member");

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin= new Admin();
                admin.AdminLogin();
//                System.out.println("Admin button clicked");
            }
        });

        patronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Patrons patron= new Patrons();
                patron.PatronLogin();
              
            }
        });
        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Member member= new Member();
                member.MemberLogin();
                
            }
        });
        title.setForeground(Color.WHITE);
        adminButton.setBackground(Color.RED);
        adminButton.setForeground(Color.WHITE);
        memberButton.setBackground(Color.RED);
        memberButton.setForeground(Color.WHITE);
        patronButton.setBackground(Color.RED);
        patronButton.setForeground(Color.WHITE);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        frame.add(title);
        frame.add(adminButton);
        frame.add(patronButton);
        frame.add(memberButton);
        
        frame.setVisible(true);
    }
    
    
    
}
