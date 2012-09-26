package games.ghoststories.controllers;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import games.ghoststories.data.GhostData;
import games.ghoststories.views.GhostCardView;

/**
 * Controller for the ghost card view. The ghost card is able to be dragged
 * around the screen and dropped on one of the game boards. The card can be
 * dropped according to the following rules:
 * <li>A red, green, blue or yellow ghost must be placed on the board of the
 * corresponding color, if possible. If all three spaces on that specific board 
 * are already occupied, then the player chooses any other location.
 * <li>A black ghost must be placed on the active player's board, if possible.
 * If all three spaces on the active player's board are already occupied, then
 * the active player chooses any other location.
 */
public class GhostCardController implements View.OnTouchListener, View.OnDragListener {
   
   /**
    * Constructor
    * @param pGhostData The data backing the ghost card
    * @param pGhostView The view representing the ghost card
    */
   public GhostCardController(GhostData pGhostData, GhostCardView pGhostView) {
      mGhostData = pGhostData;
      mGhostView = pGhostView;
      
      //Add the controller as a touch listener on the view
      mGhostView.setOnTouchListener(this);
      mGhostView.setOnDragListener(this);
   }
   
   /**
    * Destroys the controller
    */
   public void destroy() {
      mGhostView.setOnTouchListener(null);
      mGhostView.setOnDragListener(null);
   }
   
   /*
    * (non-Javadoc)
    * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
    */
   public boolean onDrag(View pView, DragEvent pEvent) {
      switch(pEvent.getAction()) {
      case DragEvent.ACTION_DRAG_ENDED:
         Log.d("GhostCardController", "ACTION_DRAG_ENDED");
         break;
      case DragEvent.ACTION_DRAG_ENTERED:
         Log.d("GhostCardController", "ACTION_DRAG_ENTERED");
         break;
      case DragEvent.ACTION_DRAG_EXITED:
         Log.d("GhostCardController", "ACTION_DRAG_EXITED");
         break;
      case DragEvent.ACTION_DRAG_LOCATION:
         Log.d("GhostCardController", "ACTION_DRAG_LOCATION");
         break;
      case DragEvent.ACTION_DRAG_STARTED:
         Log.d("GhostCardController", "ACTION_DRAG_STARTED");
         break;
      }
      return true;
   }
   
   /*
    * (non-Javadoc)
    * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
    */
   public boolean onTouch(View pView, MotionEvent pEvent) {
      if (pEvent.getAction() == MotionEvent.ACTION_DOWN) {
         ClipData clipData = ClipData.newPlainText("", "");
         View.DragShadowBuilder dsb = new View.DragShadowBuilder(pView);
         pView.startDrag(clipData, dsb, pView, 0);
         pView.setVisibility(View.INVISIBLE);
         return true;
      } else {
         return false;
      }
   }
   
   /** The data backing the ghost card **/
   private final GhostData mGhostData;
   /** The view representing the ghost card **/
   private final GhostCardView mGhostView;
}
