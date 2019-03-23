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

    // Test Valid piece movements 1 move
    @Test
    public void test_piece_Move() {
        ChessModel test = new ChessModel();

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

        //assertTrue(test.pieceAt(2,1).type().equals("Pawn"));
        //assertTrue(test.pieceAt(2,1).player() == Player.BLACK);

    }
}
