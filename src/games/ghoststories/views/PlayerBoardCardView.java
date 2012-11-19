package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.IGameBoardListener;
import games.ghoststories.data.IGhostDeckListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.utils.BitmapUtils;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PlayerBoardCardView extends ImageView 
implements IGhostDeckListener, IGameBoardListener {
   public PlayerBoardCardView(Context pContext) {
      super(pContext);
   }

   public PlayerBoardCardView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }

   public PlayerBoardCardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      
      //Read in the attributes and set the values if specified
      TypedArray a = pContext.obtainStyledAttributes(pAttrs,
            R.styleable.PlayerBoardCardView);
      for (int i = 0; i < a.getIndexCount(); ++i)
      {
         int attr = a.getIndex(i);
         switch (attr)
         {
         case R.styleable.PlayerBoardCardView_card_location:            
            int loc = a.getInteger(attr, 0);            
            mCardLocation = ECardLocation.values()[loc];
            break;
         }
      }
      a.recycle();
      
      if(!isInEditMode()) {
         GhostStoriesGameManager.getInstance().getGhostDeckData().addGhostDeckListener(this);
      }
   }
   
   public void gameBoardUpdated() {
      updateBoardImage();
   }
   
   public void ghostDeckUpdated() {    
      if(mGameBoardData.getGhostData(mCardLocation) == null) {
         //No card currently at this location. Highlight the view if the 
         //currently shown card can be place here, else unhighlight the view.
         GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
         GhostDeckData ghostDeck = gm.getGhostDeckData();
         GhostData topCard = ghostDeck.getTopCard();
         if(topCard.isFlipped()) {
            //If the top card is my color OR is black and it is my turn then
            //highlight
            if(GameUtils.isSpaceValid(topCard, mGameBoardData, mCardLocation)) {
               setHighlight(true);
            }
         } else {
            //Top card is not flipped so unhighlight
            setHighlight(false);
         }
      } else {
         //Already a card at this location so unhighlight
         setHighlight(false);
      }
   }
   
   public void setHighlight(boolean pIsHighlight) {
      if(mIsHighlighted != pIsHighlight) {
         mIsHighlighted = pIsHighlight;
         postInvalidate();
      }
   }
   
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;      
      updateBoardImage();
      mGameBoardData.addGameBoardListener(this);
   }  
   
   private void updateBoardImage() {
      GhostData gd = mGameBoardData.getGhostData(mCardLocation);
      if(gd == null) {
         //TODO Synchronization of this probably not working right
         //No ghost at this spot so draw the empty bitmap
         if(mEmptyBitmap == null) {                       
            //If the left or the right need to rotate the bitmap
            mEmptyBitmap = 
                  GhostStoriesBitmaps.sPlayerBoardBitmaps.get(
                     mGameBoardData.getColor()).get(mCardLocation);
            switch(mGameBoardData.getLocation()) {
            case BOTTOM:
            case TOP:
               setImageBitmap(mEmptyBitmap);
               break;
            case RIGHT:
               new AsyncTask<Void, Void, Void>() {
                  protected Void doInBackground(Void... params) {
                     mEmptyBitmap = BitmapUtils.rotateBitmap(mEmptyBitmap, 270);
                     return null;
                  }           
                  protected void onPostExecute(Void result) {      
                     setImageBitmap(mEmptyBitmap);
                  }
               }.execute();               
               break;
            case LEFT:
               new AsyncTask<Void, Void, Void>() {
                  protected Void doInBackground(Void... params) {
                     mEmptyBitmap = BitmapUtils.rotateBitmap(mEmptyBitmap, 90);
                     return null;
                  }           
                  protected void onPostExecute(Void result) {      
                     setImageBitmap(mEmptyBitmap);
                  }
               }.execute();  
               break;
            default:
               break;
            }
         }
         ImageViewUtils.setImageBitmapEDT(this, mEmptyBitmap);
      } else {
         //There is a ghost at this spot so draw the ghost image
         ImageViewUtils.setBoardLocationResource(this, mGameBoardData.getLocation(), 
               gd.getImageId());
      }              
   }  
   
   @Override
   protected void onDraw(Canvas pCanvas) {    
      super.onDraw(pCanvas);      
      
      if(mIsHighlighted) {
         if(mHighlightRect == null) {
            mHighlightRect = new Rect(0,  0,  getWidth(), getHeight());
         }
         pCanvas.drawBitmap(GhostStoriesBitmaps.sHighlightCardBitmap, null, 
               mHighlightRect, null);
      }
   }      
   
   private GameBoardData mGameBoardData = null;   
   private Bitmap mEmptyBitmap = null;
   private ECardLocation mCardLocation;   
   private Rect mHighlightRect = null;
   private boolean mIsHighlighted = false;    
}
