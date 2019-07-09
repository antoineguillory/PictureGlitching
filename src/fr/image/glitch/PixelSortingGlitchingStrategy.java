package fr.image.glitch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class PixelSortingGlitchingStrategy extends AbstractGlitchingStrategy {

    public PixelSortingGlitchingStrategy(BufferedImage bufi) {
        super(bufi);
    }

    public BufferedImage bugCycle() {
        initScale();
        loadPixelsSample();
        applyStrategy();
        return getBuggyImage();
    }


    public void loadPixelsSample() {
        ArrayList<Pixel> tmp = new ArrayList<>();
        for(int y = getStartY(); y!=getEndY(); ++y) {
            for(int x = getStartX(); x!=getEndX(); ++x)
                tmp.add(new Pixel(x,y, getBufi().getRGB(x,y)));
        }
        this.setEchantillon(tmp);
    }

    public void applyStrategy() {
        Comparator<Pixel> byRGB = Comparator.comparingInt(Pixel::getRgb);
        ArrayList<Pixel> newechantillon = (ArrayList<Pixel>) this.getEchantillon().clone();
        newechantillon.sort(byRGB);
        int cptX = getStartX();
        int cptY = getStartY();
        for(Pixel px : newechantillon) {
            px.setX(cptX);
            px.setY(cptY);
            ++cptX;
            if(cptX == getEndX()) {
                cptX = getStartX();
                ++cptY;
            }
        }
        setNewechantillon(newechantillon);
    }

}
