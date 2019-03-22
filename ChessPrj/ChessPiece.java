package ChessPrj;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;
    private int boardSize = 8;
    private int wKingRow;
    private int wKingCol;
    private int bKingRow;
    private int bKingCol;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        //Verifies that starting and ending locations are different
        if (((move.fromRow == move.toRow) &&
                (move.fromColumn == move.toColumn)))
            return false;
        //FIXME: NEED TO DOUBLE CHECK FOR INVALID ARGUMENT

        else if((move.toRow > boardSize-1) || (move.toRow < 0) ||
                (move.toColumn > boardSize-1) || (move.toColumn < 0)) {
            return false;
            //FIXME: I think we need to throw an error but its not
            //working currently
            //throw new IndexOutOfBoundsException();

        }
        try {
             if ((board[move.toRow][move.toColumn].player().equals(
                    this.player())))
                return false;
        }
        catch(NullPointerException e){

        }



        //If none of the above are true, the move is valid
        return true;
    }

}
