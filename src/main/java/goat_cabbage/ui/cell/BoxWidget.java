package goat_cabbage.ui.cell;

import goat_cabbage.model.field.cell_objects.Box;
import goat_cabbage.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoxWidget extends CellItemWidget {

    private final Box box;

    public BoxWidget(Box box) {
        super();
        this.box = box;
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ImageUtils.IMAGE_PATH + "box.png"));
            image = ImageUtils.resizeImage(image, 60, 96);
            image = boxImage(image);
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

    private BufferedImage boxImage(BufferedImage boxImage) {
        BufferedImage img = new BufferedImage(boxImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(boxImage, 0, 0, null);

        return img;
    }
}
