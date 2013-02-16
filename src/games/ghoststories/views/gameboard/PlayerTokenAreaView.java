package games.ghoststories.views.gameboard;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.ImageViewUtils;
import games.ghoststories.views.common.BuddhaTokenView;
import games.ghoststories.views.common.QiTokenView;
import games.ghoststories.views.common.TaoTokenView;
import games.ghoststories.views.common.YinYangTokenView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayerTokenAreaView extends LinearLayout {
   public PlayerTokenAreaView(Context pContext) {
      super(pContext);
   }
   
   public PlayerTokenAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }
   
   public PlayerTokenAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
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
         ImageViewUtils.setBoardLocationResource(abilityView, pData.getBoardLocation(), 
               pData.getAbility().getDrawableId());
      } 
   }     
   
   private PlayerData mPlayerData;
}
