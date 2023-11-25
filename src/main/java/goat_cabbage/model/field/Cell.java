package goat_cabbage.model.field;

import goat_cabbage.model.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Ячейка.
 */
public class Cell {
    /**
     * Объекты, расположенные в ячейке.
     */
    protected List<CellObject> objectList = new ArrayList<>();

    /**
     * Добавить объект в ячейку.
     * @param cellObject объект, добавляемый в ячейку.
     */
    public void addObject(@NotNull CellObject cellObject) {
        boolean isPositionSetSuccess = cellObject.setPosition(this);
        if(!isPositionSetSuccess) throw new IllegalArgumentException();
        objectList.add(cellObject);
    }

    /**
     * Изъять объект из ячейки.
     * @param cellObject запрашиваемый объект.
     * @return запрашиваемый объект. null - если объект не содержится в ячейке.
     */
    public CellObject takeObject(CellObject cellObject) {
        CellObject result = null;
        if (objectList.contains(cellObject)) {
            objectList.remove(cellObject);
            cellObject.setPosition(null);
            result = cellObject;
        }
        return result;
    }

    /**
     * Получить перемещаемый объект ячейки.
     * @return перемещаемый объект ячейки.
     */
    public MobileCellObject getMobileCellObject() {
        return (MobileCellObject) objectList.stream().filter(i -> i instanceof MobileCellObject).findFirst().orElse(null);
    }

    /**
     * Соседние ячейки.
     */
    private final Map<Direction, Cell> neighborCells = new EnumMap<>(Direction.class);

    /**
     * Получить соседние ячейки
     * @return соседние ячейки
     */
    public final Map<Direction, Cell> getNeighborCells() {
        return Collections.unmodifiableMap(neighborCells);
    }

    /**
     * Получить соседнюю ячейку в заданном направлении.
     * @param direction направление.
     * @return соседняя ячейка. null, если в заданном направлении нет соседней ячейки.
     */
    public Cell getNeighborCell(@NotNull Direction direction) {
        return neighborCells.get(direction);
    }

    /**
     * Установить ячейку соседней.
     * @param neighborCell соседняя ячейка.
     * @param direction направление.
     * @throws IllegalArgumentException если передняя ячейка не может быть соседней.
     */
    void setNeighborCells(@NotNull Cell neighborCell, @NotNull Direction direction) {
        if (neighborCells == this || neighborCells.containsKey(direction) || neighborCells.containsValue(neighborCell))
            throw new IllegalArgumentException();
        neighborCells.put(direction, neighborCell);
        if (neighborCell.getNeighborCell(direction.getOppositeDirection()) == null) {
            neighborCell.setNeighborCells(this, direction.getOppositeDirection());
        }
    }

    /**
     * Получить направление с соседней ячейкой.
     * @param other соседняя ячейка.
     * @return направление.
     */
    public Direction getNeighnborDirection(@NotNull Cell other) {
        for (var i : neighborCells.entrySet()) {
            if (i.getValue().equals(other)) return i.getKey();
        }
        return null;
    }
}