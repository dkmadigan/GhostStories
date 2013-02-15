package games.ghoststories.controllers.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.enums.EDragItem;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

/*package*/class DiceDragListener implements OnDragListener {

   public boolean onDrag(View pView, DragEvent pEvent) {
      boolean handled = false;
      Object localState = pEvent.getLocalState();
      if(localState instanceof DragData) {
         DragData dragData = (DragData)localState;
         //Only handle Dice Drags for my view
         if(dragData.getView().getId() == pView.getId() && 
               dragData.getDragItem() == EDragItem.COMBAT_DICE) {
            switch(pEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENDED:
               if(pEvent.getResult()) {
                  pView.setEnabled(false);
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
