package fr.image.glitch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractGlitchingStrategy implements GlitchingStrategy {
    private BufferedImage bufi;
    private ArrayList<Pixel> echantillon;
    private ArrayList<Pixel> newechantillon;

    private int startX;
    private int endX;
    private int startY;
    private int endY;


    public AbstractGlitchingStrategy(BufferedImage bufi) {
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
        this.endX = r.nextInt((startX+40) - startX +1) + startX;

        this.startY = r.nextInt((bufi.getHeight()-40)+ 1);
        this.endY = r.nextInt((startY+40) - startY +1) + startY;

    }

    public void loadPixelsSample() {
        ArrayList<Pixel> tmp = new ArrayList<>();
        for(int y = getStartY(); y!=getEndY(); ++y) {
            for(int x = getStartX(); x!=getEndX(); ++x)
                tmp.add(new Pixel(x,y, getBufi().getRGB(x,y)));
        }
        this.setEchantillon(tmp);
    }

    public BufferedImage getBuggyImage() {
        for (Pixel px: newechantillon) {
            System.out.println(px.getRgb());
            bufi.setRGB(px.getX(), px.getY(), px.getRgb());
        }
        return bufi;
    }


    public BufferedImage getBufi() {
        return bufi;
    }

    public void setBufi(BufferedImage bufi) {
        this.bufi = bufi;
    }

    public ArrayList<Pixel> getEchantillon() {
        return echantillon;
    }

    public void setEchantillon(ArrayList<Pixel> echantillon) {
        this.echantillon = echantillon;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public ArrayList<Pixel> getNewechantillon() {
        return newechantillon;
    }

    public void setNewechantillon(ArrayList<Pixel> newechantillon) {
        this.newechantillon = newechantillon;
    }
}
