package games.ghoststories.views.common;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.TokenSupplyData;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
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
    * @see games.ghoststories.data.interfaces.ITokenListener#tokenDataUpdated()
    */
   public void tokenDataUpdated() { 
      int numTokens = mPlayerData.getNumBuddhaTokens();
      if(numTokens != getNumber()) {
         setNumber(numTokens);
      }
   }
      
   public void setData(PlayerData pData) {
      super.setData(pData);
      mPlayerData = pData;
      setNumber(mPlayerData.getNumBuddhaTokens());             
      if(getDrawable() == null) {
         ImageViewUtils.setBoardLocationResource(this, pData.getBoardLocation(), 
               R.drawable.buddha);
      }      
   } 
   
   private PlayerData mPlayerData;
}
