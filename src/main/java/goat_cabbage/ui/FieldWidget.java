package goat_cabbage.ui;

import goat_cabbage.model.Direction;
import goat_cabbage.model.Point;
import goat_cabbage.model.event.GoatActionEvent;
import goat_cabbage.model.event.GoatActionListener;
import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.Field;
import goat_cabbage.model.field.cell_objects.Goat;
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
        field.addFieldActionListener(new FieldController());
    }

    private void fillField() {
        for (int i = 0; i < field.getHeight(); ++i) {
            JPanel row = createRow(i);
            add(row);
        }
    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < field.getWidth(); ++i) {
            Point point = new Point(i, rowIndex);

            Cell cell = field.getCell(point);
            CellWidget cellWidget = widgetFactory.create(cell);

            row.add(cellWidget);
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
