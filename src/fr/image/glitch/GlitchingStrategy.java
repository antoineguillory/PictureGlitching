package fr.image.glitch;

import java.awt.image.BufferedImage;

public interface GlitchingStrategy {
    BufferedImage bugCycle();
    void initScale();
    void loadPixelsSample();
    void applyStrategy();
    BufferedImage getBuggyImage();
}
