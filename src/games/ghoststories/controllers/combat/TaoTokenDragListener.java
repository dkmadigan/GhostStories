package games.ghoststories.controllers.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.views.common.TaoTokenView;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

/**
 * Drag and drop listener for the Tao Tokens during combat. These tokens
 * can be dragged from the player area at the top down onto the ghosts. If
 * the drag was successfully applied to a ghost, then decrement the token 
 * readout.
 */
/*package*/ class TaoTokenDragListener implements OnDragListener {

   /*
    * (non-Javadoc)
    * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
    */
   public boolean onDrag(View pView, DragEvent pEvent) {
      boolean handled = false;
      Object localState = pEvent.getLocalState();
      if(localState instanceof DragData) {
         DragData dragData = (DragData)localState;
         //Only handle Tao Drags for my view
         if(dragData.getView() == pView && 
               dragData.getDragItem() == EDragItem.COMBAT_TAO) {
            switch(pEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENDED:
               if(pEvent.getResult()) {
                  TaoTokenView tokenView = (TaoTokenView)pView;
                  tokenView.decrement();
                  tokenView.setTextColor(Color.RED);
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

}
