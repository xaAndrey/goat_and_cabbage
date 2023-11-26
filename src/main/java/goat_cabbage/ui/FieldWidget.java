package goat_cabbage.ui;

import goat_cabbage.model.Direction;
import goat_cabbage.model.Orientation;
import goat_cabbage.model.Point;
import goat_cabbage.model.event.GoatActionEvent;
import goat_cabbage.model.event.GoatActionListener;
import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.Field;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.ui.cell.BetweenCellsWidget;
import goat_cabbage.ui.cell.CellItemWidget;
import goat_cabbage.ui.cell.CellWidget;
import goat_cabbage.ui.cell.GoatWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FieldWidget extends JPanel {
    private final Field field;
    private final WidgetFactory widgetFactory;

    public FieldWidget(@NotNull Field field, @NotNull WidgetFactory widgetFactory) {
        this.field = field;
        this.widgetFactory = widgetFactory;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillField();
        subscribeOnRobots();
    }

    private void fillField() {
        if (field.getHeight() > 0) {
            JPanel startRowWalls = createRowWalls(0, Direction.NORTH);
            add(startRowWalls);
        }

        for (int i = 0; i < field.getHeight(); ++i) {
            JPanel row = createRow(i);
            add(row);
            JPanel rowWalls = createRowWalls(i, Direction.SOUTH);
            add(rowWalls);
        }
    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < field.getWidth(); ++i) {
            Point point = new Point(i, rowIndex);

            Cell cell = field.getCell(point);
            CellWidget cellWidget = widgetFactory.create(cell);

            if (i == 0) {
                BetweenCellsWidget westCellWidget = new BetweenCellsWidget(Orientation.VERTICAL);
                row.add(westCellWidget);
            }

            row.add(cellWidget);

            BetweenCellsWidget eastCellWidget = new BetweenCellsWidget(Orientation.VERTICAL);

            row.add(eastCellWidget);
        }
        return row;
    }

    private JPanel createRowWalls(int rowIndex, Direction direction) {
        if (direction == Direction.EAST || direction == Direction.WEST) throw new IllegalArgumentException();
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < field.getWidth(); ++i) {
            Point point = new Point(i, rowIndex);
            Cell cell = field.getCell(point);

            BetweenCellsWidget southCellWidget = new BetweenCellsWidget(Orientation.HORIZONTAL);

            row.add(southCellWidget);
        }
        return row;
    }

    private void subscribeOnRobots() {
        Goat goat = field.getGoatOnField();
        goat.addGoatActionListener(new GoatController());
    }

    private class GoatController implements GoatActionListener {
        @Override
        public void goatIsMoved(@NotNull GoatActionEvent event) {
            CellItemWidget goatWidget = widgetFactory.getWidget(event.getGoat());
            CellWidget from = widgetFactory.getWidget(event.getFromCell());
            CellWidget to = widgetFactory.getWidget(event.getToCell());
            from.removeItem(goatWidget);
            to.addItem(goatWidget);
            goatWidget.requestFocus();
        }

        @Override
        public void goatActivityChanged(@NotNull GoatActionEvent event) {
            Goat goat = event.getGoat();
            GoatWidget goatWidget = (GoatWidget) widgetFactory.getWidget(goat);
            goatWidget.setActive(goat.isActive());
        }
    }
}
