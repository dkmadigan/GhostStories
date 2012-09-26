package games.ghoststories.views;

import games.ghoststories.data.PlayerData;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.views.NumberedImageView;

public class AbstractNumberedTokenView extends NumberedImageView {
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
      super(pContext, pAttrs, pDefStyle, sXOffset, sYOffset, -1);
   }
   
   /**
    * Sets the player data used to populate this view
    * @param pData The player data
    */
   public void setPlayerData(PlayerData pData) {
      mPlayerData = pData;
   }
   
   /*
    * (non-Javadoc)
    * @see com.views.NumberedImageView#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {
      //Set the text size based on the image size
      mTextSize = Math.min(getWidth(), getHeight()) / 3;
      super.onDraw(pCanvas);
   }
   
   /** The x offset of the text **/
   private static final float sXOffset = 0.7f;
   /** The y offset of the text **/
   private static final float sYOffset = 0.7f;   
   
   /** The player data **/
   protected PlayerData mPlayerData;
}
