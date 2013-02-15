package games.ghoststories.utils;

import java.util.ArrayList;
import java.util.List;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.ETileLocation;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class GameUtils {
   public static void invalidateView(View pView) {
      if(isUIThread()) {
         pView.invalidate();
      } else {
         pView.postInvalidate();
      }
   }
        
   public static boolean isUIThread() {
      return (Looper.getMainLooper() == Looper.myLooper());
   }
   
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
   
   public static boolean isVillageTileReachable(VillageTileData pVillageData,
         PlayerData pPlayerData) {
      //TODO Handle special player abilities
      boolean reachable = false;
      ETileLocation playerLoc = pPlayerData.getLocation();
      ETileLocation villageLoc = pVillageData.getLocation();
      if(playerLoc == villageLoc || 
            playerLoc.getAdjacentTiles().contains(villageLoc)) {
         reachable = true;
      }
      return reachable;
   }
   
   public static void runAnimation(final int pAnimationId, final View pView) {
      if(isUIThread()) {
         Animation anim = AnimationUtils.loadAnimation(pView.getContext(), 
               pAnimationId);
         pView.startAnimation(anim);
      } else {
         pView.post(new Runnable() {
            public void run() {
               runAnimation(pAnimationId, pView); 
            }                    
         });
      }
   }
   
   public static void translationXBy(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(isUIThread()) {
         pView.animate().setDuration(pDuration).translationXBy(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationXBy(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }
   
   public static void translationYBy(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(isUIThread()) {
         pView.animate().setDuration(pDuration).translationYBy(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationYBy(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }     
   
   public static void translationY(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(isUIThread()) {
         pView.animate().setDuration(pDuration).translationY(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationY(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }  
   
   public static void translationX(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(isUIThread()) {
         pView.animate().setDuration(pDuration).translationX(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationX(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }  
   
   public static void animateXY(final View pView, 
         final int pX, final int pY, final int pDuration, 
         final Runnable pEndAction) {
      if(isUIThread()) {
         pView.animate().setDuration(pDuration).x(
               pX).y(pY).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               animateXY(pView, pX, pY, pDuration, pEndAction);
            }
         });
      }
   }  
}
