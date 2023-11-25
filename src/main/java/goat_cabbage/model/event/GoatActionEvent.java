package goat_cabbage.model.event;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.cell_objects.Goat;
import org.jetbrains.annotations.NotNull;

import java.util.EventObject;

/**
 * Объект события класса коза.
 */
public class GoatActionEvent extends EventObject {
    /**
     * Коза.
     */
    private Goat goat;

    /**
     * Ячейка откуда переместилась коза.
     */
    private Cell fromCell;

    /**
     * Ячейка куда переместилась коза
     */
    private Cell toCell;

    /**
     * Конструктор объекта события класса коза.
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GoatActionEvent(Object source) {
        super(source);
    }

    /**
     * Установить ячейку откуда переместилась коза.
     * @param fromCell ячейка откуда переместилась коза.
     */
    public void setFromCell(Cell fromCell) {
        this.fromCell = fromCell;
    }

    /**
     * Получить ячейку откуда переместилась коза.
     * @return ячейка откуда переместилась коза.
     */
    public Cell getFromCell() {
        return fromCell;
    }

    /**
     * Установить ячейку куда переместилась коза.
     * @param toCell ячейка куда переместилась коза.
     */
    public void setToCell(Cell toCell) {
        this.toCell = toCell;
    }

    /**
     * Получить ячейку куда переместилась коза.
     * @return ячейка куда переместилась коза.
     */
    public Cell getToCell() {
        return this.toCell;
    }

    /**
     * Установить козу.
     * @param goat коза.
     */
    public void setGoat(@NotNull Goat goat) {
        this.goat = goat;
    }

    /**
     * Получить козу.
     * @return коза.
     */
    public Goat getGoat() {
        return goat;
    }
}
