package games.ghoststories.controllers;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.utils.GameUtils;

import java.util.Arrays;
import java.util.List;

import android.graphics.Point;
import android.view.View;

public class HaunterController implements IGamePhaseListener {

   public HaunterController() {
      GhostStoriesGameManager.getInstance().addGamePhaseListener(this);      
   }

   public void gamePhaseUpdated(EGamePhase pGamePhase) {
      //TODO Animate one at a time, zooming in when they animate. Probably
      //need to create some kind of list of ghosts that need to be updated. 
      //Then update one at a time and wait for animation to finish before 
      //updating the next.
      if(pGamePhase == EGamePhase.YinPhase1A) {
         GameBoardData gbd = 
               GhostStoriesGameManager.getInstance().getCurrentPlayerBoard();
         for(ECardLocation cl : sCardOrder) {
            GhostData gd = gbd.getGhostData(cl);
            if(gd != null) {
               switch(gd.getHaunterLocation()) {
               case CARD:
                  gd.setHaunterLocation(EHaunterLocation.BOARD, 
                        mAdvanceGamePhaseRunnable);
                  break;
               case BOARD:
                  gd.setHaunterLocation(EHaunterLocation.TILE, null);
                  GameUtils.hauntVillageTile(gbd.getLocation(), cl);
                  gd.setHaunterLocation(EHaunterLocation.CARD, 
                        mAdvanceGamePhaseRunnable);
                  break;
               case TILE:
               case NONE:
               default:
                  break;
               }
            }
         }
         //TODO Figure out a way to delay this so it only happens after 
         //the ghosts are animated.
         GhostStoriesGameManager.getInstance().advanceGamePhase();
      }
   }
     
   private Runnable mAdvanceGamePhaseRunnable = new Runnable() {      
      public void run() {
         GhostStoriesGameManager.getInstance().advanceGamePhase();
      }
   };
   
   private static List<ECardLocation> sCardOrder = 
         Arrays.asList(ECardLocation.LEFT, ECardLocation.MIDDLE, 
               ECardLocation.RIGHT);
}
