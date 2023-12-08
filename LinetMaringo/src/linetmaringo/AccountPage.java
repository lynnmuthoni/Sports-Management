package linetmaringo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class AccountPage extends JFrame {

    private JLabel userImageLabel;
    private JLabel userDetailsLabel, nameL,emailL,statusL,phoneL;
    private JTextField  nameT,emailT,StatusT,phoneT;
    private JButton membershipFeeButton;
    private JSlider progressSlider;
    private JButton viewReportsButton;

    public AccountPage() {
        setTitle("Account Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
//    getContentPane().setBackground(new Color(0, 102, 204));
// getContentPane().setBackground(new ImageIcon(getClass().getResource("/images/3.png")));

        // Set background image
       ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpeg"));
       JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userImageLabel = new JLabel(new ImageIcon(getClass().getResource("/images/background.jpeg")));
        topPanel.add(userImageLabel);
        mainPanel.add(topPanel, createConstraints(0, 0, 1, 1));

        // Center Panel
        JPanel centerPanel = new JPanel(new MigLayout("fillx", "[center]"));
        userDetailsLabel = new JLabel("User Details: Name: Owen, Email: owen@gmail.com, Phone:0726872357, Status:active");
        nameL = new JLabel(" Name: Owen");
        emailL = new JLabel("Email: owen@gmail.com");
        phoneL = new JLabel(" Phone: 0769376573");
        statusL = new JLabel("Status:active");
        
        nameT = new JTextField(" Name: ");
        emailT = new JTextField("Email: ");
        phoneT = new JTextField(" Phone: ");
        StatusT = new JTextField("Status:");
        
        centerPanel.add(userDetailsLabel, "align center, wrap");

        membershipFeeButton = createStyledButton("Membership Fee", Color.BLUE);
        centerPanel.add(membershipFeeButton, "align center, wrap");

        progressSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        progressSlider.setMajorTickSpacing(20);
        progressSlider.setMinorTickSpacing(5);
        progressSlider.setPaintTicks(true);
        progressSlider.setPaintLabels(true);
        centerPanel.add(progressSlider, "align center, wrap");

        mainPanel.add(centerPanel, createConstraints(0, 1, 1, 1));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewReportsButton = createStyledButton("View Damage Reports", Color.GREEN);
        bottomPanel.add(viewReportsButton);
        mainPanel.add(bottomPanel, createConstraints(0, 2, 1, 1));

        add(mainPanel);
        setButtonStyles(membershipFeeButton);
        setButtonStyles(viewReportsButton);
        addListeners();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void setButtonStyles(JButton button) {
        button.setPreferredSize(new Dimension(200, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners() {
        membershipFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog(AccountPage.this, "Enter membership fee amount:");
                if (amount != null) {
                    // Save the entered amount to the database (you can implement this logic)
                    JOptionPane.showMessageDialog(AccountPage.this, "Membership fee paid: " + amount);
                }
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ViewReports v= new ViewReports();         
                             
            }
        });
    }

    private GridBagConstraints createConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        return constraints;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AccountPage().setVisible(true);
            }
        });
    }
}
