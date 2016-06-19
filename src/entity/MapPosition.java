package entity;

/**
 * Created by Stiffix on 19/06/16.
 */
public class MapPosition {
    private final int row;
    private final int col;

    public MapPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
}
