package games.ghoststories.views.common;

import games.ghoststories.data.PlayerData;
import games.ghoststories.data.TokenSupplyData;
import games.ghoststories.data.interfaces.ITokenListener;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.views.NumberedImageView;

public abstract class AbstractNumberedTokenView extends NumberedImageView implements ITokenListener {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public AbstractNumberedTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public AbstractNumberedTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public AbstractNumberedTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      //Since the text size is determined based on the view size, it must
      //be set in onDraw after the component has a height and width.
      super(pContext, pAttrs, pDefStyle, sXOffset, sYOffset, -1, sDefaultColor);
   }
   
   /**
    * Sets the data used to populate this view
    * @param pData The data
    */
   public void setData(TokenSupplyData pData) {
      mData = pData;
      mData.addTokenListener(this);
   }
   
   /*
    * (non-Javadoc)
    * @see com.views.NumberedImageView#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {
      //Set the text size based on the image size
      mPaint.setTextSize(Math.min(getWidth(), getHeight()) / 3);
      super.onDraw(pCanvas);
   }
   
   /** The x offset of the text **/
   private static final float sXOffset = 0.7f;
   /** The y offset of the text **/
   private static final float sYOffset = 0.7f;   
   
   /** The data **/
   protected TokenSupplyData mData;
}
