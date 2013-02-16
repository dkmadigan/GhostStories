package games.ghoststories.views.common;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class YinYangTokenView extends ImageView {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public YinYangTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public YinYangTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public YinYangTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      //Since the text size is determined based on the view size, it must
      //be set in onDraw after the component has a height and width.
      super(pContext, pAttrs, pDefStyle);
   }
   
   /**
    * Sets the player data used to populate this view
    * @param pData The player data
    */
   public void setData(PlayerData pData) {
      mPlayerData = pData;
      switch(mPlayerData.getColor()) {
      case BLUE:
         setImageResource(R.drawable.yin_yang_blue);
         break;
      case GREEN:
         setImageResource(R.drawable.yin_yang_green);
         break;
      case RED:
         setImageResource(R.drawable.yin_yang_red);
         break;
      case YELLOW:
         setImageResource(R.drawable.yin_yang_yellow);
         break;
      default:
         break;
      }
   }
   
   private PlayerData mPlayerData;
}
