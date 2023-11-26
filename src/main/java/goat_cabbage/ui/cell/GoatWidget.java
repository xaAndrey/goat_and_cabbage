package goat_cabbage.ui.cell;

import goat_cabbage.model.Direction;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.ui.utils.GameWidgetsUtils;
import goat_cabbage.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class GoatWidget extends CellItemWidget {
    /**
     *
     */
    private final Goat goat;

    /**
     *
     */
    private final Color color;

    /**
     * @param goat
     * @param color
     */
    public GoatWidget(Goat goat, Color color) {
        super();
        this.goat = goat;
        this.color = color;
        setFocusable(true);
        addKeyListener(new KeyController());
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getImageFile());
            image = ImageUtils.resizeImage(image, 60, 96);
            image = goatImageWithChargeText(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public CellWidget.Layer getLayer() {
        return CellWidget.Layer.BOTTOM;
    }

    /**
     * @param state
     */
    public void setActive(boolean state) {
        setFocusable(state);
        requestFocus();
        repaint();
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 120);
    }

    /**
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param goatImage
     * @return
     */
    private BufferedImage goatImageWithChargeText(BufferedImage goatImage) {
        BufferedImage img = new BufferedImage(goatImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(goatImage, 0, 0, null);

        if (cellItemState == State.DEFAULT) {
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(goatNumberMovesColor());
            g.drawString(goatNumberMoves(), 5, 112);
        }

        return img;
    }

    /**
     * @return
     */
    private String goatNumberMoves() {
        return goat.getNumberMoves() + "";
    }

    /**
     * @return
     */
    private Color goatNumberMovesColor() {
        return GameWidgetsUtils.numberMovesTextColor(goat.getNumberMoves());
    }

    /**
     * @return
     */
    private File getImageFile() {
        return new File(ImageUtils.IMAGE_PATH + "goat.png");
    }

    /**
     *
     */
    private class KeyController implements KeyListener {
        @Override
        public void keyTyped(KeyEvent arg0) {
            // No implements yet.
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            moveAction(keyCode);

            repaint();
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            // No implements yet&
        }

        private void moveAction(int keyCode) {
            Direction direction = directionByKeyCode(keyCode);
            System.out.println("keyCode = " + keyCode + ", goat.png go to " + direction);
            if (direction != null) {
                goat.move(direction);
            }
        }

        private Direction directionByKeyCode(int keyCode) {
            Direction direction = null;
            switch (keyCode) {
                case KeyEvent.VK_W:
                    direction = Direction.NORTH;
                    break;
                case KeyEvent.VK_S:
                    direction = Direction.SOUTH;
                    break;
                case KeyEvent.VK_A:
                    direction = Direction.WEST;
                    break;
                case KeyEvent.VK_D:
                    direction = Direction.EAST;
                    break;
            }
            return direction;
        }


    }
}
