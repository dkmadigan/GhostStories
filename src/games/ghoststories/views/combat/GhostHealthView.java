package games.ghoststories.views.combat;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GhostHealthView extends ImageView {

   public GhostHealthView(Context pContext) {
      super(pContext);
   }

   public GhostHealthView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public GhostHealthView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   public void setColor(EColor pColor) {
      setImageResource(GameUtils.getTaoTokenId(pColor));
      GameUtils.invalidateView(this);
   }
   
   public void setActive(boolean pActive) {
      mActive = pActive;
      GameUtils.invalidateView(this);
   }
   
   @Override
   protected void onDraw(Canvas pCanvas) {
      super.onDraw(pCanvas);
      if(isInEditMode()) {
         Bitmap xout = 
               BitmapFactory.decodeResource(getResources(), R.drawable.xout);         
         pCanvas.drawBitmap(xout, null, new RectF(0, 0, getWidth(), getHeight()), null);
      } else {
         //XOut the health if necessary
         if(mRect == null) {
            mRect = new RectF(0, 0, getWidth(), getHeight());
         }
         
         if(!mActive) {
            pCanvas.drawBitmap(GhostStoriesBitmaps.sXOutBitmap, null, mRect, null);
         }
      }
   }
   
   private boolean mActive = true;
   private RectF mRect = null;
}
