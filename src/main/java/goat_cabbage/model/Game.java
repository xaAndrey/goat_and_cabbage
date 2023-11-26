package goat_cabbage.model;

import goat_cabbage.model.event.GameActionEvent;
import goat_cabbage.model.event.GameActionListener;
import goat_cabbage.model.event.GoatActionEvent;
import goat_cabbage.model.event.GoatActionListener;
import goat_cabbage.model.field.Field;
import goat_cabbage.model.field.cell_objects.Gabbage;
import goat_cabbage.model.field.cell_objects.Goat;
import goat_cabbage.model.labirint.Labirint;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Игра.
 */
public class Game {
    /**
     * Статус игры.
     */
    private GameStatus gameStatus;

    /**
     * Коза.
     */
    private Goat goat;

    /**
     * Игровое поле.
     */
    private Field gameField;

    /**
     * Конструктор.
     * @param labirint лабиринт, содержащий расстановку элементов на поле.
     */
    public Game(Labirint labirint) {
        startGame(labirint);
    }

    /**
     * Старт новой игры.
     * @param labirint лабиринт, содержащий расстановку элементов на поле.
     */
    public void startGame(@NotNull Labirint labirint) {
        setStatus(GameStatus.GAME_IS_ON);

        buildField(labirint);
        setGoat(gameField.getGoatOnField());

        gameField.getGoatOnField().addGoatActionListener(new GoatObserver());


    }

    /**
     * Прервать игру
     */
    public void abort() {
        setStatus(GameStatus.GAME_ABORTED);
    }

    /**
     * Получить текущий статус игры.
     * @return текующий статус игры.
     */
    public GameStatus getStatus() {
        return gameStatus;
    }

    /**
     * Установить текущий статус игры.
     * @param status текущий статус игры.
     */
    private void setStatus(GameStatus status) {
        if (gameStatus != status) {
            gameStatus = status;
            fireGameStatusIsChanged(gameStatus);
        }
    }

    /**
     * Получить козу.
     * @return коза.
     */
    public Goat getGoat() {
        return goat;
    }

    public void setGoat(Goat goat) {
        this.goat = goat;
    }

    /**
     * Получить игровое поле.
     * @return игровое поле.
     */
    public Field getGameField() {
        return gameField;
    }

    /**
     * Обновить состояние игры.
     */
    private void updateGameStatus() {
        GameStatus status = determineOutcomeGame();
        setStatus(status);
    }

    /**
     * Определить исход игры.
     * @return статус игры.
     */
    private GameStatus determineOutcomeGame() {
        GameStatus result = GameStatus.GAME_IS_ON;

        Goat goatOnField = gameField.getGoatOnField();

        if (goatOnField.getNumberMoves() <= 0) {
            result = GameStatus.GOAT_NOT_HAVE_MOVES;
        }

        return result;
    }

    /**
     * Построить игровое поле.
     * @param labirint лабиринт, содержащий расстановку элементов на поле.
     */
    private void buildField(@NotNull Labirint labirint) {
        gameField = labirint.buildField();
    }

    /**
     * Класс, реализующий наблюдение за событиями.
     */
    private class GoatObserver implements GoatActionListener {
        @Override
        public void goatIsMoved(@NotNull GoatActionEvent event) {
            fireGoatIsMoved(event.getGoat());
        }

        @Override
        public void goatActivityChanged(@NotNull GoatActionEvent event) {
            // Not implemented yet
        }
    }

    /**
     * Список слушателей, подписанных на события игры.
     */
    private final ArrayList<GameActionListener> gameActionListeners = new ArrayList<>();

    /**
     * Добавить нвоого слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void addGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.add(listener);
    }

    /**
     * Удалить слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void removeGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.remove(listener);
    }

    /**
     * Оповестить слушателей, что коза переместилась.
     * @param goat коза, которая переместилась.
     */
    public void fireGoatIsMoved(@NotNull Goat goat) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setGoat(goat);
            listener.goatIsMoved(event);
        }
    }

    /**
     * Оповестить сулшателей {@link Game#gameActionListeners}, что статус игры изменился.
     * @param status статус игры.
     */
    public void fireGameStatusIsChanged(@NotNull GameStatus status) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setStatus(status);
            listener.gameStatusChanged(event);
        }
    }
}
