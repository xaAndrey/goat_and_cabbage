package goat_cabbage.model.field;

import goat_cabbage.model.Direction;
import org.jetbrains.annotations.NotNull;

/**
 * Перемещаемый объект ячейки.
 */
public abstract class MobileCellObject extends CellObject{
    /**
     * Переместить объект в заданном направлении.
     * @param direction направление.
     */
    public abstract void move(@NotNull Direction direction);

    /**
     * Может ли объект переместиться в заданном направлении.
     * @param direction направление.
     * @return соседнюю ячейку, если объект может переместиться в заданном направлении. Иначе null
     */
    protected abstract Cell canMove(@NotNull Direction direction);
}
