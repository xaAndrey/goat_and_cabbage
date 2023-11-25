package goat_cabbage.model;

/**
 * Направления.
 */
public enum Direction {
    /**
     * Север.
     */
    NORTH,

    /**
     * Юг.
     */
    SOUTH,

    /**
     * Запад.
     */
    WEST,

    /**
     * Восток.
     */
    EAST;

    /**
     * Противоположное направление.
     */
    private Direction opposite;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        WEST.opposite = EAST;
        EAST.opposite = WEST;
    }

    /**
     * Получить противоположное направление.
     * @return противоположное направление.
     */
    public Direction getOppositeDirection() {
        return opposite;
    }
}
