package goat_cabbage.model.event;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.cell_objects.Box;

import java.util.EventObject;

public class BoxActionEvent extends EventObject {
    private Box box;
    private Cell fromCell;
    private Cell toCell;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BoxActionEvent(Object source) {
        super(source);
    }

    public void setFromCell(Cell fromCell) {
        this.fromCell = fromCell;
    }

    public Cell getFromCell() {
        return fromCell;
    }

    public void setToCell(Cell toCell) {
        this.toCell = toCell;
    }

    public Cell getToCell() {
        return toCell;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Box getBox() {
        return box;
    }
}
