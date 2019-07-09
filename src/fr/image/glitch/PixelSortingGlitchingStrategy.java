package fr.image.glitch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class PixelSortingGlitchingStrategy implements AbstractGlitchingStrategy {
    private BufferedImage bufi;
    private ArrayList<Pixel> echantillon;
    private ArrayList<Pixel> newechantillon;

    private int startX;
    private int endX;
    private int startY;
    private int endY;


    public PixelSortingGlitchingStrategy(BufferedImage bufi) {
        this.bufi = bufi;
    }

    public BufferedImage bugCycle() {
        initScale();
        loadPixelsSample();
        applyStrategy();
        return getBuggyImage();
    }

    public void initScale() {
        Random r = new Random();
        this.startX = r.nextInt((bufi.getWidth()-40) + 1);
        System.out.println(startX);
        this.endX = r.nextInt((startX+40) - startX +1) + startX;
        System.out.println(endX);

        this.startY = r.nextInt((bufi.getHeight()-40)+ 1);
        System.out.println(startY);
        this.endY = r.nextInt((startY+40) - startY +1) + startY;
        System.out.println(endY);

    }

    public void loadPixelsSample() {
        echantillon = new ArrayList<>();
        for(int y = startY; y!=endY; ++y) {
            for(int x = startX; x!=endX; ++x)
                echantillon.add(new Pixel(x,y, bufi.getRGB(x,y)));
        }
    }

    public void applyStrategy() {
        Comparator<Pixel> byRGB = Comparator.comparingInt(Pixel::getRgb);
        newechantillon = (ArrayList<Pixel>) echantillon.clone();
        newechantillon.sort(byRGB);
        int cptX = startX;
        int cptY = startY;
        for(Pixel px : newechantillon) {
            px.setX(cptX);
            px.setY(cptY);
            ++cptX;
            if(cptX == endX) {
                cptX = startX;
                ++cptY;
            }
        }
    }


    public BufferedImage getBuggyImage() {
        for (Pixel px: newechantillon) {
            bufi.setRGB(px.getX(), px.getY(), px.getRgb());
        }
        return bufi;
    }
}
