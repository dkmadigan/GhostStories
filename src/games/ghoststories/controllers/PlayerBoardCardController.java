package games.ghoststories.controllers;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.PlayerBoardCardView;
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
               mGameBoardData.addGhost((GhostData)pEvent.getLocalState(), 
                     mCardLocation);
               handle = true;
               GhostStoriesGameManager.getInstance().advanceGamePhase();
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

      private final GameBoardData mGameBoardData;
      private final PlayerBoardCardView mView;
      private final ECardLocation mCardLocation;

   }
