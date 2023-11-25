package goat_cabbage.model.event;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;

/**
 * Интерфейс слушателя события класса коза.
 */
public interface GoatActionListener extends EventListener {
    /**
     * Коза переместилась.
     * @param event объект события класса коза.
     */
    void goatIsMoved(@NotNull GoatActionEvent event);

    /**
     * Состояние активности козы изменилось.
     * @param event объект события класса коза.
     */
    void goatActivityChanged(@NotNull GoatActionEvent event);
}
