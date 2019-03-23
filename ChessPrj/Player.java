package ChessPrj;

/**********************************************************************
 * Enum class that defines the Player color constraint
 *********************************************************************/
public enum Player {
    // Denotes the black player
    BLACK,

    // Denotes the white player
    WHITE;

    /******************************************************************
     * Returns the next Player constraint
     * @return A constraint for the next Player
     *****************************************************************/
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;
    }
}
