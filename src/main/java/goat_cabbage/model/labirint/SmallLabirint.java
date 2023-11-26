package goat_cabbage.model.labirint;

import goat_cabbage.model.Game;
import goat_cabbage.model.Point;
import goat_cabbage.model.field.Field;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.model.field.cell_objects.Wall;
import org.jetbrains.annotations.NotNull;

/**
 * Лабиринт маленького поля.
 */
public class SmallLabirint extends Labirint {
    /**
     * Высота поля.
     */
    private static final int FIELD_HEIGHT = 4;

    /**
     * Ширина поля.
     */
    private static final int FIELD_WIDTH = 4;

    @Override
    protected int fieldHeight() {
        return FIELD_HEIGHT;
    }

    @Override
    protected int fieldWidth() {
        return FIELD_WIDTH;
    }

    @Override
    protected void addGoat(@NotNull Field field) {
        Goat goat = new Goat(5, 1);

        field.getCell(new Point(0,2)).addObject(goat);
    }

    @Override
    protected void addWalls(@NotNull Field field) {
        field.getCell(new Point(1, 0)).addObject(new Wall());
        field.getCell(new Point(1, 1)).addObject(new Wall());
        field.getCell(new Point(1, 2)).addObject(new Wall());
        field.getCell(new Point(3, 1)).addObject(new Wall());
        field.getCell(new Point(3, 2)).addObject(new Wall());
        field.getCell(new Point(3, 3)).addObject(new Wall());
    }
}
