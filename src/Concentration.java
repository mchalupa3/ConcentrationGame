/**
 * The game of Concentration (also called Memory or Match Match)
 *
 * Create a gameboard of tiles. Each tile contains a card that has exactly
 * one match on the board. Cards are originally show "face down" on the board.
 *
 * Player chooses two random cards from the board. The chosen cards
 * are temporarily shown face up. If the cards match, they are removed from board.
 *
 * Play continues, matching two cards at a time, until all cards have been matched.
 */
import java.util.Random;
public class Concentration extends Board
{
    Random rand = new Random();


    public static final int CONCENTRATION = 100;
    public static final int SEVENS = 200;
    private int gamerules;
    // create the game board
    private static Tile[][] gameboard = makeBoard();

    /**
     * The constructor for the game. Creates the 2-dim gameboard
     * by populating it with tiles.
     */
    public Concentration() {
        String[] cards = getCards();
        int numCards = cards.length - 1;

        for(int i = 0; i < gameboard.length; i++){
            for(int j = 0; j < gameboard[0].length; j++ ){
                int n = (int)(Math.random() * numCards);
                gameboard[i][j] = new Tile(cards[n]);
                cards[n] = cards[numCards];
                numCards = numCards - 1;
            }
        }

    }
    /**
     * Determine if the board is full of cards that have all been matched,
     * indicating the game is over
     *
     * Precondition: gameboard is populated with tiles
     *
     * @return true if all pairs of cards have been matched, false otherwse
     */
    public boolean allTilesMatch() {

        for(int i=0; i < gameboard.length; i++){
            for (int j=0; j < gameboard[0].length; j++){
                if(!gameboard[i][j].matched())
                    return false;
            }
        }

        return true;
    }

    /**
     * Check for a match between the cards in the two tile locations.
     * For matched cards, remove from the board. For unmatched cares, them face down again.
     *
     * Precondition: gameboard is populated with tiles,
     * row values (i values) must be in the range of 0 to gameboard.length,
     * column values (j values) must be in the range of 0 to gameboard[0].length
     *
     * @param row1 the row value of Tile 1
     * @param column1 the column value of Tile 1
     * @param row2 the row value of Tile 2
     * @param column2 the column vlue of Tile 2
     * @return a message indicating whether or not a match occured
     */
    public String checkForMatch(int row1, int column1, int row2, int column2) {
        boolean tilesMatch = false;
        String msg = "";

        Tile tile1 = gameboard[row1][column1];
        Tile tile2 = gameboard[row2][column2];

        if(gamerules == CONCENTRATION){
            tilesMatch = tile1.equals(tile2);
        }
        if(SEVENS == gamerules){
            tilesMatch = tile1.addsTo7(tile2);
        }
        if(tilesMatch == true){
            msg ="Match";
        }
        else {
            tile1.matched = false;
            tile2.matched = false;
        }



        return msg;
    }

    /**
     * Set  tile to show its card in the face up state
     *
     * Precondition: gameboard is populated with tiles,
     * row values (i values) must be in the range of 0 to gameboard.length,
     * column values (j values) must be in the range of 0 to gameboard[0].length
     *
     * @param row the row value of Tile
     * @param column the column value of Tile
     */
    public void showFaceUp (int row, int column) {

        Tile tile = gameboard[row][column];
        tile.faceUp(true);
        tile.faceUp(true);
    }

    /**
     * Returns a string representation of the board. A tab is placed between tiles,
     * and a newline is placed at the end of a row
     *
     * Precondition: gameboard is populated with tiles
     *
     * @return a string represetation of the board
     */
    public String toString() {

        String boardValues="";
        for(int i =0; i< gameboard.length; i++){
            for(int j =0; j<gameboard[0].length; j++){
                Tile t = gameboard[i][i];
                if(t.isFaceUp()) boardValues = t.getFace() + boardValues;
                else boardValues += t.getBack();
                boardValues += "\t";
            }
            boardValues += "\n";
        }

        return boardValues;
    }

    public boolean validSelection(int i, int j){
        if (i< gameboard.length){
            if(j<gameboard.length){
                if(!gameboard[i][j].matched())
                    return true;
            }
        }

        return false;
    }

}

