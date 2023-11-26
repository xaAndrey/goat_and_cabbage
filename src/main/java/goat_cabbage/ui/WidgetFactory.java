package goat_cabbage.ui;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.CellObject;
import goat_cabbage.model.field.MobileCellObject;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.model.field.cell_objects.Wall;
import goat_cabbage.ui.cell.CellItemWidget;
import goat_cabbage.ui.cell.CellWidget;
import goat_cabbage.ui.cell.GoatWidget;
import goat_cabbage.ui.cell.WallWidget;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<Cell, CellWidget> cells = new HashMap<>();
    private final Map<CellObject, CellItemWidget> cellObjects = new HashMap<>();
    private final ArrayList<Color> usedColors = new ArrayList<>();

    public CellWidget create(@NotNull Cell cell) {
        if (cells.containsKey(cell)) return cells.get(cell);

        CellWidget item = new CellWidget();

        MobileCellObject mobileCellObject = cell.getMobileCellObject();
        if (mobileCellObject != null) {
            CellItemWidget goatWidget = create(mobileCellObject);
            item.addItem(goatWidget);
        }

        if (cell instanceof Cell && mobileCellObject == null) {
            Wall wall = (Wall) cell.getCellObject();
            if (wall != null) {
                CellItemWidget wallWidget = create(wall);
                item.addItem(wallWidget);
            }
        }

        cells.put(cell, item);
        return item;
    }

    public CellWidget getWidget(@NotNull Cell cell) {
        return cells.get(cell);
    }

    public void remove(@NotNull Cell cell) {
        cells.remove(cell);
    }

    public CellItemWidget create(@NotNull CellObject cellObject) {
        if (cellObjects.containsKey(cellObject)) return cellObjects.get(cellObject);

        CellItemWidget createdWidget = null;
        if (cellObject instanceof Goat) {
            Color color = Color.BLUE;
            usedColors.add(color);
            createdWidget = new GoatWidget((Goat) cellObject, color);
        } else if (cellObject instanceof Wall) {
            createdWidget = new WallWidget((Wall) cellObject);
        } else {
            throw new IllegalArgumentException();
        }

        cellObjects.put(cellObject, createdWidget);
        return createdWidget;
    }

    public CellItemWidget getWidget(@NotNull CellObject cellObject) {
        return cellObjects.get(cellObject);
    }

    public void remove(@NotNull CellObject cellObject) {
        cellObjects.remove(cellObject);
    }
}