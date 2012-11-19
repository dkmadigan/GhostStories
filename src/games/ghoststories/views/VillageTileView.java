package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EVillageTile;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * {@link ImageView} representing a single village tile. 
 */
public class VillageTileView extends ImageView {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public VillageTileView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public VillageTileView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public VillageTileView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /**
    * Sets the village tile data to use for this view. Will trigger a repaint
    * of the view.
    * @param pData The village tile data to use for this view
    */
   public void setVillageTileData(VillageTileData pData) {
      mData = pData;      
      postInvalidate();
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

         GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
         for(EColor c : EColor.getPlayerColors()) {
            if(gm.getVillageTile(c).getLocation() == mData.getLocation()) {
               float x = getWidth() / 2;
               float y = getHeight() / 2;
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
}
