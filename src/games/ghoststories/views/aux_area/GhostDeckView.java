package games.ghoststories.views.aux_area;

import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.interfaces.IGhostDeckListener;
import games.ghoststories.enums.EDragItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;

import com.interfaces.IDraggable;
import com.utils.ImageViewUtils;
import com.views.NumberedImageView;

/**
 * View that represents the ghost deck. The ghost deck can either have the top
 * card face down or face up. If the top card is face up and it is the correct
 * game phase then this supports dragging and dropping the card onto the
 * player game boards.
 */
public class GhostDeckView extends NumberedImageView implements 
IGhostDeckListener, IDraggable<DragData> {
   
   /**
    * Constructor
    * @param pContext
    */
   public GhostDeckView(Context pContext) {
      super(pContext);
      setShowNumber(false);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GhostDeckView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
      setShowNumber(false);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GhostDeckView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle); 
      setShowNumber(false);
   }     
   
   /*
    * (non-Javadoc)
    * @see com.views.IDraggable#getDragData()
    */
   public DragData getDragData() {    
      return new DragData(EDragItem.GHOST_CARD, mGhostDeckData.getTopCard(), this);
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
         ImageViewUtils.setImageBitmap(this, mTopCardBitmap);
      } else {
         //Card is not yet loaded. Push a runnable onto the thread doing the
         //loading. When this task runs the image should be loaded.
         sExecutor.execute(new Runnable() {
            public void run() {             
               if(mTopCardBitmap != null) { //This better be true
                  ImageViewUtils.setImageBitmap(GhostDeckView.this, mTopCardBitmap);
               } else {
                  Log.e("GhostDeckView", "Error loading bitmap");
               }
            }
         });
      }
   }
   
   /**
    * Loads the bitmap of the top card on the ghost deck. We do this as soon 
    * as the previous top card is removed from the deck so it is ready when
    * the player flips the top card. Push the task off onto a background thread
    * to ensure that it is loaded before trying to show it in {@link #flipTopCard()}.
    */
   private void loadTopCardBitmap() {      
      sExecutor.execute(new Runnable() {
         public void run() {          
            mTopCardBitmap = 
                  BitmapFactory.decodeResource(getResources(), 
                        mGhostDeckData.getTopCard().getImageId());  
         }
      });
   }
   
   /**
    * Sets the bitmap for this view to the back of the card. The view varies 
    * depending on whether the card is a WuFeng card or regular ghost card.
    * @param pCard The top card
    */
   private void showCardBack(GhostData pCard) {
      if(pCard.isWuFeng()) {
         ImageViewUtils.setImageBitmap(this, GhostStoriesBitmaps.sWuFengCardBitmap);   
      } else {
         ImageViewUtils.setImageBitmap(this, GhostStoriesBitmaps.sGhostCardBitmap);
      }
   }
   
   /** Thread used to preload the top card bitmap **/
   private static final Executor sExecutor = Executors.newSingleThreadExecutor();
   
   /** Model for this view **/
   private GhostDeckData mGhostDeckData;
   /** Bitmap of the current top card **/
   private Bitmap mTopCardBitmap = null;
}
