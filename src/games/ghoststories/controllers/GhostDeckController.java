package games.ghoststories.controllers;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.views.GhostDeckView;

public class GhostDeckController implements View.OnClickListener, View.OnTouchListener, View.OnDragListener {
   public GhostDeckController(GhostDeckData pGhostDeckData, 
         GhostDeckView pGhostDeckView) {
      mGhostDeckData = pGhostDeckData;
      mGhostDeckView = pGhostDeckView;
      mGhostDeckView.setOnClickListener(this);
      mGhostDeckView.setOnTouchListener(this);
      mGhostDeckView.setOnDragListener(this);
   }
   
   public void onClick(View pView) {  
      Log.d("GhostDeckController", "onClick");
      //Only allow flipping the top card during the correct phase
      if(GhostStoriesGameManager.getInstance().getCurrentGamePhase() ==
            EGamePhase.EYinPhase3 && !mGhostDeckData.getTopCard().isFlipped()) {
         mGhostDeckData.flipTopCard();      
      }              
   }
   
   public boolean onTouch(View pView, MotionEvent pEvent) {           
      Log.d("GhostDeckController", "onTouch " + pEvent.getAction());
      if(GhostStoriesGameManager.getInstance().getCurrentGamePhase() == 
            EGamePhase.EYinPhase3 && mGhostDeckData.getTopCard().isFlipped()) {
         if (pEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData clipData = ClipData.newPlainText("", "");                
            View.DragShadowBuilder dsb = new View.DragShadowBuilder(pView) {
               @Override
               public void onDrawShadow(Canvas canvas) {
                  
                  super.onDrawShadow(canvas);
               }
            };
            mGhostDeckData.setTopCardDragging(true);
            pView.startDrag(clipData, dsb, mGhostDeckData.getTopCard(), 0);            
            return true;
         } else {
            return false;
         }
      }
      
      return false;
   }
   
   public boolean onDrag(View pView, DragEvent pEvent) {    
      switch(pEvent.getAction()) {
      case DragEvent.ACTION_DRAG_ENDED:
         Log.d("GhostCardController", "ACTION_DRAG_ENDED");
         if(pEvent.getResult()) {
            mGhostDeckData.removeTopCard();
         } else {
            mGhostDeckData.setTopCardDragging(false);
         }
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
      return false;
   }
   
   private final GhostDeckData mGhostDeckData;
   private final GhostDeckView mGhostDeckView;
}
