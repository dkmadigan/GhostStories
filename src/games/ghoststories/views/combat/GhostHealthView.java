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

/**
 * View representing a single token of ghost health. The token is one of the
 * five colors (see {@link EColor}) and can be in two different states, active
 * and not active. If the token is active it shows as normal, indicating that
 * the ghost still has that health available. If the token is not active then
 * it is crossed out, indicating that the ghost has taken damage.
 */
public class GhostHealthView extends ImageView {

   /**
    * Constructor
    * @param pContext
    */
   public GhostHealthView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GhostHealthView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GhostHealthView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /**
    * @return The color of the health token
    */
   public EColor getColor() {
      return mColor; 
   }
   
   /**
    * @return Whether or not the health token is active
    */
   public boolean isActive() {
      return mActive;
   }
   
   /**
    * Sets the color of this token
    * @param pColor The color of the token
    */
   public void setColor(EColor pColor) {
      setImageResource(GameUtils.getTaoTokenId(pColor));
      GameUtils.invalidateView(this);
   }
   
   /**
    * Sets whether or not the token is active. 
    * @param pActive Whether or not the token is active
    */
   public void setActive(boolean pActive) {
      mActive = pActive;
      GameUtils.invalidateView(this);
   }
   
   /*
    * (non-Javadoc)
    * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
    */
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
   
   /** Whether or not the token is active **/
   private boolean mActive = true;
   /** The color of the token **/
   private EColor mColor = null;
   /** Used for rendering the X when the token is not active **/
   private RectF mRect = null;   
}
