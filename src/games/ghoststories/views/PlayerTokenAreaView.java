package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import android.content.Context;
import android.util.AttributeSet;
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
      buddhaTokenView.setPlayerData(mPlayerData);
      
      QiTokenView qiTokenView = (QiTokenView)findViewById(R.id.qi_tokens);
      qiTokenView.setPlayerData(mPlayerData);
      
      for(EColor color : EColor.values()) {
         TauTokenView tauTokenView = (TauTokenView)findViewById(color.getTokenId());
         tauTokenView.setPlayerData(mPlayerData);
      }
      
      YinYangTokenView yinYangTokenView = (YinYangTokenView)findViewById(R.id.yin_yang);
      yinYangTokenView.setPlayerData(mPlayerData);      
   }     
   
   private PlayerData mPlayerData;
}
