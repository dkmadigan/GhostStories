package games.ghoststories.controllers;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import com.views.listeners.DragTouchListener;

import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.views.aux_area.GhostDeckView;

/**
 * Controller class responsible for handling interactions with the ghost deck.
 * These interactions include:
 * <li>Drawing the top card of the deck
 * <li>Dragging the top card of the deck to the game board
 */
public class GhostDeckController implements View.OnClickListener, View.OnDragListener {
   /**
    * Constructor
    * @param pGhostDeckData
    * @param pGhostDeckView
    */
   public GhostDeckController(GhostDeckData pGhostDeckData, 
         GhostDeckView pGhostDeckView) {
      mGhostDeckData = pGhostDeckData;
      mGhostDeckView = pGhostDeckView;
      mGhostDeckView.setOnClickListener(this);
      mGhostDeckView.setOnTouchListener(mDragTouchListener);
      mGhostDeckView.setOnDragListener(this);
   }

   /**
    * Called when the deck is "clicked" on. Flips the top card if it is not
    * already flipped and we are in the correct phase of the game.
    */
   public void onClick(View pView) {  
      if(GhostStoriesGameManager.getInstance().getCurrentGamePhase() ==
            EGamePhase.YinPhase3 && !mGhostDeckData.getTopCard().isFlipped()) {
         mGhostDeckData.flipTopCard();      
      }              
   }
   
   /**
    * Handles the end of a drag event. If the card was successfully dropped 
    * on the board the remove the top card from the ghost deck. Otherwise reset
    * the top card to a non dragging state.
    */
   public boolean onDrag(View pView, DragEvent pEvent) {   
      boolean handled = false;
      Object data = pEvent.getLocalState();
      if(data instanceof DragData) {
         DragData dragData = (DragData)data;
         if(dragData.getDragItem() == EDragItem.GHOST_CARD) {
            switch(pEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENDED:
               if(pEvent.getResult()) {
                  mGhostDeckData.removeTopCard();
               } else {
                  mGhostDeckData.setTopCardDragging(false);
               }
               handled = true;
               break;
            default:
               break;
            }
         }
      }      
      return handled;
   }

   /**
    * Touch listener that initiates a drag if dragging is allowed based on the
    * current game phase and top card state.
    */
   private DragTouchListener mDragTouchListener = new DragTouchListener() {
      public boolean onTouch(View pView, MotionEvent pEvent) {
         if(GhostStoriesGameManager.getInstance().getCurrentGamePhase() == 
               EGamePhase.YinPhase3 && mGhostDeckData.getTopCard().isFlipped()) {
            if(super.onTouch(pView, pEvent)) {
               mGhostDeckData.setTopCardDragging(true);
               return true;
            }
         }
         return false;
      };
   };           

   /** The data representing the ghost deck **/
   private final GhostDeckData mGhostDeckData;
   /** The view for the ghost deck **/
   private final GhostDeckView mGhostDeckView;
}
