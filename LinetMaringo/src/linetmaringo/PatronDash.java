/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linetmaringo;
import javax.swing.*;
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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LINET
 */
class Patrons{
  public void PatronLogin() {
         JFrame adminframe = new JFrame("Patron Login Page");
        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminframe.setLocationRelativeTo(null);
//        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminframe.setSize(400, 300);
        adminframe.setLayout(new BorderLayout());

        // Create a colorful background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.PINK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create a panel for the login form
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add email label and field
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 0, 10);
        loginPanel.add(new JLabel("Email:"), c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextField emailField = new JTextField(20);
        loginPanel.add(emailField, c);

        // Add password label and field
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        loginPanel.add(new JLabel("Password:"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, c);

        // Add login button
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton, c);


        // Add action for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                  PatronDash patrondash= new PatronDash();
                
                 String email = emailField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
                System.out.println(email + password);

//                if (authenticateUser(email, password)) {
//                    JOptionPane.showMessageDialog(null, "Login successful!");
//                    
//                } else {
//                    JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
//                }
             
            String patrongame = authenticateUser(email, password);

            if (patrongame != null) {
                JOptionPane.showMessageDialog(null, "Welcome patron for " + patrongame);
                // Store the game in a variable, e.g., PATRONGAME = patrongame;
                 // Create an instance of AnotherClass and pass patrongame
//                 PatronDash ptdash= new PatronDash();
//                  AnotherClass anotherClassInstance = new AnotherClass(patrongame);
//              captains captain= new captains(patrongame);
              PatronDash patron= new PatronDash();
             
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
            }
                
            }
        });
        
        ///////////////
        // Add login panel to the background panel
        backgroundPanel.add(loginPanel);

        // Add the background panel to the frame
        adminframe.add(backgroundPanel, BorderLayout.CENTER);

        adminframe.setVisible(true);   

//        adminFrame.setDefaultCloseOperation(JFrame.DIS 400, 300);

       
        
    }
        
      private String authenticateUser(String email, String password) {
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");           
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmaringo","root","Sleepylynn@1");
                System.out.println("heererrrr");
        String query = "SELECT * FROM patrons WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        String patrongame = null;

        if (resultSet.next()) {
            // Retrieve other patron information if needed
            int memberId = resultSet.getInt("idpatrons");
            String patronName = resultSet.getString("name");
            patrongame = resultSet.getString("sport");

            System.out.println("Authenticated user ID: " + memberId);
            System.out.println("Authenticated user Name: " + patronName);
            System.out.println("Game: " + patrongame);
        }

        resultSet.close();
        preparedStatement.close();
        conn.close();

        return patrongame;
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        return null;
    }
}
      
       

}
public class PatronDash extends JFrame {
    private JLabel eventsLabel, captainLabel, titleLabel, damageLabel;
    private String patrongame;

    public PatronDash() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
        setSize(1200, 1000);
        getContentPane().setBackground(Color.BLACK);

       titleLabel = new JLabel("                                           PATRON DASHBOARD");
       titleLabel.setPreferredSize(new Dimension(1290,60)); 
       
        captainLabel = new JLabel();
       captainLabel.setPreferredSize(new Dimension(290,280));   
       captainLabel.setIcon(new ImageIcon(getClass().getResource("/images/captain.png")));
     
          damageLabel = new JLabel();
       damageLabel.setPreferredSize(new Dimension(290,280));   
      damageLabel.setIcon(new ImageIcon(getClass().getResource("/images/report da.png")));

          eventsLabel = new JLabel();
       eventsLabel.setPreferredSize(new Dimension(290,280));   
       eventsLabel.setIcon(new ImageIcon(getClass().getResource("/images/events.png")));

      
          captainLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
//                 captains captain = new captains(patrongame);
               captains captain = new captains();
//               captain.setVisible(true);
            }
                });
           eventsLabel.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseClicked(MouseEvent e) 
            {
//                System.out.println("Cllllllllicked");
               events myev= new events();
               myev.createAndShowGUI();
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
        mainPanel.setBackground(Color.DARK_GRAY);

        // Add labels to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(captainLabel);
        mainPanel.add(damageLabel);
        mainPanel.add(eventsLabel);
       

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of AdminDash
        SwingUtilities.invokeLater(() -> new PatronDash());
    }

    PatronDash(String patrongame) {
       this.patrongame = patrongame;
    }
     
      
}


  