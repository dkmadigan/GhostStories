package games.ghoststories.views.common;

import com.interfaces.IDraggable;

import games.ghoststories.R;
import games.ghoststories.data.DragData;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDragItem;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * View representing a stack of tao tokens.
 */
public class TaoTokenView extends AbstractNumberedTokenView implements IDraggable<DragData>{

   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public TaoTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public TaoTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public TaoTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      
      //Read in the attributes and set the values if specified
      TypedArray a = pContext.obtainStyledAttributes(pAttrs,
            R.styleable.TauTokenView);
      for (int i = 0; i < a.getIndexCount(); ++i)
      {
         int attr = a.getIndex(i);
         switch (attr)
         {
         case R.styleable.TauTokenView_token_color: 
            int color = a.getInteger(attr, 0);            
            mTokenColor = EColor.values()[color];
            break;
         }
      }
      a.recycle();
   }
   
   /*
    * (non-Javadoc)
    * @see com.interfaces.IDraggable#getDragData()
    */
   public DragData getDragData() {
      return new DragData(EDragItem.COMBAT_TAO, mTokenColor, this);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.IPlayerDataListener#playerDataUpdated()
    */   
   public void playerDataUpdated() {
      int numTokens = mPlayerData.getNumTauTokens(mTokenColor);
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
      setNumber(mPlayerData.getNumTauTokens(mTokenColor));
   }
   
   /** The color of the token this view represents **/
   private EColor mTokenColor = EColor.BLACK;
}
