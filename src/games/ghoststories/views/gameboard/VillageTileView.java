package games.ghoststories.views.gameboard;

import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.data.interfaces.IVillageTileListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * {@link ImageView} representing a single village tile. 
 */
public class VillageTileView extends ImageView implements 
IGamePhaseListener, IVillageTileListener {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public VillageTileView(Context pContext) {
      super(pContext);
      initialize();
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public VillageTileView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
      initialize();
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public VillageTileView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      initialize();
   }
   
   /**
    * Performs any initialization necessary for this view.
    */
   private void initialize() {
      GhostStoriesGameManager.getInstance().addGamePhaseListener(this);      
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase) {      
      PlayerData pd = 
            GhostStoriesGameManager.getInstance().getCurrentPlayerData();
      if(pGamePhase == EGamePhase.YangPhase1 &&
            GameUtils.isVillageTileReachable(mData, pd)) {
         mIsHighlighted = true;
         GameUtils.invalidateView(this);
      } else if (pGamePhase == EGamePhase.YangPhase2) {
         //Once the move turn is over with then turn off the highlight unless
         //the player is on this tile, in that case keep the highlight since 
         //the village tile can be used
         if(pd.getLocation() != mData.getLocation()) { 
            mIsHighlighted = false;            
         }
         GameUtils.invalidateView(this);
      } else {
         if(mIsHighlighted) {
            mIsHighlighted = false;
            GameUtils.invalidateView(this);
         }
      }
   }
   
   /**
    * Sets the village tile data to use for this view. Will trigger a repaint
    * of the view.
    * @param pData The village tile data to use for this view
    */
   public void setVillageTileData(VillageTileData pData) {
      mData = pData;      
      mData.addVillageTileListener(this);
      setImageResource(mData.getActiveImageId());
      GameUtils.invalidateView(this);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IVillageTileListener#villageTileUpdated()
    */
   public void villageTileUpdated() {      
      GameUtils.invalidateView(this);
   }
   
   @Override
   protected void dispatchDraw(Canvas canvas) {    
      super.dispatchDraw(canvas);
   }
   
   /*
    * (non-Javadoc)
    * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {      
      super.onDraw(pCanvas);
      if(!isInEditMode()) {         
         if(mData.isActive()) {
            setImageResource(mData.getActiveImageId());
         } else {
            setImageResource(mData.getHauntedImageId());
         }
         
         if(mIsHighlighted) {
            if(mHighlightRect == null) {
               mHighlightRect = new Rect(0,  0,  getWidth(), getHeight());
            }
            pCanvas.drawBitmap(GhostStoriesBitmaps.sHighlightCardBitmap, null, 
                  mHighlightRect, null);
         }
         
         GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
         for(EColor c : EColor.getPlayerColors()) {
            if(gm.getVillageTile(c).getLocation() == mData.getLocation()) {
               pCanvas.drawBitmap(GhostStoriesBitmaps.sPlayerBitmaps.get(c), 
                     null, getRect(c), null);
            }
         }
      }            
   }
   
   private Rect getRect(EColor pColor) {
      int h = getHeight();
      int w = getWidth();
      switch(pColor) {
      case RED:
         return new Rect(w/4, 0, w/2, h/3);
      case BLUE:
         return new Rect(w/2, 0, (w/4)*3, h/3);
      case GREEN:
         return new Rect(w/4, h/3, w/2, (h/3)*2);
      case YELLOW:
         return new Rect(w/2, h/3, (w/4)*3, (h/3)*2);
      default:
         break;
      }
      return null;
   }
   
   /** The village tile data to use for this view **/
   private VillageTileData mData;
   /** Whether or not this village tile is highlighted **/
   private boolean mIsHighlighted = false;
   private Rect mHighlightRect = null;
}
