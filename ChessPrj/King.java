package ChessPrj;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;

        if((move.fromRow+1 == move.toRow)&&(move.fromColumn == move.toColumn))
            valid = true;
        else if((move.fromRow+1 == move.toRow)&&(move.fromColumn+1 == move.toColumn))
            valid = true;
        else if((move.fromRow == move.toRow)&&(move.fromColumn+1 == move.toColumn))
            valid = true;
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn+1 == move.toColumn))
            valid = true;
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn == move.toColumn))
            valid = true;
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn-1 == move.toColumn))
            valid = true;
        else if((move.fromRow == move.toRow)&&(move.fromColumn-1 == move.toColumn))
            valid = true;
        else if((move.fromRow+1 == move.toRow)&&(move.fromColumn-1 == move.toColumn))
            valid = true;

        return valid&&superValid;
    }
}
