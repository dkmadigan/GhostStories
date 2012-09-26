package games.ghoststories.enums;

import games.ghoststories.R;

public enum EBoardLocation {
   TOP(R.id.top_board, R.id.top_token_area),
   BOTTOM(R.id.bottom_board, R.id.bottom_token_area),
   RIGHT(R.id.right_board, R.id.right_token_area),
   LEFT(R.id.left_board, R.id.left_token_area);
   
   private EBoardLocation(int pBoardId, int pTokenAreaId) {
      mBoardId = pBoardId;
      mTokenAreaId = pTokenAreaId;
   }
   
   public int getBoardId() {
      return mBoardId;
   }
   
   public int getTokenAreaId() {
      return mTokenAreaId;
   }
   
   private final int mBoardId;
   private final int mTokenAreaId;
}
