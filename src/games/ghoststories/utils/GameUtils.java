package games.ghoststories.utils;

import java.util.ArrayList;
import java.util.List;

import com.utils.AndroidUtils;
import com.utils.ImageRotationTask;
import com.utils.ImageViewUtils;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.village.VillageTileData;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.enums.ETileLocation;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

/**
 * Generic game level utility methods
 */
public abstract class GameUtils {
   
   /**
    * Gets the set of ghosts that are adjacent to the given 
    * {@link ETileLocation}
    * @param pTile The tile to get the adjacent ghosts for
    * @return A list of ghosts that are adjacent. This list will have 0, 1 or
    *         2 ghosts in it.
    */
   public static List<GhostData> getAdjacentGhosts(ETileLocation pTile) {
      List<GhostData> ghosts = new ArrayList<GhostData>();
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      GhostData gd1 = null;
      GhostData gd2 = null;
      switch(pTile) {
      case TOP_LEFT:
         gd1 = gm.getGameBoard(EBoardLocation.TOP).getGhostData(ECardLocation.RIGHT);
         gd2 = gm.getGameBoard(EBoardLocation.TOP).getGhostData(ECardLocation.RIGHT);         
         break;
      case TOP_CENTER:
         gd1 = gm.getGameBoard(EBoardLocation.TOP).getGhostData(ECardLocation.MIDDLE);
         break;
      case TOP_RIGHT:
         gd1 = gm.getGameBoard(EBoardLocation.TOP).getGhostData(ECardLocation.LEFT);
         gd2 = gm.getGameBoard(EBoardLocation.RIGHT).getGhostData(ECardLocation.RIGHT);
         break;
      case MIDDLE_LEFT:
         gd1 = gm.getGameBoard(EBoardLocation.LEFT).getGhostData(ECardLocation.MIDDLE);
         break;
      case MIDDLE_CENTER:
         break;
      case MIDDLE_RIGHT:
         gd1 = gm.getGameBoard(EBoardLocation.RIGHT).getGhostData(ECardLocation.MIDDLE);
         break;
      case BOTTOM_LEFT:
         gd1 = gm.getGameBoard(EBoardLocation.LEFT).getGhostData(ECardLocation.RIGHT);
         gd2 = gm.getGameBoard(EBoardLocation.BOTTOM).getGhostData(ECardLocation.LEFT);         
         break;
      case BOTTOM_CENTER:
         gd1 = gm.getGameBoard(EBoardLocation.BOTTOM).getGhostData(ECardLocation.MIDDLE);
         break;
      case BOTTOM_RIGHT:
         gd1 = gm.getGameBoard(EBoardLocation.RIGHT).getGhostData(ECardLocation.LEFT);
         gd2 = gm.getGameBoard(EBoardLocation.BOTTOM).getGhostData(ECardLocation.RIGHT);         
         break;
      }
      if(gd1 != null) ghosts.add(gd1);
      if(gd2 != null) ghosts.add(gd2);
      return ghosts;
   }
   
