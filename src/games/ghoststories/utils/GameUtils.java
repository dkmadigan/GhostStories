package games.ghoststories.utils;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import android.os.Looper;

public class GameUtils {
   public static boolean isUIThread() {
      return (Looper.getMainLooper() == Looper.myLooper());
   }
   
   public static boolean isSpaceValid(GhostData pCard, GameBoardData pBoardData,
         ECardLocation pLocation) {
      //If the top card is my color OR is black and it is my turn then
      //highlight
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      if(pBoardData.getGhostData(pLocation) == null && 
            pCard.getColor() == pBoardData.getColor() ||
            (pCard.getColor() == EColor.BLACK && 
                  gm.getCurrentPlayerData().getColor() == pBoardData.getColor())) {
         return true;
      }
      return false;
   }
}
