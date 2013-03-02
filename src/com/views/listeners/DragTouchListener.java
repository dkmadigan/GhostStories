package com.views.listeners;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.interfaces.IDraggable;

/**
 * Generic {@link OnTouchListener} for draggable objects. Creates a drag 
 * shadow of the view being touched and starts the drag operation. If the
 * dragged view implements the {@link IDraggable} interface then the data from
 * this view is passed along with the dragged object. 
 */
public class DragTouchListener implements OnTouchListener {      
   /*
    * (non-Javadoc)
    * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
    */
   public boolean onTouch(View pView, MotionEvent pEvent) {
      if(pEvent.getAction() == MotionEvent.ACTION_DOWN) {
         ClipData clipData = ClipData.newPlainText("", "");                
         View.DragShadowBuilder dsb = new View.DragShadowBuilder(pView);
         
         Object data = null;         
         if(pView instanceof IDraggable) {
            data = ((IDraggable<?>)pView).getDragData();
         }
                     
         pView.startDrag(clipData, dsb, data, 0);            
         return true;
      }
       return false;
   } 
}
