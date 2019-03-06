package ChessPrj;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //Verifies that starting and ending locations are different
        if (((move.fromRow == move.toRow) &&
                (move.fromColumn == move.toColumn)) == true)
            return false;
        else if(!(board[move.fromRow][move.fromColumn].type().equals(this.type())))
            return false;




        return false;
    }
}
