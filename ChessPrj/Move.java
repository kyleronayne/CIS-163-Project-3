package ChessPrj;

/***********************************************************************
 * Creates an object for a ChessPiece's move specifications. Details
 * a move's from row and column as well as the to row and column.
 *********************************************************************/
public class Move {


    /** Denotes the row in which a chess piece is moving from */
    public int fromRow;

    /** Denotes the column in which a chess piece is moving from */
    public int fromColumn;

    /** Denotes the row in which a chess piece is moving to */
    public int toRow;

    /** Denotes the column in which a chess piece is moving to */
    public int toColumn;


    /******************************************************************
     * The default constructor
     *****************************************************************/
    public Move() {
    }


    /******************************************************************
     * Constructor that initializes the move specification instance
     * variables
     * @param fromRow The row in which a chess piece is moving from
     * @param fromColumn The column in which a chess piece is moving
     * from
     * @param toRow The row in which a chess piece is moving to
     * @param toColumn The column in which a chess piece is moving to
     *****************************************************************/
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }


    /******************************************************************
     * Returns a String that details a Move's specifications
     * @return A String that details a Move's specifications
     *****************************************************************/
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" +
                fromColumn + ", toRow=" + toRow + ", toColumn=" +
                toColumn + "]";
    }
}