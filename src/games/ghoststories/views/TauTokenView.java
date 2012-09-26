package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * View representing a stack of tau tokens.
 */
public class TauTokenView extends AbstractNumberedTokenView {

   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public TauTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public TauTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public TauTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
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
   
   @Override
   public void setPlayerData(PlayerData pData) {      
      super.setPlayerData(pData);
      setNumber(mPlayerData.getNumTauTokens(mTokenColor));
   }
   
   private EColor mTokenColor = EColor.BLACK;
}
