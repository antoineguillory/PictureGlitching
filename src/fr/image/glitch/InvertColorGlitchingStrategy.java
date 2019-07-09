package fr.image.glitch;

import java.awt.image.BufferedImage;

public class InvertColorGlitchingStrategy implements GlitchingStrategy {
    @Override
    public BufferedImage bugCycle() {
        initScale();
        loadPixelsSample();
        applyStrategy();
        return getBuggyImage();
    }

    @Override
    public void initScale() {

    }

    @Override
    public void loadPixelsSample() {

    }

    @Override
    public void applyStrategy() {

    }

    @Override
    public BufferedImage getBuggyImage() {
        return null;
    }
}
