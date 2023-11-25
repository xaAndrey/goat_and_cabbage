package goat_cabbage.model.event;

import goat_cabbage.model.GameStatus;
import goat_cabbage.model.field.cell_objects.Goat;
import org.jetbrains.annotations.NotNull;

import java.util.EventObject;

/**
 * Объект события класса игры.
 */
public class GameActionEvent extends EventObject {
    /**
     * Коза.
     */
    private Goat goat;

    /**
     * Статус игры.
     */
    private GameStatus status;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameActionEvent(Object source) {
        super(source);
    }

    /**
     * Установить козу.
     * @param goat коза.
     */
    public void setGoat(@NotNull Goat goat) {
        this.goat = goat;
    }

    /**
     * Установить статус игры.
     * @param status статус игры.
     */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Получить козу.
     * @return коза.
     */
    public Goat getGoat() {
        return goat;
    }

    /**
     * Получить статус игры.
     * @return статус игры.
     */
    public GameStatus getStatus() {
        return status;
    }
}
