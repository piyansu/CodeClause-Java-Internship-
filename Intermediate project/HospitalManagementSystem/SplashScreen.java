package HospitalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SplashScreen {
    JFrame frame;
    JLabel image = new JLabel(new ImageIcon("/photos/who.png"));
    JLabel text = new JLabel("Loading.....");
    JProgressBar progressBar = new JProgressBar();
    JLabel message = new JLabel();
    String name, date;

    public SplashScreen() {


        createGUI();
        addImage();
        addText();
        addProgressBar();
        runProgressBar();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null); // Set layout for content pane
        frame.setUndecorated(true);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        // Set the background color of the content pane
        frame.getContentPane().setBackground(new Color(240, 252, 74)); // Replace with your preferred color
        
        frame.setVisible(true);
        
        // Set the shape after the frame is visible
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20));
    }

    public void addImage() {
        java.net.URL iconURL = getClass().getResource("/photos/who.png");
        ImageIcon i1 = new ImageIcon(iconURL);
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(230, 30, 150, 150);
        frame.add(label);
    }

    public void addText() {
        text.setFont(new Font("arial", Font.BOLD, 30));
        text.setBounds(220, 220, 600, 40);
        text.setForeground(Color.BLUE);
        frame.add(text);
    }

    public void addProgressBar() {
        progressBar.setBounds(100, 280, 400, 25);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        frame.add(progressBar);
    }

    public void runProgressBar() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                int i = 0;
                while (i <= 100) {
                    Thread.sleep(20);
                    publish(i);
                    i++;
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int value = chunks.get(chunks.size() - 1);
                progressBar.setValue(value);
            }

            @Override
            protected void done() {
                frame.dispose();
                new options();
            }
        };

        worker.execute(); // This was missing in your code
    }
}
