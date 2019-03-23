package ChessPrj;

import org.junit.Test;

import java.util.InvalidPropertiesFormatException;

import static org.junit.Assert.*;

/**********************************************************************
 * Test class for Chess Model checking that the game of chess plays
 * how it should. Covers the entire class checking important moves
 *********************************************************************/
public class ChessModelTest {

    // Testing Board and Piece initialization
    @Test
    public void test_ChessModel(){
        ChessModel test = new ChessModel();

        assertTrue(test.numRows() == 8);
        assertTrue(test.numColumns() == 8);
        // Assert the pieces are placed Properly

        // Top of board Black Piece Positions and owner:
        assertTrue(test.pieceAt(0,0).type().equals("Rook"));
        assertTrue(test.pieceAt(0,1).type().equals("Knight"));
        assertTrue(test.pieceAt(0,2).type().equals("Bishop"));
        assertTrue(test.pieceAt(0,3).type().equals("Queen"));
        assertTrue(test.pieceAt(0,4).type().equals("King"));
        assertTrue(test.pieceAt(0,5).type().equals("Bishop"));
        assertTrue(test.pieceAt(0,6).type().equals("Knight"));
        assertTrue(test.pieceAt(0,7).type().equals("Rook"));
        for (int i = 0; i < test.numColumns(); i++){
            assertTrue(test.pieceAt(1, i).type().equals("Pawn"));
            assertTrue(test.pieceAt(0,i).player() == Player.BLACK);
            assertTrue(test.pieceAt(1,i).player() == Player.BLACK);
        }

        //Bottom of board White Piece Positions and owner
        assertTrue(test.pieceAt(7,0).type().equals("Rook"));
        assertTrue(test.pieceAt(7,1).type().equals("Knight"));
        assertTrue(test.pieceAt(7,2).type().equals("Bishop"));
        assertTrue(test.pieceAt(7,3).type().equals("Queen"));
        assertTrue(test.pieceAt(7,4).type().equals("King"));
        assertTrue(test.pieceAt(7,5).type().equals("Bishop"));
        assertTrue(test.pieceAt(7,6).type().equals("Knight"));
        assertTrue(test.pieceAt(7,7).type().equals("Rook"));
        for (int i = 0; i < test.numColumns(); i++){
            assertTrue(test.pieceAt(6,i).type().equals("Pawn"));
            assertTrue(test.pieceAt(6,i).player() == Player.WHITE);
            assertTrue(test.pieceAt(7,i).player() == Player.WHITE);
        }
    }

    // Tests player switches
    @Test
    public void test_nextPlayer() {
        ChessModel test = new ChessModel();
        Player currentPlayer;
        // Tests 4 player switches
        for (int i = 0; i < 4; i++) {
            test.setNextPlayer();
            currentPlayer = test.currentPlayer();
            assertEquals(currentPlayer, test.currentPlayer());
        }
    }

    // Test Good movements for Black Player
    @Test
    public void test_Black_piece_Move() {
        ChessModel test = new ChessModel();

        /** Pawn movement */

            // forward 1 space
        Move p = new Move (1,0,2,0);
        assertTrue(test.isValidMove(p));

            // forward 2 spaces
        p = new Move(1,0,3,0);
        assertTrue(test.isValidMove(p));

            // diagonal move
        p = new Move(1,0,2,1);
        assertFalse(test.isValidMove(p));

        // move pawn out of rooks way
        p = new Move(1,0,5,5);
        test.move(p);

        // Does moving a pawn actually put it there?
        p = new Move (1,7,2,7);
        test.move(p);
        assertTrue(test.pieceAt(2,7).type().equals("Pawn"));

        /** Rook Movement */

            // Vertical Movement
        Move r = new Move(0,0,4,0);
        assertTrue(test.isValidMove(r));

        // Testing movement
        test.move(r);
        assertTrue(test.pieceAt(4,0).type().equals("Rook"));

            // Horizontal Movement
        r = new Move(4,0,4,4);
        assertTrue(test.isValidMove(r));

        /** Knight Movement */

        Move k = new Move(0,1,2,2);
        assertTrue(test.isValidMove(k));

            // Move out of Queens way
        test.move(k);
        assertTrue(test.pieceAt(2,2).type().equals("Knight"));

            // Move Pawn out of way;
        p = new Move(1,1,5,5);
        test.move(p);

        /** Bishop Movement */
        Move b = new Move(0,2,1,1);
        assertTrue(test.isValidMove(b));

            // Move out of Queens Way
        test.move(b);
        assertTrue(test.pieceAt(1,1).type().equals("Bishop"));

        /** Queen Movement */
        Move q = new Move (0,3,0,0);
        assertTrue(test.isValidMove(q));

        // move out of kings way
        test.move(q);
        assertTrue(test.pieceAt(0,0).type().equals("Queen"));

        // Move pawn out of way;
        p = new Move(1,3,5,5);
        test.move(p);

        /** King Movement */

            // Horizontal movement
        Move king = new Move(0,4,0,3);
        assertTrue(test.isValidMove(king));

            // Diagonal movement
        king = new Move(0,4,1,3);
        assertTrue(test.isValidMove(king));

        test.move(king);
        assertTrue(test.pieceAt(1,3).type().equals("King"));
    }

