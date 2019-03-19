package ChessPrj;
import java.util.ArrayList;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private GUIcodes gameStatus;
    private ChessPiece newPiece;
    private ArrayList<IChessPiece[][]> prevBoard;
    private IChessPiece[][] startBoard;
    private boolean hasUndoneToStart = false;

    // declare other instance variables as needed

    public ChessModel() {
        board = new IChessPiece[8][8];
        prevBoard = new ArrayList<>();
        startBoard = new IChessPiece[8][8];
        prevBoard.clear();
        player = Player.WHITE;
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        for(int i=0; i<8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
            board[1][i] = new Pawn(Player.BLACK);
        }
        startBoard = generateNewBoard();
        gameStatus = GUIcodes.NoMessage;
    }

    //FIXME: Something ain't working right here
    public boolean isComplete() {
        if(currentPlayer() == Player.WHITE) {
            for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if((pieceAt(r, c) != null))
                    if((pieceAt(r, c).player()==Player.WHITE))
                    for(int x=0; x<numRows(); x++)
                        for(int y=0; y<numColumns(); y++) {
                            Move m = new Move(r, c, x, y);
                            Move m2 = new Move(x, y, r, c);
                            if(pieceAt(r, c).isValidMove(m, board))    {
                                newPiece = null;
                                if(pieceAt(x, y) != null)   {
                                    //Want a new black piece
                                    if(pieceAt(x, y).type().equals("King"))
                                        newPiece = new King(Player.BLACK);
                                    else if(pieceAt(x, y).type().equals("Queen"))
                                        newPiece = new Queen(Player.BLACK);
                                    else if(pieceAt(x, y).type().equals("Bishop"))
                                        newPiece = new Bishop(Player.BLACK);
                                    else if(pieceAt(x, y).type().equals("Knight"))
                                        newPiece = new Knight(Player.BLACK);
                                    else if(pieceAt(x, y).type().equals("Rook"))
                                        newPiece = new Rook(Player.BLACK);
                                    else if(pieceAt(x, y).type().equals("Pawn"))
                                        newPiece = new Pawn(Player.BLACK);
                                    else
                                        newPiece = null;
                                }
                                move(m);
                                if(inCheck(currentPlayer().next()))    {
                                    move(m2);
                                    board[x][y] = newPiece;
                                }
                                else    {
                                    move(m2);
                                    board[x][y] = newPiece;
                                    return false;
                                }

                            }
                        }}
        else {
                        for (int r1 = 0; r1 < numRows(); r1++)
                            for (int c1 = 0; c1 < numColumns(); c1++)
                                if ((pieceAt(r1, c1) != null))
                                    if ((pieceAt(r1, c1).player() == Player.BLACK)) {
                                        for (int x = 0; x < numRows(); x++)
                                            for (int y = 0; y < numColumns(); y++) {
                                                Move m = new Move(r1, c1, x, y);
                                                Move m2 = new Move(x, y, r1, c1);
                                                if(pieceAt(r1, c1).isValidMove(m, board))    {
                                                    newPiece = null;
                                                    if(pieceAt(x, y) != null)   {
                                                        //Want a new white piece
                                                        if(pieceAt(x, y).type().equals("King"))
                                                            newPiece = new King(Player.WHITE);
                                                        else if(pieceAt(x, y).type().equals("Queen"))
                                                            newPiece = new Queen(Player.WHITE);
                                                        else if(pieceAt(x, y).type().equals("Bishop"))
                                                            newPiece = new Bishop(Player.WHITE);
                                                        else if(pieceAt(x, y).type().equals("Knight"))
                                                            newPiece = new Knight(Player.WHITE);
                                                        else if(pieceAt(x, y).type().equals("Rook"))
                                                            newPiece = new Rook(Player.WHITE);
                                                        else if(pieceAt(x, y).type().equals("Pawn"))
                                                            newPiece = new Pawn(Player.WHITE);
                                                        else
                                                            newPiece = null;
                                                    }
                                                    move(m);
                                                    if(inCheck(currentPlayer().next()))    {
                                                        move(m2);
                                                        board[x][y] = newPiece;
                                                    }
                                                    else    {
                                                        move(m2);
                                                        board[x][y] = newPiece;
                                                        return false;
                                                    }

                                                }
                                            }
                                    }
                    }
        gameStatus = GUIcodes.Checkmate;
        return true;
    }

    public boolean isValidMove(Move move) {
        boolean valid = false;

        if (board[move.fromRow][move.fromColumn] != null)
            valid = (board[move.fromRow][move.fromColumn].isValidMove(move, board));

        return valid;
    }

    public void move(Move move) {
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
        player = player.next();

    }

    public boolean inCheck(Player p) {
        int kingRow = -3;
        int kingCol = -1;
        // Replace kingCaptured boolean with checkmate method when
        // finished

        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null)
                    if(pieceAt(r, c).player() == p)
                        if(pieceAt(r, c).type().equals("King")){
                            kingRow = r;
                            kingCol = c;

                        }

        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null) {
                    if (pieceAt(r, c).player() == p.next()) {
                            Move move = new Move(r, c, kingRow, kingCol);
                            if (pieceAt(r, c).isValidMove(move, board)) {
                                gameStatus = GUIcodes.inCheck;
                               return true;
                            }
                    }
                }


        return false;
    }

    public boolean inCheck(Player p, int row, int col) {
        int kingRow = row;
        int kingCol = col;
        // Replace kingCaptured boolean with checkmate method when
        // finished

        for(int r=0; r<numRows(); r++)
            for(int c=0; c<numColumns(); c++)
                if(pieceAt(r, c) != null) {
                    if (pieceAt(r, c).player() == p.next()) {
                            Move move = new Move(r, c, kingRow, kingCol);
                            if (pieceAt(r, c).isValidMove(move, board)) {
                                gameStatus = GUIcodes.inCheck;
                                return true;
                            }
                    }
                }


        return false;
    }


    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return 8;
    }

    public int numColumns() {
        return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    public void setNextPlayer() {
        player = player.next();
    }

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void saveBoard() {
        prevBoard.add(generateNewBoard());
    }
    //FIXME: If you move, then undo to beginning, then move, undo again, it will put you to the last spot...
    public boolean goToLastBoard() {
        System.out.println(prevBoard.size());
        if((prevBoard.size() <=1) || ((prevBoard.size() == 2) && (hasUndoneToStart)))   {
            prevBoard.clear();
            prevBoard.add(generateNewBoard(startBoard));
            board = prevBoard.get(0);
            player = Player.WHITE;
            hasUndoneToStart = true;
            return true;
        }

        else  {
            board = prevBoard.get(prevBoard.size()-1);
            prevBoard.remove(prevBoard.size()-1);
            setNextPlayer();
            return false;
        }
    }

    private IChessPiece[][] generateNewBoard()  {
        IChessPiece[][] copy = new IChessPiece[8][8];

        for(int i=0; i<numRows(); i++)
            for(int j=0; j<numColumns(); j++) {
                if(board[i][j] != null)
                    copy[i][j] = board[i][j];
            }
        return copy;
    }

    private IChessPiece[][] generateNewBoard(IChessPiece[][] arrayToCopy)  {
        IChessPiece[][] copy = new IChessPiece[8][8];

        for(int i=0; i<numRows(); i++)
            for(int j=0; j<numColumns(); j++) {
                if(arrayToCopy[i][j] != null)
                    copy[i][j] = arrayToCopy[i][j];
            }
        return copy;
    }

    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */

    }
}
