package goat_cabbage.ui.cell;

import goat_cabbage.model.field.cell_objects.Wall;
import goat_cabbage.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WallWidget extends CellItemWidget {

    private final Wall wall;

    public WallWidget(Wall wall) {
        super();
        this.wall = wall;
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ImageUtils.IMAGE_PATH + "wall.png"));
            image = ImageUtils.resizeImage(image, 60, 96);
            image = wallImage(image);
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

    private BufferedImage wallImage(BufferedImage wallImage) {
        BufferedImage img = new BufferedImage(wallImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(wallImage, 0, 0, null);

        return img;
    }
}
