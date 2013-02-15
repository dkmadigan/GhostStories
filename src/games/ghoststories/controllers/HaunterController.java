package games.ghoststories.controllers;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.utils.GameUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class that handles all things related to updating the haunters 
 * during the different game phases.
 */
public class HaunterController implements IGamePhaseListener {

   /**
    * Constructor
    */
   public HaunterController() {
      GhostStoriesGameManager.getInstance().addGamePhaseListener(this);      
   }

   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase) {
      //TODO Animate one at a time, zooming in when they animate. Probably
      //need to create some kind of list of ghosts that need to be updated. 
      //Then update one at a time and wait for animation to finish before 
      //updating the next.
      if(pGamePhase == EGamePhase.YinPhase1A) {
         GameBoardData gbd = 
               GhostStoriesGameManager.getInstance().getCurrentPlayerBoard();
         mNumHauntersToAnimate = gbd.getNumHaunters();
         for(ECardLocation cl : sCardOrder) {
            GhostData gd = gbd.getGhostData(cl);
            if(gd != null) {
               switch(gd.getHaunterLocation()) {
               case CARD:
                  gd.setHaunterLocation(EHaunterLocation.BOARD, 
                        mAnimationCompleteRunnable);
                  break;
               case BOARD:                  
                  gd.setHaunterLocation(EHaunterLocation.TILE, null);
                  GameUtils.hauntVillageTile(gbd.getLocation(), cl);
                  gd.setHaunterLocation(EHaunterLocation.CARD, 
                        mAnimationCompleteRunnable);
                  break;
               case TILE:
               case NONE:
               default:
                  break;
               }
            }
         }
      }
   }       
   
   /**
    * Runnable that executes when the haunter animation is complete. When all
    * animations are complete then advance the game phase.
    */
   private Runnable mAnimationCompleteRunnable = new Runnable() {      
      public void run() {
         mNumHauntersToAnimate--;
         if(mNumHauntersToAnimate == 0) {
            //If there are no more haunters animating then advance the game phase
            GhostStoriesGameManager.getInstance().advanceGamePhase();
         }
      }
   };
   
   /** 
    * The number of haunters that are animating. Used as a counter to wait
    * until all haunters are done animating before advancing game phase. 
    */
   private int mNumHauntersToAnimate = 0;
   
   /** Animate the haunters from left board to right board **/
   private static List<ECardLocation> sCardOrder = 
         Arrays.asList(ECardLocation.LEFT, ECardLocation.MIDDLE, 
               ECardLocation.RIGHT);
}