   /**
    * Gets the list of village tiles that are hauntable given a particular 
    * {@link EBoardLocation} and {@link ECardLocation}. The hauntable tiles are
    * the three tiles that are directly in front of the given card location. 
    * This will return all tiles regardless of current haunting state.
    * @param pBoardLocation The board location
    * @param pCardLocation The card location
    * @return A list of size 3 that represents the village tiles that can 
    *         be haunted by the board/card combo. The list will be in order that
    *         the hauntings should occur.
    */
   public static List<VillageTileData> getHauntableVillageTiles(
         EBoardLocation pBoardLocation, ECardLocation pCardLocation) {
      List<VillageTileData> villageTiles = new ArrayList<VillageTileData>();
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      switch(pBoardLocation) {
      case BOTTOM:
         switch(pCardLocation) {
         case LEFT:
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_LEFT));
            break;
         case MIDDLE:
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_CENTER));
            break;
         case RIGHT:
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_RIGHT));
            break;
         }
         break;
      case RIGHT:
         switch(pCardLocation) {
         case LEFT:
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_LEFT));
            break;
         case MIDDLE:
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_LEFT));
            break;
         case RIGHT:
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_LEFT));
            break;
         }
         break;
      case TOP:
         switch(pCardLocation) {
         case LEFT:
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_RIGHT));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_RIGHT));
            break;
         case MIDDLE:
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_CENTER));
            break;
         case RIGHT:
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_LEFT));
            break;
         }
         break;
      case LEFT:
         switch(pCardLocation) {
         case LEFT:
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.TOP_RIGHT));
            break;
         case MIDDLE:
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.MIDDLE_RIGHT));
            break;
         case RIGHT:
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_LEFT));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_CENTER));
            villageTiles.add(gm.getVillageTile(ETileLocation.BOTTOM_RIGHT));
            break;
         }
         break;
      }
      return villageTiles;
   }
   
   /**
    * Gets the drawable id for the token of the given color
    * @param pColor The color of the token id to get
    * @return The drawable id for the token of the given color
    */
   public static int getTaoTokenId(EColor pColor) {
      int id = -1;
      switch(pColor) {
      case BLACK:
         id = R.drawable.tao_token_black;
         break;
      case BLUE:
         id = R.drawable.tao_token_blue;
         break;
      case GREEN:
         id = R.drawable.tao_token_green;
         break;
      case RED:
         id = R.drawable.tao_token_red;
         break;
      case YELLOW:
         id = R.drawable.tao_token_yellow;
         break;
      }
      return id;
   }
   
   /**
    * Haunts a village tile in front of the given board and card. Uses  
    * {@link #getHauntableVillageTiles(EBoardLocation, ECardLocation)} to get
    * the set of hauntable village tiles and haunts the next one.
    * @param pBoardLocation The board location
    * @param pCardLocation The card location
    */
   public static void hauntVillageTile(EBoardLocation pBoardLocation,  
         ECardLocation pCardLocation) {
      List<VillageTileData> villageTiles = GameUtils.getHauntableVillageTiles(
            pBoardLocation, pCardLocation);
      
      //Check the tiles in front of this card and see which one to haunt
      for(VillageTileData vtd : villageTiles) {            
         if(vtd.isActive()) {             
            vtd.setIsActive(false);               
            break;
         }
      }  
   }
   
   /**
    * Invalidates the given view. If on the UI thread, call 
    * {@link View#invalidate()}, else call {@link View#postInvalidate()}.
    * @param pView The view to invalidate
    */
   public static void invalidateView(View pView) {
      if(AndroidUtils.isUIThread()) {
         pView.invalidate();
      } else {
         pView.postInvalidate();
      }
   }
   
   /**
    * Whether or not a ghost at the given {@link EBoardLocation} and
    * {@link ECardLocation} is attackable from the given {@link ETileLocation}.
    * A ghost is attackable if it is adjacent to the player.
    * @param pBoardLocation The board location of the ghost
    * @param pCardLocation The card location of the ghost
    * @param pPlayerLocation The player location
    * @return <code>true</code> if attackable, <code>false</code> otherwise
    */
   public static boolean isGhostAttackable(EBoardLocation pBoardLocation,
         ECardLocation pCardLocation, ETileLocation pPlayerLocation) {
      boolean attackable = false;
      switch(pPlayerLocation) {
      case BOTTOM_CENTER:
         attackable = (pBoardLocation == EBoardLocation.BOTTOM && 
            pCardLocation == ECardLocation.MIDDLE);
         break;
      case BOTTOM_LEFT:
         attackable = ((pBoardLocation == EBoardLocation.BOTTOM && 
            pCardLocation == ECardLocation.LEFT) || 
               (pBoardLocation == EBoardLocation.LEFT && 
               pCardLocation == ECardLocation.RIGHT));
         break;
      case BOTTOM_RIGHT:
         attackable = ((pBoardLocation == EBoardLocation.BOTTOM && 
               pCardLocation == ECardLocation.RIGHT) ||
               (pBoardLocation == EBoardLocation.RIGHT && 
               pCardLocation == ECardLocation.LEFT));
         break;
      case MIDDLE_CENTER:
         break;
      case MIDDLE_LEFT:
         attackable = (pBoardLocation == EBoardLocation.LEFT && 
               pCardLocation == ECardLocation.MIDDLE);
         break;
      case MIDDLE_RIGHT:
         attackable = (pBoardLocation == EBoardLocation.RIGHT && 
               pCardLocation == ECardLocation.MIDDLE);
         break;
      case TOP_CENTER:
         attackable = (pBoardLocation == EBoardLocation.TOP && 
               pCardLocation == ECardLocation.MIDDLE);
         break;
      case TOP_LEFT:
         attackable = ((pBoardLocation == EBoardLocation.LEFT && 
               pCardLocation == ECardLocation.LEFT) ||
               (pBoardLocation == EBoardLocation.TOP && 
               pCardLocation == ECardLocation.RIGHT));
         break;              
      case TOP_RIGHT:
         attackable = ((pBoardLocation == EBoardLocation.RIGHT && 
               pCardLocation == ECardLocation.RIGHT) || 
               (pBoardLocation == EBoardLocation.TOP && 
               pCardLocation == ECardLocation.LEFT));
         break;
      }
      return attackable;
   }
   
   /**
    * Determines whether or not the ghost can be dropped on the specified 
    * board and card location. The space is valid if it does not have a 
    * @param pCard The ghost card data
    * @param pBoardData The board data
    * @param pLocation The card location
    * @return
    */
   public static boolean isSpaceValid(GhostData pCard, GameBoardData pBoardData,
         ECardLocation pLocation) {
      boolean valid = false;   
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      if(pBoardData.getGhostData(pLocation) == null) {                           
         //If the ghost is a black ghost it must go in front of the current
         //player if possible, else it can go anywhere there is no ghost
         if(pCard.getColor() == EColor.BLACK) {
            GameBoardData currentPlayerBoard = gm.getCurrentPlayerBoard();
            if(currentPlayerBoard.isBoardFilled()) {
               valid = true;
            } else {
               //Ghost must go in front of current player
               if(currentPlayerBoard.getColor() == pBoardData.getColor()) {
                  valid = true;
               }
            }
         } else {
            //If the ghost is not a black ghost it must go on the matching 
            //player board if possible, else it can go anywhere                     
            GameBoardData matchingBoard = gm.getGameBoard(pCard.getColor());
            if(matchingBoard.isBoardFilled()) {
               valid = true;
            } else if(pCard.getColor() == pBoardData.getColor()) {
               valid = true;
            }
         }
      }
      return valid;
   }       
   
   /**
    * Determines whether or not the village tile specified by the given tile
    * data is reachable by the given player.
    * @param pVillageData The village tile
    * @param pPlayerData The player
    * @return <code>true</code> if reachable, <code>false</code> if not reachable
    */
   public static boolean isVillageTileReachable(VillageTileData pVillageData,
         PlayerData pPlayerData) {      
      boolean reachable = false;
      //Dance of the Peaks allows player to move anywhere
      if(pPlayerData.getAbilityActive() && 
            pPlayerData.getAbility() == EPlayerAbility.DANCE_OF_THE_PEAKS) {
         reachable = true;
      } else {
         ETileLocation playerLoc = pPlayerData.getLocation();
         ETileLocation villageLoc = pVillageData.getLocation();
         if(playerLoc == villageLoc || 
               playerLoc.getAdjacentTiles().contains(villageLoc)) {
            reachable = true;
         }
      }
      return reachable;
   } 
   
   /**
    * Sets up an {@link ImageView} for a particular board. The 
    * {@link EBoardLocation#TOP} and {@link EBoardLocation#BOTTOM} boards
    * are already rotated so just set the bitmaps. The 
    * {@link EBoardLocation#LEFT} and {@link EBoardLocation#RIGHT} boards need
    * the images to be rotated prior to setting them.
    * @param pView The view to set the image of
    * @param pLocation The board location
    * @param pImageId The id of the image to set
    */
   public static void setBoardLocationResource(final ImageView pView, 
         final EBoardLocation pLocation, final int pImageId) {
      //The TOP and BOTTOM card locations are already rotated...only need to rotate
      //the LEFT and RIGHT ones.
      switch(pLocation) {
      case TOP:
      case BOTTOM:
         ImageViewUtils.setImageResource(pView, pImageId);
         break;
      case RIGHT:
         new ImageRotationTask(pView.getResources(), pImageId) {
            @Override
            protected void onPostExecute(Bitmap pResult) {
               pView.setImageBitmap(pResult);               
            }
         }.execute(270);
         break;
      case LEFT:
         new ImageRotationTask(pView.getResources(), pImageId) {
            @Override
            protected void onPostExecute(Bitmap pResult) {
               pView.setImageBitmap(pResult);
            }
         }.execute(90);
         break;
      }
   }
}
