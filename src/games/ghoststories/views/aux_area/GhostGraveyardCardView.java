package games.ghoststories.views.aux_area;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GhostGraveyardCardView extends ImageView {

   /**
    * Constructor
    * @param pContext
    */
   public GhostGraveyardCardView(Context pContext) {
      super(pContext);      
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GhostGraveyardCardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GhostGraveyardCardView(Context pContext, AttributeSet pAttrs,
         int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }      

   /*
    * (non-Javadoc)
    * @see com.views.NumberedImageView#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {
      super.onDraw(pCanvas);
      if(isInEditMode()) {
         Bitmap xout = 
               BitmapFactory.decodeResource(getResources(), R.drawable.xout);         
         pCanvas.drawBitmap(xout, null, new RectF(0, 0, getWidth(), getHeight()), null);
      } else {      
         if(mRect == null) {
            mRect = new RectF(0, 0, getWidth(), getHeight());
         }
         pCanvas.drawBitmap(GhostStoriesBitmaps.sXOutBitmap, null, mRect, null);
      }
   }     
   
   private RectF mRect = null;
}
