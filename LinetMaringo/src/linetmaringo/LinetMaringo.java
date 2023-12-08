
package linetmaringo;

import javax.swing.SwingUtilities;


public class LinetMaringo {

   
    public static void main(String[] args) {
          // Create the splash screen
        Splashscreen splashScreen = new Splashscreen("/images/splsh.png", 2000);
        splashScreen.showSplash();

        // Simulate some initialization work during the splash screen display
        try {
            Thread.sleep(3000); // Replace with actual initialization work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.dispose();

        // Create the main screen
        SwingUtilities.invokeLater(() -> {
            
            MainDash maindash = new MainDash();
            maindash.createAndShowGUI();
        });
    }
    
}
