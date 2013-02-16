package games.ghoststories.views.common;

import games.ghoststories.data.PlayerData;
import games.ghoststories.data.TokenSupplyData;
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
    * @see games.ghoststories.data.interfaces.ITokenListener#tokenDataUpdated()
    */
   public void tokenDataUpdated() {
      int numTokens = mData.getNumQi();
      if(numTokens != getNumber()) {
         setNumber(numTokens);
      }      
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.views.common.AbstractNumberedTokenView#setData(games.ghoststories.data.TokenSupplyData)
    */
   @Override
   public void setData(TokenSupplyData pData) {
      super.setData(pData);
      setNumber(pData.getNumQi());
   }
}