package ChessPrj;

public class Pawn extends ChessPiece {

    public Pawn(Player player) {
        super(player);
    }

    public String type() {
        return "Pawn";
    }

    // determines if the move is valid for a pawn piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean superValid = super.isValidMove(move, board);
        boolean validMove = false;


        if (this.player() == Player.WHITE) {
            if (move.fromRow == 6 && move.toRow == move.fromRow - 2 &&
                    move.toColumn == move.fromColumn) {
                if (board[move.fromRow - 1][move.toColumn] == null
                        && board[move.fromRow - 2][move.toColumn]
                        == null) {
                    validMove = true;
                }
            }

            if ((move.toRow == move.fromRow - 1) && (move.toColumn ==
                    move.fromColumn)) {
                if (board[move.toRow][move.toColumn] == null) {
                    validMove = true;
                }
            }
            else if ((move.toRow == move.fromRow - 1) &&
                    (move.toColumn == move.fromColumn + 1) ||
                    (move.toColumn == move.fromColumn - 1)) {
                try {
                    if(board[move.toRow][move.toColumn].player() ==
                            Player.BLACK) {
                        validMove = true;
                    }
                }
                catch(NullPointerException e){}
            }
        }


        if (this.player() == Player.BLACK)   {
            if ((move.fromRow == 1) && (move.toRow == move.fromRow + 2)
                    && (move.toColumn == move.fromColumn)) {
                if ((board[move.fromRow + 1][move.toColumn] == null) &&
                        board[move.fromRow + 2][move.fromColumn] == null) {
                    validMove = true;
                }
            }

            if ((move.toRow == move.fromRow + 1) && (move.toColumn ==
                    move.fromColumn)) {
                if (board[move.toRow][move.toColumn] == null) {
                    validMove = true;
                }
            }

            else if ((move.toRow == move.fromRow + 1) &&
                    (move.toColumn == move.fromColumn + 1)
                    || (move.toColumn == move.fromColumn - 1)) {
                try {
                    if (board[move.toRow][move.toColumn].player() ==
                            Player.WHITE)
                        validMove = true;
                }
                catch (NullPointerException e) {}
            }
        }

        return validMove&&superValid;
    }
}