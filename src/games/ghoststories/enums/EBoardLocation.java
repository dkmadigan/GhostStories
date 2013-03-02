package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Represents the possible locations of the player boards
 */
public enum EBoardLocation {
   TOP(R.id.top_board, R.id.top_token_area),
   BOTTOM(R.id.bottom_board, R.id.bottom_token_area),
   RIGHT(R.id.right_board, R.id.right_token_area),
   LEFT(R.id.left_board, R.id.left_token_area);
   
   /**
    * Constructor
    * @param pBoardId The id of the top board layout
    * @param pTokenAreaId The id of the top token area layout
    */
   private EBoardLocation(int pBoardId, int pTokenAreaId) {
      mBoardId = pBoardId;
      mTokenAreaId = pTokenAreaId;
   }
   
   /**
    * @return The layout id of the board
    */
   public int getBoardId() {
      return mBoardId;
   }
   
   /**
    * @return The layout id of the token area
    */
   public int getTokenAreaId() {
      return mTokenAreaId;
   }
   
   /** The layout id of the board **/
   private final int mBoardId;
   /** The layout id of the token area **/
   private final int mTokenAreaId;
}
