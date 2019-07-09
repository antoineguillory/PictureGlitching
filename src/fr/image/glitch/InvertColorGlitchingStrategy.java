package fr.image.glitch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class InvertColorGlitchingStrategy extends AbstractGlitchingStrategy {

    public InvertColorGlitchingStrategy(BufferedImage bufi) {
        super(bufi);
    }

    @Override
    public BufferedImage bugCycle() {
        initScale();
        loadPixelsSample();
        applyStrategy();
        return getBuggyImage();
    }

    public void applyStrategy() {
        ArrayList<Pixel> newechantillon = (ArrayList<Pixel>) this.getEchantillon().clone();
        for(Pixel px : newechantillon) {
            int rgba = px.getRgb();
            Color col = new Color(rgba, true);
            col = new Color(255 - col.getRed(),
                    255 - col.getGreen(),
                    255 - col.getBlue());
            px.setRgb(col.getRGB());
        }
        setNewechantillon(newechantillon);
    }

}
