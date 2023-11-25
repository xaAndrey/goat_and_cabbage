package goat_cabbage.model.event;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;

/**
 * Интерфейс слушателя события класса игры.
 */
public interface GameActionListener extends EventListener {
    /**
     * Робот переместился.
     * @param event объект события класса игры.
     */
    void goatIsMoved(@NotNull GameActionEvent event);

    /**
     * Статус игры изменился.
     * @param event объект события класса игры.
     */
    void gameStatusChanged(@NotNull GameActionEvent event);
}
