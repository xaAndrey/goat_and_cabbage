package goat_cabbage.ui.cell;

import goat_cabbage.model.field.cell_objects.Cabbage;
import goat_cabbage.model.field.cell_objects.Wall;
import goat_cabbage.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CabbageWidget extends CellItemWidget {
    private final Cabbage cabbage;

    public CabbageWidget(Cabbage cabbage) {
        super();
        this.cabbage = cabbage;
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ImageUtils.IMAGE_PATH + "cabbage.jpeg"));
            image = ImageUtils.resizeImage(image, 60, 96);
            image = cabbageImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public CellWidget.Layer getLayer() {
        return CellWidget.Layer.BOTTOM;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 120);
    }

    private BufferedImage cabbageImage(BufferedImage cabbageImage) {
        BufferedImage img = new BufferedImage(cabbageImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(cabbageImage, 0, 0, null);

        return img;
    }
}
