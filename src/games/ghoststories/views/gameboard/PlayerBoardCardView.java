package games.ghoststories.views.gameboard;

import java.util.ArrayList;
import java.util.List;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.data.interfaces.IGameBoardListener;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.data.interfaces.IGhostDeckListener;
import games.ghoststories.data.interfaces.IGhostListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.enums.ETileLocation;
import games.ghoststories.utils.BitmapUtils;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * View representing a single card on a players board. The view is composed
 * of three different pieces:
 * <li>The card view: If this space is empty the view is populated based on the 
 * board color and card location. If this space is filled with a ghost the 
 * view is populated with the ghost image. 
 * <li>The haunter view: If there is a ghost on the card that contains a 
 * haunter then the haunter view will be enabled.
 * <li>The highlight view: If a player is able to move a ghost into this 
 * space from the deck, the view will be highlighted.
 */
public class PlayerBoardCardView extends RelativeLayout 
implements IGhostDeckListener, IGameBoardListener, IGhostListener, IGamePhaseListener {
   /**
    * Constructor
    * @param pContext
    */
   public PlayerBoardCardView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public PlayerBoardCardView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
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
         GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
         gm.getGhostDeckData().addGhostDeckListener(this); 
         gm.addGamePhaseListener(this);
      }
      //This ensures that onDraw will be called when the view is invalidated
      setWillNotDraw(false);
      setClipChildren(false);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGameBoardListener#gameBoardUpdated()
    */
   public void gameBoardUpdated() {
      if(mGhostData != null) {
         mGhostData.removeGhostListener(this);
      }
      mGhostData = mGameBoardData.getGhostData(mCardLocation);
      if(mGhostData != null) {
         mGhostData.addGhostListener(this);
      }
      updateBoardImage();
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase) {
      if(pGamePhase == EGamePhase.YangPhase2 && mGhostData != null && 
            GameUtils.isGhostAttackable(mGameBoardData.getLocation(), 
                  mCardLocation, GhostStoriesGameManager.getInstance(
                        ).getCurrentPlayerData().getLocation())) {
         setHighlight(true);
      } else {
         setHighlight(false);
      }
   }
      
   /**
    * Called when the ghost deck has been updated. If the top card is showing 
    * then highlight this space if the card is able to be placed here, else 
    * unhighlight this space. If the top card is not showing then unhighlight 
    * this space. 
    */
   public void ghostDeckUpdated() {    
      if(mGhostData == null) {
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
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGhostListener#haunterUpdated(games.ghoststories.enums.EHaunterLocation, games.ghoststories.enums.EHaunterLocation)
    */
   public void haunterUpdated(EHaunterLocation pOldLocation, 
         EHaunterLocation pNewLocation, Runnable pRunnable) {
      if(pOldLocation == EHaunterLocation.CARD && 
            pNewLocation == EHaunterLocation.BOARD) {
         animateHaunter(sCardToBoardAnimationAmount, pRunnable);
      } else if(pOldLocation == EHaunterLocation.BOARD && 
            pNewLocation == EHaunterLocation.TILE) {
         //
      } else if(pOldLocation == EHaunterLocation.TILE && 
            pNewLocation == EHaunterLocation.CARD) {         
         GameUtils.animateXY(mHaunterImageView, 
               (int)mCardImageView.getX(), 
               (int)mCardImageView.getY(), 
               sHaunterAnimationDuration, pRunnable);               
      } 
   }
  
   /**
    * Sets the {@link GameBoardData} for this view.
    * @param pGameBoardData
    */
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;      
     
      mCardImageView = (ImageView)findViewById(R.id.card);
      mHaunterImageView = (ImageView)findViewById(R.id.haunter);
      mHighlightView = (ImageView)findViewById(R.id.highlight);           
      
      loadBitmaps();
      updateBoardImage();
      mGameBoardData.addGameBoardListener(this);      
   }
   
   /**
    * Animate the haunter by the specified amount.
    * @param pValue
    */
   private void animateHaunter(float pValue, Runnable pEndAction) {
      switch(mGameBoardData.getLocation()) {
      case BOTTOM:
      case TOP:
         GameUtils.translationY(mHaunterImageView, 
               mHaunterImageView.getHeight() * -pValue, 
               sHaunterAnimationDuration, pEndAction);         
         break;
      case RIGHT:
         GameUtils.translationX(mHaunterImageView, 
               mHaunterImageView.getWidth() * -pValue, 
               sHaunterAnimationDuration, pEndAction);          
         break;
      case LEFT:
         GameUtils.translationX(mHaunterImageView, 
               mHaunterImageView.getWidth() * pValue, 
               sHaunterAnimationDuration, pEndAction);           
         break;
      }
   }
   
   /**
    * Load the necessary bitmaps that are used by this view.
    */
   private void loadBitmaps() {
      //TODO Fix this uglyness
      //If the left or the right need to rotate the bitmap
      mEmptyBitmap = 
            GhostStoriesBitmaps.sPlayerBoardBitmaps.get(
               mGameBoardData.getColor()).get(mCardLocation);
      mHaunterBitmap = GhostStoriesBitmaps.sHaunterBitmaps.get(
            mGameBoardData.getLocation()).get(EHaunterLocation.CARD);
      switch(mGameBoardData.getLocation()) {
      case BOTTOM:
      case TOP:     
         mCardImageView.setImageBitmap(mEmptyBitmap);
         break;
      case RIGHT:
         new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
               mEmptyBitmap = BitmapUtils.rotateBitmap(mEmptyBitmap, 270);    
               return null;
            }           
            protected void onPostExecute(Void result) {      
               mCardImageView.setImageBitmap(mEmptyBitmap);
               mHaunterImageView.setImageBitmap(mHaunterBitmap);
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
               mCardImageView.setImageBitmap(mEmptyBitmap);
               mHaunterImageView.setImageBitmap(mHaunterBitmap);
            }
         }.execute();  
         break;
      default:
         break;
      }
   }
   
   /**
    * Set whether or not this view is highlighted.
    * @param pIsHighlight
    */
   private void setHighlight(boolean pIsHighlight) {
      if(mIsHighlighted != pIsHighlight) {
         mIsHighlighted = pIsHighlight;
         GameUtils.invalidateView(this);
      }
   }
   
   /**
    * Updates the image on this space based. If there is a ghost then use 
    * the ghost image, else use the empty image.
    */
   private void updateBoardImage() {
      if(mGhostData == null) {
         //No ghost at this spot so draw the empty bitmap
         ImageViewUtils.setImageBitmapEDT(mCardImageView, mEmptyBitmap);
      } else {
         //There is a ghost at this spot so draw the ghost image
         ImageViewUtils.setBoardLocationResource(mCardImageView, 
               mGameBoardData.getLocation(), 
               mGhostData.getImageId());
      }              
   } 
   
   /*
    * (non-Javadoc)
    * @see android.view.View#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {    
      super.onDraw(pCanvas);      
      
      if(!isInEditMode()) {         
         if(mIsHighlighted) {
            mHighlightView.setVisibility(View.VISIBLE);
         } else {
            mHighlightView.setVisibility(View.INVISIBLE);
         }
                           
         if(mGhostData != null && mGhostData.getHaunterLocation() != EHaunterLocation.NONE) {
            mHaunterImageView.setVisibility(View.VISIBLE);       
         } else {
            mHaunterImageView.setVisibility(View.INVISIBLE);
         }
      }
   }   
   
   /** The location of this view **/
   private ECardLocation mCardLocation;
   /** The image view of the card **/
   private ImageView mCardImageView = null;
   /** The game baord data used by this view **/
   private GameBoardData mGameBoardData = null; 
   /** The bitmap to use when this view has no ghost on it **/
   private Bitmap mEmptyBitmap = null;
   /** The current ghost data occupying this view or null if no ghost **/
   private GhostData mGhostData = null;
   /** The haunter bitmap **/
   private Bitmap mHaunterBitmap = null;
   /** The image view of the haunter **/
   private ImageView mHaunterImageView = null;
   /** The image view of the highlight **/
   private ImageView mHighlightView = null;
   /** Whether or not this view is highlighted **/
   private boolean mIsHighlighted = false;    
   
   /** The duration of the haunter move animation **/
   private static final int sHaunterAnimationDuration = 2000;
   
   private static final float sCardToBoardAnimationAmount = 0.6f;
}
