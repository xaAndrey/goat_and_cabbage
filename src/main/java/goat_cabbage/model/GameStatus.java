package goat_cabbage.model;

/**
 * Статус игры.
 */
public enum GameStatus {
    /**
     * Победа
     */
    WIN,

    /**
     * Игра идёт.
     */
    GAME_IS_ON,

    /**
     * Коза больше не имеет ходов.
     */
    GOAT_NOT_HAVE_MOVES,

    /**
     * Игра прервана.
     */
    GAME_ABORTED
}
