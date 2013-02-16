package games.ghoststories.views.common;

import games.ghoststories.R;
import games.ghoststories.data.DragData;
import games.ghoststories.data.TokenSupplyData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDragItem;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.interfaces.IDraggable;

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
            R.styleable.TaoTokenView);
      for (int i = 0; i < a.getIndexCount(); ++i)
      {
         int attr = a.getIndex(i);
         switch (attr)
         {
         case R.styleable.TaoTokenView_token_color: 
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
    * @see games.ghoststories.data.interfaces.ITokenListener#tokenDataUpdated()
    */
   public void tokenDataUpdated() {
      int numTokens = mData.getNumTaoTokens(mTokenColor);
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
      setNumber(mData.getNumTaoTokens(mTokenColor));
   }
   
   /** The color of the token this view represents **/
   private EColor mTokenColor = EColor.BLACK;
}
