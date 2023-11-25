package goat_cabbage.model.field;

import goat_cabbage.model.Direction;
import goat_cabbage.model.Point;
import goat_cabbage.model.field.cell_objects.Gabbage;
import goat_cabbage.model.field.cell_objects.Goat;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Поле.
 */
public class Field {
    /**
     * Ячейки поля.
     */
    private final Map<Point, Cell> cells = new HashMap<>();

    /**
     * Ширина поля.
     */
    private final int width;

    /**
     * Высота поля.
     */
    private final int height;

//    private final Gabbage gabbage;

    /**
     * Конструктор
     * @param width ширина. Должна быть > 0.
     * @param height высота. Должна быть > 0.
     * @param pointGabbage координата ячейки с капустой.
     * @throws IllegalArgumentException если ширина, высота или координата ячейки переданы некорректные.
     */
    public Field(int width, int height, @NotNull Point pointGabbage) {
        if (width <= 0) throw new IllegalArgumentException("Field width must be more than 0");
        if (height <= 0) throw new IllegalArgumentException("Field height must be more than 0");
        if (pointGabbage.getX() >= width || pointGabbage.getY() >= height) {
            throw new IllegalArgumentException("exit point coordinates must be in range from 0 to weight or height");
        }

        this.width = width;
        this.height = height;

        buildField();
//        this.gabbage = (Gabbage) getCell(pointGabbage);
//        ((Gabbage) getCell(pointGabbage)).addExitCellActionListener(new ExitCellObserver());

    }

    /**
     * Построить игровое поле.
     */
    private void buildField() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Point p = new Point(x, y);
                Cell cell = new Cell();
                if (x > 0) cell.setNeighborCells(getCell(p.to(Direction.WEST, 1)), Direction.WEST);
                if (y > 0) cell.setNeighborCells(getCell(p.to(Direction.NORTH, 1)), Direction.NORTH);
                cells.put(p, cell);
            }
        }
    }

    /**
     * Получить ширину поля.
     * @return ширина поля.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Получить высоту поля.
     * @return высота поля.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Получить ячейку по заданной координате.
     * @param point координата.
     * @return ячейка.
     */
    public Cell getCell(@NotNull Point point) {
        return cells.get(point);
    }

    /**
     * Получить козу на поле.
     * @return коза на поле.
     */
    public Goat getGoatOnField() {
        for (var i : cells.entrySet()) {
            Goat goat = (Goat) i.getValue().getMobileCellObject();
            if (goat != null) return goat;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return width == field.width &&
                height == field.height &&
                Objects.equals(cells, field.cells);
//                Objects.equals(gabbage, field.gabbage);
    }

    @Override
    public String toString() {
        return "Field{" +
                "cells=" + cells +
                ", width=" + width +
                ", height=" + height +
//                ", gabbage=" + gabbage +
                "}";
    }
}
