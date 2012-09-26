package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.IGhostDeckListener;
import games.ghoststories.utils.GameUtils;

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
      mGhostCardBitmap = BitmapFactory.decodeResource(getResources(), 
            R.drawable.ghost_card_back);
   }
   
   public GhostDeckView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      mGhostCardBitmap = BitmapFactory.decodeResource(getResources(), 
            R.drawable.ghost_card_back);
   }
   
   public GhostDeckView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      //TODO Is this the right place to do this?
      mGhostCardBitmap = BitmapFactory.decodeResource(getResources(), 
            R.drawable.ghost_card_back);      
   }
   
   /**
    * Sets the ghost deck data model for this view
    * @param pGhostDeckData
    */
   public void setGhostDeckData(GhostDeckData pGhostDeckData) {
      mGhostDeckData = pGhostDeckData;        
      setNumber(mGhostDeckData.getNumGhosts());     
      
      //Load the top card bitmap so it is ready when it needs to be flipped
      loadTopCardBitmap();
      
      mGhostDeckData.addGhostDeckListener(this);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.IGhostDeckListener#ghostDeckUpdated()
    */
   public void ghostDeckUpdated() {
      if(mGhostDeckData.getTopCard().isFlipped()) {
         //Top card is flipped over so update the bitmap
         flipTopCard();
      } else {
         //Top card is not flipped so load the back of the card and load the 
         //ready the top card bitmap
         setImageBitmapEDT(mGhostCardBitmap);
         setNumber(mGhostDeckData.getNumGhosts());
         loadTopCardBitmap();
      }
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
         setImageBitmapEDT(mTopCardBitmap);
      } else {
         //Card is not yet loaded. Push a runnable onto the thread doing the
         //loading. When this task runs the image should be loaded.
         sExecutor.execute(new Runnable() {
            public void run() {             
               if(mTopCardBitmap != null) { //This better be true
                  setImageBitmapEDT(mTopCardBitmap);
               } else {
                  Log.e("GhostDeckView", "Error loading bitmap");
               }
            }
         });
      }
   }
   
   private void setImageBitmapEDT(final Bitmap pBitmap) {
      if(GameUtils.isUIThread()) {
         setImageBitmap(pBitmap);
      } else {
         post(new Runnable() {
            public void run() {                
               setImageBitmap(pBitmap);
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
   
   private static final Executor sExecutor = Executors.newSingleThreadExecutor();   
   private final Bitmap mGhostCardBitmap;
   
   private GhostDeckData mGhostDeckData;   
   private Bitmap mTopCardBitmap = null;
}
