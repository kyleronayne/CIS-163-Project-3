package ChessPrj;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;
    private int boardSize = 8;

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
                (move.fromColumn == move.toColumn)) == true)
            return false;
        //Verifies that the piece is in the position its moving from
        //FIXME: Not sure how this will react if the beginning spot
        //FIXME: has nothing in it
        else if(!(board[move.fromRow][move.fromColumn].type().equals(
                this.type())))
            throw new IllegalArgumentException();
        //Verifies that the piece is not moving to a spot occupied
        //by the same player
        else if((board[move.toRow][move.toColumn].player().equals(
                this.player())))
            return false;
        else if((move.toRow > boardSize-1) || (move.toRow < 0) ||
                (move.toColumn > boardSize-1) || (move.toColumn < 0))
            throw new IndexOutOfBoundsException();
        //If none of the above are true, the move is valid
        return true;
    }
}
