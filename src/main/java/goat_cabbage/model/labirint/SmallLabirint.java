package goat_cabbage.model.labirint;

import goat_cabbage.model.Game;
import goat_cabbage.model.Point;
import goat_cabbage.model.field.Field;
import goat_cabbage.model.field.cell_objects.Goat;
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
}
