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

        if((Math.abs(move.fromRow - move.toRow) == Math.abs(move.fromColumn - move.toColumn))) {
            valid = true;

            if((move.fromRow > move.toRow) && (move.fromColumn > move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow-i][move.fromColumn-i] == null))
                        return false;
                }
            }
            else if((move.fromRow < move.toRow) && (move.fromColumn < move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow+i][move.fromColumn+i] == null))
                        return false;
                }
            }
            else if((move.fromRow > move.toRow) && (move.fromColumn < move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow-i][move.fromColumn+i] == null))
                        return false;
                }
            }
            else if((move.fromRow < move.toRow) && (move.fromColumn > move.toColumn)) {
                for(int i=1; i<Math.abs(move.fromRow - move.toRow); i++)    {
                    if(!(board[move.fromRow+i][move.fromColumn-i] == null))
                        return false;
                }
            }
        }


        return valid&&superValid;
    }
}