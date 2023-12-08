
package linetmaringo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Splashscreen extends JWindow{     
     private int duration;
    private ImageIcon splashImage;

    public Splashscreen(String imagePath, int duration) {
        this.duration = duration;
        splashImage = new ImageIcon(getClass().getResource(imagePath)); // Use getResource to load the image
    }

    public void showSplash() {
        JPanel content = new JPanel(new BorderLayout());
        content.add(new JLabel(splashImage), BorderLayout.CENTER);

        setContentPane(content);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
