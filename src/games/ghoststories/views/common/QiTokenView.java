package games.ghoststories.views.common;

import games.ghoststories.data.PlayerData;
import android.content.Context;
import android.util.AttributeSet;

/**
 * View representing a stack of qi tokens.
 */
public class QiTokenView extends AbstractNumberedTokenView {

   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public QiTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public QiTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public QiTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.IPlayerDataListener#playerDataUpdated()
    */
   public void playerDataUpdated() {
      int numTokens = mPlayerData.getQi();
      if(numTokens != getNumber()) {
         setNumber(numTokens);
      }      
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.views.AbstractNumberedTokenView#setPlayerData(games.ghoststories.data.PlayerData)
    */
   @Override
   public void setPlayerData(PlayerData pData) {
      super.setPlayerData(pData);
      setNumber(mPlayerData.getQi());
   }
}