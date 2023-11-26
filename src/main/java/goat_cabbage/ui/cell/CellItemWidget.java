package goat_cabbage.ui.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import goat_cabbage.ui.cell.CellWidget.Layer;

/**
 * Виджет объекта для виджета ячейки.
 */
public abstract class CellItemWidget extends JPanel {
    /**
     * Конструктор.
     */
    public CellItemWidget() {
        setState(State.DEFAULT);
        setOpaque(false);
    }

    /**
     * Состояние виджета.
     */
    public enum State {
        /**
         * Обычный.
         */
        DEFAULT,

        /**
         * Маленький.
         */
        SMALL
    }

    /**
     * Состояние виджета.
     */
    protected State cellItemState = State.DEFAULT;

    /**
     * Установить состояние виджета.
     * @param state состояние виджета.
     */
    void setState(State state) {
        cellItemState = state;
        setPreferredSize(getDimension());
        repaint();
        revalidate();
    }

    /**
     * Получить состояние виджета.
     * @return состояние виджета.
     */
    public State getState() {
        return cellItemState;
    }

    /**
     * Получить изображение виджета.
     * @return изображение виджета.
     */
    protected abstract BufferedImage getImage();

    /**
     * Получить слой на котором располагается виджет.
     * @return слой на котором располагается виджет.
     */
    public abstract Layer getLayer();

    /**
     * Получить размеры виджета.
     * @return размеры виджета.
     */
    protected abstract Dimension getDimension();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, null);
    }
}
