package games.ghoststories.controllers.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.views.common.TaoTokenView;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class TaoTokenDragListener implements OnDragListener {

   public TaoTokenDragListener() {
   }

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
