package ChessPrj;

public class King extends ChessPiece {

    private boolean WkingMoved;
    private boolean BkingMoved;

    public King(Player player) {
        super(player);
        WkingMoved = false;
        BkingMoved = false;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
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
            if(board[move.fromRow][move.fromColumn] != null)
                if(board[move.fromRow][move.fromColumn].type().equals("King"))
                    if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
                        WkingMoved = true;
                    else
                        BkingMoved = true;
            valid = true;
        }
        else if (move.toColumn == move.fromColumn - 2 || move.toColumn == move.fromColumn + 2) {
            if (this.player() == Player.WHITE) {
                if (!WkingMoved) {
                    if(move.toColumn == move.fromColumn +2) {
                        if(board[7][7] != null)
                            if(board[7][7].type().equals("Rook"))
                                if(board[7][5] == null && board[7][6] == null)  {
                                    WkingMoved=true;
                                    valid = true;
                                }
                    }
                    else if(move.toColumn == move.fromColumn-2) {
                        if(board[7][0] != null)
                            if(board[7][0].type().equals("Rook"))
                                if(board[7][1] == null && board[7][2] == null && board[7][3]==null) {
                                    WkingMoved = true;
                                    valid = true;
                                }
                    }

                }
            }

            if (this.player() == Player.BLACK) {
                System.out.println(BkingMoved);
                if (!BkingMoved) {
                    if(move.toColumn == move.fromColumn-2)  {
                        if(board[0][0] != null)
                            if(board[0][0].type().equals("Rook"))
                                if(board[0][1] == null & board[0][2] == null) {
                                    BkingMoved = true;
                                    valid = true;
                                }
                    }
                    else if(move.toColumn == move.fromColumn+2) {
                        if(board[0][7] != null)
                            if(board[0][7].type().equals("Rook"))
                                if(board[0][4]==null && board[0][5]==null && board[0][6]==null) {
                                    BkingMoved = true;
                                    valid = true;
                                }
                    }
                }
            }
        }


        return valid&&superValid;
    }
}
