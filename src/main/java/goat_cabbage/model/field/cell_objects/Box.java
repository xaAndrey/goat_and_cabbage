package goat_cabbage.model.field.cell_objects;

import goat_cabbage.model.Direction;
import goat_cabbage.model.event.BoxActionEvent;
import goat_cabbage.model.event.BoxActionListener;
import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.CellObject;
import goat_cabbage.model.field.MobileCellObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Box extends MobileCellObject {
    private int weight;

    public int getWeight() {
        return  this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean canLocaleAtPosition(@NotNull Cell cell) {
        return cell.getCellObject() == null;
    }

    @Override
    public void move(@NotNull Direction direction) {
        Cell oldPosition = position;
        Cell newPosition = canMove(direction, 1000);
        Cell neighborCell = position.getNeighborCell(direction);
        if (newPosition != null) {
            if (neighborCell.getCellObject() instanceof Box) {
                ((Box) neighborCell.getCellObject()).move(direction);
            }
            fireBoxIsMoved(oldPosition, newPosition);
            position.takeObject(position.getMobileCellObject());
            newPosition.addObject(this);
        }
    }

    @Override
    protected Cell canMove(@NotNull Direction direction) {
        return null;
    }

    protected Cell canMove(@NotNull Direction direction, int power) {
        Cell result = null;

        if (power < this.weight) {
            return result;
        }

        Cell neighborCell = position.getNeighborCell(direction);
        if (neighborCell != null &&
                canLocaleAtPosition(neighborCell) &&
                !(neighborCell.getCellObject() instanceof Wall) &&
                !(neighborCell.getCellObject() instanceof  Cabbage) &&
                !(neighborCell.getCellObject() instanceof Grass)) {
            if (neighborCell.getCellObject() instanceof Box) {
                Cell resultNext = ((Box) neighborCell.getCellObject()).canMove(direction, power - 1);
                if (resultNext == null) {
                    return result;
                } else {
                    result = neighborCell;
                }
            } else {
                result = neighborCell;
            }
        }

        return result;
    }

    private final ArrayList<BoxActionListener> boxListListener = new ArrayList<>();

    public void addBoxActionListener(BoxActionListener listener) {
        boxListListener.add(listener);
    }

    public void removeBoxActionListener(BoxActionListener listener) {
        boxListListener.remove(listener);
    }

    private void fireBoxIsMoved(@NotNull Cell oldPosition, @NotNull Cell newPosition) {
        for (BoxActionListener listener : boxListListener) {
            BoxActionEvent event = new BoxActionEvent(listener);
            event.setBox(this);
            event.setFromCell(oldPosition);
            event.setToCell(newPosition);
            listener.boxIsMoved(event);
        }
    }
}
