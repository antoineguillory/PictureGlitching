package fr.image.glitch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {

    private JFileChooser jfc = new JFileChooser();
    private JPanel imgjp = new JPanel();

    public UI() {
        JFrame jf = new JFrame("Image Glitch");
        JPanel jpp = new JPanel();
        jfc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = jfc.getSelectedFile().getAbsolutePath();
                if(tmp.endsWith(".png")) {
                    jfc.setVisible(false);
                    loadImage(tmp);
                    jf.repaint();
                    jf.pack();
                }
            }
        });
        jf.setLayout(new BorderLayout());
        jpp.add(jfc);
        jf.add(imgjp, BorderLayout.CENTER);
        jf.add(jpp, BorderLayout.SOUTH);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();
    }

    public void loadImage(String file) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(file));
            AbstractGlitchingStrategy load = new PixelSortingGlitchingStrategy(img);
            for(int i = 0; i != 15; ++i) {
                img = load.bugCycle();
            }
            AbstractGlitchingStrategy load2 = new InvertColorGlitchingStrategy(img);
            for(int i = 0; i != 15; ++i) {
                img = load2.bugCycle();
            }
            JLabel picLabel = new JLabel(new ImageIcon(img));
            imgjp.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
