package ChessPrj;
import java.lang.*;

public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean superValid = super.isValidMove(move, board);

        boolean valid = false;
        if((move.fromRow == move.toRow) || (move.fromColumn == move.toColumn))
            valid = false;

        if((Math.abs(move.fromRow - move.toRow) == Math.abs(move.fromColumn - move.toColumn)))
            valid = true;

        return valid;
    }
}