package games.ghoststories.views.gameboard;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.common.BuddhaTokenView;
import games.ghoststories.views.common.QiTokenView;
import games.ghoststories.views.common.TaoTokenView;
import games.ghoststories.views.common.YinYangTokenView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * View representing a single players token area. The token area contains all
 * of the Tao Tokens, Qi Tokens, Buddha Tokens and Yin Yangs that a player 
 * currently posseses. 
 */
public class PlayerTokenAreaView extends LinearLayout {
   
   /**
    * Constructor
    * @param pContext
    */
   public PlayerTokenAreaView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public PlayerTokenAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public PlayerTokenAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /**
    * Sets the player data used to populate the view.
    * @param pData The data model
    */
   public void setPlayerData(PlayerData pData) {
      mPlayerData = pData;
      
      //Update the different token views in this player area      
      BuddhaTokenView buddhaTokenView = (BuddhaTokenView)findViewById(R.id.buddha_tokens);
      buddhaTokenView.setData(mPlayerData);
      
      QiTokenView qiTokenView = (QiTokenView)findViewById(R.id.qi_tokens);
      qiTokenView.setData(mPlayerData);
      
      for(EColor color : EColor.values()) {
         TaoTokenView taoTokenView = (TaoTokenView)findViewById(color.getTokenId());
         taoTokenView.setData(mPlayerData);
      }
            
      YinYangTokenView yinYangTokenView = (YinYangTokenView)findViewById(R.id.yin_yang);
      yinYangTokenView.setData(mPlayerData);      
      
      ImageView abilityView = (ImageView)findViewById(R.id.ability);
      if(abilityView.getDrawable() == null) {
         GameUtils.setBoardLocationResource(abilityView, pData.getBoardLocation(), 
               pData.getAbility().getDrawableId());
      } 
   }     
   
   /** The data model **/
   private PlayerData mPlayerData;
}
