package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.IGhostDeckListener;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.utils.ImageViewUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;

import com.views.NumberedImageView;

public class GhostDeckView extends NumberedImageView implements IGhostDeckListener {
   public GhostDeckView(Context pContext) {
      super(pContext);
      setShowNumber(false);
   }
   
   public GhostDeckView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
      setShowNumber(false);
   }
   
   public GhostDeckView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle); 
      setShowNumber(false);
   }     
   
   /**
    * @return The bitmap of the top card. There are no guarantees that this 
    * bitmap has already been loaded.
    */
   public Bitmap getTopCardBitmap() {
      return mTopCardBitmap;
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.IGhostDeckListener#ghostDeckUpdated()
    */
   public void ghostDeckUpdated() {
      GhostData topCard = mGhostDeckData.getTopCard();
      if(topCard.isFlipped()) {
         //Top card is flipped over so update the bitmap
         if(topCard.isDragging()) {
            //showCardBack(topCard);
            //TODO What to show on deck when card is being dragged?
            flipTopCard();
         } else {
            flipTopCard();   
         }         
      } else {
         //Top card is not flipped so load the back of the card and load the 
         //ready the top card bitmap  
         showCardBack(topCard);
         
         setNumber(mGhostDeckData.getNumGhosts());
         loadTopCardBitmap();
      }
   }  
   
   /**
    * Sets the ghost deck data model for this view
    * @param pGhostDeckData
    */
   public void setGhostDeckData(GhostDeckData pGhostDeckData) {
      mGhostDeckData = pGhostDeckData;        
      setNumber(mGhostDeckData.getNumGhosts()); 
      setShowNumber(false);
      
      //Load the top card bitmap so it is ready when it needs to be flipped
      loadTopCardBitmap();
      
      mGhostDeckData.addGhostDeckListener(this);
   }     
   
   /**
    * Called when the ghost deck is updated. Render the new top card and 
    * update the count.
    */
   private void flipTopCard() {      
      //If the top card is loaded then set it on the UI thread. If the top 
      //card is not yet loaded wait for it to load and then set it on the UI
      //thread
      if(mTopCardBitmap != null) {
         ImageViewUtils.setImageBitmapEDT(this, mTopCardBitmap);
      } else {
         //Card is not yet loaded. Push a runnable onto the thread doing the
         //loading. When this task runs the image should be loaded.
         sExecutor.execute(new Runnable() {
            public void run() {             
               if(mTopCardBitmap != null) { //This better be true
                  ImageViewUtils.setImageBitmapEDT(GhostDeckView.this, mTopCardBitmap);
               } else {
                  Log.e("GhostDeckView", "Error loading bitmap");
               }
            }
         });
      }
   }
   
   private void loadTopCardBitmap() {      
      sExecutor.execute(new Runnable() {
         public void run() {          
            mTopCardBitmap = 
                  BitmapFactory.decodeResource(getResources(), 
                        mGhostDeckData.getTopCard().getImageId());  
         }
      });
   }
   
   private void showCardBack(GhostData pCard) {
      if(pCard.isWuFeng()) {
         ImageViewUtils.setImageBitmapEDT(this, GhostStoriesBitmaps.sWuFengCardBitmap);   
      } else {
         ImageViewUtils.setImageBitmapEDT(this, GhostStoriesBitmaps.sGhostCardBitmap);
      }
   }
   
   private static final Executor sExecutor = Executors.newSingleThreadExecutor();      
   private GhostDeckData mGhostDeckData;   
   private Bitmap mTopCardBitmap = null;
}
