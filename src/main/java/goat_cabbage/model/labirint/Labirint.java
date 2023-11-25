package goat_cabbage.model.labirint;

import goat_cabbage.model.Point;
import goat_cabbage.model.field.Field;
import org.jetbrains.annotations.NotNull;

/**
 * Лабиринт.
 */
public abstract class Labirint {
    /**
     * Построить поле.
     * @return поле.
     */
    public Field buildField() {
        Field field = new Field(fieldWidth(), fieldHeight(), new Point(3, 3));

        addGoat(field);

        return field;
    }

    /**
     * Высота поля.
     * @return высота поля.
     */
    protected abstract int fieldHeight();

    /**
     * Ширина поля.
     * @return ширина поля.
     */
    protected abstract int fieldWidth();

    /**
     * Добавить козу на поле.
     * @param field поле.
     */
    protected abstract void addGoat(@NotNull Field field);
}
