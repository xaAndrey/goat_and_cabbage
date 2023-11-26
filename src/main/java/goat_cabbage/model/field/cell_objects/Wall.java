package goat_cabbage.model.field.cell_objects;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.CellObject;
import org.jetbrains.annotations.NotNull;

public class Wall extends CellObject {
    @Override
    public boolean canLocaleAtPosition(@NotNull Cell cell) {
        return position == null && cell.getCellObject() == null;
    }
}