    // Test Good movements for White player
    @Test
    public void test_White_piece_Move() {
        ChessModel test = new ChessModel();

        /** Pawn movement */

        // forward 1 space
        Move p = new Move (6,0,5,0);
        assertTrue(test.isValidMove(p));

        // forward 2 spaces
        p = new Move(6,0,4,0);
        assertTrue(test.isValidMove(p));

        // diagonal move
        p = new Move(6,0,5,1);
        assertFalse(test.isValidMove(p));

        // move pawn out of rooks way
        p = new Move(6,0,4,4);
        test.move(p);

        // Does moving a pawn actually put it there?
        p = new Move (6,7,5,7);
        test.move(p);
        assertTrue(test.pieceAt(5,7).type().equals("Pawn"));

        /** Rook Movement */

        // Vertical Movement
        Move r = new Move(7,0,4,0);
        assertTrue(test.isValidMove(r));

        // Testing movement
        test.move(r);
        assertTrue(test.pieceAt(4,0).type().equals("Rook"));

        // Horizontal Movement
        r = new Move(4,0,4,3);
        assertTrue(test.isValidMove(r));

        /** Knight Movement */

        Move k = new Move(7,1,5,2);
        assertTrue(test.isValidMove(k));

        // Move out of Queens way
        test.move(k);
        assertTrue(test.pieceAt(5,2).type().equals("Knight"));

        // Move Pawn out of way;
        p = new Move(6,1,4,4);
        test.move(p);

        /** Bishop Movement */
        Move b = new Move(7,2,6,1);
        assertTrue(test.isValidMove(b));

        // Move out of Queens Way
        test.move(b);
        assertTrue(test.pieceAt(6,1).type().equals("Bishop"));

        /** Queen Movement */
        Move q = new Move (7,3,7,0);
        assertTrue(test.isValidMove(q));

        // move out of kings way
        test.move(q);
        assertTrue(test.pieceAt(7,0).type().equals("Queen"));

        // Move pawn out of way;
        p = new Move(6,3,5,5);
        test.move(p);

        /** King Movement */

        // Horizontal movement
        Move king = new Move(7,4,7,3);
        assertTrue(test.isValidMove(king));

        // Diagonal movement
        king = new Move(7,4,6,3);
        assertTrue(test.isValidMove(king));

        test.move(king);
        assertTrue(test.pieceAt(6,3).type().equals("King"));
    }

    // Testing Valid Moves for a Pawn
    @Test
    public void test_Pawn_Movement(){
        ChessModel test = new ChessModel();

        /** Black Player movement */
        // 1 space forward
        Move m = new Move(1,1,2,1);
        assertTrue(test.isValidMove(m));
        // 2 space forward from start
        // Is this the pawns first move?
        ((Pawn) test.pieceAt(1,1)).setFirstMove(true);
        assert(((Pawn) test.pieceAt(1,1)).getFirstMove() == true);
        Move m2 = new Move (1,1,3,1);
        assertTrue(test.isValidMove(m2));

        // Cannot move backwards
        Move m3 = new Move (1,1,0,1);
        assertFalse(test.isValidMove(m3));
            // Back to the left?
        m3 = new Move(1,1,0,0);
        assertFalse(test.isValidMove(m3));
            // Back to the Right?
        m3 = new Move (1,1,0,2);
        assertFalse(test.isValidMove(m3));
        //cannot move diagonal with no piece there.

        // Move white pawn for enPassant
        Move p = new Move (6,0,3,0);
        test.move(p);
        test.move(m2);
        // EnPassant should be possible
        ((Pawn) test.pieceAt(3,1)).setFirstMove(true);
        p = new Move (3,0,2,1);
        assertTrue(test.isValidMove(p));

    }

    @Test
    public void test_canCastle() {
        ChessModel test = new ChessModel();

        Move knightMove = new Move(0, 1, 2, 0);
        test.move(knightMove);
        Move bishopMove = new Move(0, 2, 1, 3);
        test.move(bishopMove);
        Move queenMove = new Move(0, 3, 1, 3);
        test.move(queenMove);

        Move kingMove = new Move(0, 4, 0, 2);
        assertTrue(test.isValidMove(kingMove));
    }

    @Test
    public void test_Undo() {
        ChessModel test = new ChessModel();

        Move knightMove = new Move(0, 1, 5, 5);
        Move bishopMove = new Move(0, 2, 4, 4);
        assertEquals(test.goToLastBoard(), true);

        test.move(knightMove);
        test.saveBoard();
        test.move(bishopMove);
        test.saveBoard();
        assertEquals(test.goToLastBoard(), false);
    }

}
