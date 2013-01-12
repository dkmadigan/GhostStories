package games.ghoststories.controllers;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EGhostAbility;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.enums.EVillageTile;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.gameboard.PlayerBoardCardView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class PlayerBoardCardController implements OnDragListener {
   public PlayerBoardCardController(GameBoardData pGameBoardData, 
         PlayerBoardCardView pView, ECardLocation pCardLocation) {
      mGameBoardData = pGameBoardData;
      mView = pView;
      mCardLocation = pCardLocation;
      mView.setOnDragListener(this);
   }

   public boolean onDrag(View pView, DragEvent pEvent) {
      boolean handle = false;
      switch(pEvent.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
         Log.d("PlayerBoardCardController", "ACTION_DRAG_STARTED");
         handle = true;
         break;
      case DragEvent.ACTION_DRAG_ENTERED:
         Log.d("PlayerBoardCardController", "ACTION_DRAG_ENTERED");
         break;        
      case DragEvent.ACTION_DRAG_LOCATION:
         Log.d("PlayerBoardCardController", "ACTION_DRAG_LOCATION");
         break;
      case DragEvent.ACTION_DROP:
         Log.i("PlayerBoardCardController", "ACTION_DROP");
         if(pEvent.getLocalState() instanceof GhostData) {
            GhostData data = (GhostData)pEvent.getLocalState();
            
            //Only allow dropping on this space if valid
            if(GameUtils.isSpaceValid(data, mGameBoardData, mCardLocation)) {
               GhostData ghostData = (GhostData)pEvent.getLocalState();
               mGameBoardData.addGhost(ghostData, mCardLocation);
               handle = true;
               handleEnterAbility(ghostData);               
            }                       
         }         
         break;
         case DragEvent.ACTION_DRAG_ENDED:
            Log.d("PlayerBoardCardController", "ACTION_DRAG_ENDED");
            break;
         case DragEvent.ACTION_DRAG_EXITED:
            Log.d("PlayerBoardCardController", "ACTION_DRAG_EXITED");
            break;   
         }
         return handle;
      }
   
      private void handleEnterAbility(GhostData pData) {
         GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
         boolean advanceGamePhase = true;
         for(EGhostAbility ability : pData.getEnterAbilities()) {
            switch(ability) {
            case ALL_LOSE_ABILITY: //TODO Handle this
               break;
            case ALL_LOSE_TAU: //TODO Handle this
               break;
            case CLEAR_CIRCLE_OF_PRAYER:
               gm.getVillageTile(EVillageTile.CIRCLE_OF_PRAYER).setTokenColor(null);
               break;
            case HAUNT_BOARD:
               pData.setHaunterLocation(EHaunterLocation.BOARD, null);
               break;
            case HAUNT_TILE:
               GameUtils.hauntVillageTile(mGameBoardData.getLocation(), 
                     mCardLocation);
               break;
            case LOSE_ABILITY:
               gm.getPlayerData(mGameBoardData.getColor()).setAbilityActive(false);
               break;
            case LOSE_DIE:
               gm.adjustNumDice(-1);
               break;
            case SUMMON_GHOST:
               //TODO Probably need to make it obvious you need to redraw a ghost
               advanceGamePhase = false;
               break;
            }
         }
         if(advanceGamePhase) {
            gm.advanceGamePhase();
         }         
      }

      private final GameBoardData mGameBoardData;
      private final PlayerBoardCardView mView;
      private final ECardLocation mCardLocation;

   }
