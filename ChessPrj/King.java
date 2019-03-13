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
        int r,c;
        int boardSize = 8;
        if((move.fromRow+1 == move.toRow)&&(move.fromColumn == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow+1 == move.toRow)&&(move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow == move.toRow)&&(move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn+1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow-1 == move.toRow)&&(move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow == move.toRow)&&(move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }
        else if((move.fromRow+1 == move.toRow)&&(move.fromColumn-1 == move.toColumn)) {
            for(r=0; r<boardSize; r++)
                for(c=0; c<boardSize; c++)
                    if(board[r][c] != null)
                        if(board[r][c].player() != super.player()) {
                            Move newMove = new Move(r,c,move.toRow,move.toColumn);
                            if(board[r][c].isValidMove(newMove, board))
                                return false;
                        }
            valid = true;
        }

        return valid&&superValid;
    }
}
