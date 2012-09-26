package games.ghoststories.views;

import games.ghoststories.data.VillageTileData;
import games.ghoststories.enums.EVillageTile;
import android.content.Context;
import android.graphics.Canvas;
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
      if(mData.isActive()) {
         setImageResource(mData.getActiveImageId());
      } else {
         setImageResource(mData.getHauntedImageId());
      }
   }
   
   /** The village tile data to use for this view **/
   private VillageTileData mData;
}
