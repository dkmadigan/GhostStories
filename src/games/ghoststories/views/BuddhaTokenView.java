package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.utils.ImageRotationTask;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

public class BuddhaTokenView extends AbstractNumberedTokenView {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public BuddhaTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public BuddhaTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public BuddhaTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.views.AbstractNumberedTokenView#setPlayerData(games.ghoststories.data.PlayerData)
    */
   @Override
   public void setPlayerData(final PlayerData pData) {      
      super.setPlayerData(pData);        
      setNumber(mPlayerData.getNumBuddhaTokens());                 
      ImageViewUtils.setBoardLocationResource(this, pData.getBoardLocation(), 
            R.drawable.buddha);           
   }     
}
