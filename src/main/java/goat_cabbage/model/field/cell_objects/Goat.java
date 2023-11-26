package goat_cabbage.model.field.cell_objects;

import goat_cabbage.model.Direction;
import goat_cabbage.model.event.GoatActionEvent;
import goat_cabbage.model.event.GoatActionListener;
import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.MobileCellObject;
import goat_cabbage.ui.cell.CellWidget;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Коза.
 */
public class Goat extends MobileCellObject {
    /**
     * Необходимое кол-во шагов для передвижения.
     */
    private static final int AMOUNT_OF_CHARGE_FOE_MOVE = 1;

    /**
     * Состояние активности козы.
     */
    private boolean isActive;

    /**
     * Количество шагов козы.
     */
    private int numberMoves;

    /**
     * Сила козы.
     */
    private int power;

    /**
     * Трава, съеденная козой.
     */
    private List<Grass> eatenGrasses;

    /**
     * Конструктор.
     *
     * @param numberMoves количество шагов козы.
     * @param power       сила козы.
     */
    public Goat(@NotNull int numberMoves, @NotNull int power) {
        this.numberMoves = numberMoves;
        this.power = power;
    }

    /**
     * Установить кол-во шагов козы.
     *
     * @param numberMoves кол-во шагов козы.
     */
    public void setNumberMoves(int numberMoves) {
        this.numberMoves = numberMoves;
    }

    /**
     * Получить кол-во шагов козы.
     *
     * @return кол-во шагов козы.
     */
    public int getNumberMoves() {
        return this.numberMoves;
    }

    /**
     * Установить силу козы.
     *
     * @param power сила козы.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Получить силу козы.
     *
     * @return сила козы.
     */
    public int getPower() {
        return this.power;
    }

    @Override
    public boolean canLocaleAtPosition(@NotNull Cell newPosition) {
        return newPosition.getMobileCellObject() == null;
    }

    @Override
    public void move(@NotNull Direction direction) {
        Cell oldPosition = position;
        Cell newPosition = canMove(direction);
        if (newPosition != null) {
            fireGoatIsMoved(oldPosition, newPosition);
            position.takeObject(position.getMobileCellObject());
            newPosition.addObject(this);
        }
    }

    @Override
    protected Cell canMove(@NotNull Direction direction) {
        Cell result = null;

        Cell neighborCell = position.getNeighborCell(direction);
        System.out.println((Wall) neighborCell.getCellObject());
        if (neighborCell != null && canLocaleAtPosition(neighborCell) && ((Wall) neighborCell.getCellObject() == null)) {
            result = neighborCell;
        }

        return result;
    }

    /**
     * Сделать козу активной.
     *
     * @param value состояние активности.
     */
    public void setActive(boolean value) {
        if (this.numberMoves > 0 || !value) {
            isActive = value;
            fireGoatChangeActive();
        }
    }

    /**
     * Получить состояние активности козы.
     *
     * @return состояние активности козы.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Список слушателей, подписанныз на события козы.
     */
    private final ArrayList<GoatActionListener> goatListListener = new ArrayList<>();

    /**
     * Добавить нового слушателя за событиями козы.
     *
     * @param listener слушатель.
     */
    public void addGoatActionListener(GoatActionListener listener) {
        goatListListener.add(listener);
    }

    /**
     * Удалить слушателя за событиями козы.
     *
     * @param listener слушатель.
     */
    public void removeGoatActionListener(GoatActionListener listener) {
        goatListListener.remove(listener);
    }

    /**
     * Оповестить слушателей, что коза переместилась.
     *
     * @param oldPosition ячейка откуда переместилась козы.
     * @param newPosition ячейка куда переместилась козы.
     */
    private void fireGoatIsMoved(@NotNull Cell oldPosition, @NotNull Cell newPosition) {
        for (GoatActionListener listener : goatListListener) {
            GoatActionEvent event = new GoatActionEvent(listener);
            event.setGoat(this);
            event.setFromCell(oldPosition);
            event.setToCell(newPosition);
            listener.goatIsMoved(event);
        }
    }

    /**
     * Оповестить слушателей, что состояние активности козы изменилось.
     */
    private void fireGoatChangeActive() {
        for (GoatActionListener listener : goatListListener) {
            GoatActionEvent event = new GoatActionEvent(listener);
            event.setGoat(this);
            listener.goatActivityChanged(event);
        }
    }
}
