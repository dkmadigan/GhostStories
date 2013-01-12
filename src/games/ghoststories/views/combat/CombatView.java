package games.ghoststories.views.combat;

import java.util.ArrayList;
import java.util.List;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.views.aux_area.PlayerInfoView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CombatView extends RelativeLayout {

   public CombatView(Context pContext) {
      super(pContext);
   }

   public CombatView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public CombatView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   public void setCombatData(PlayerData pPrimaryAttacker, 
         List<PlayerData> pSecondaryAttackers, GhostData pGhost1, 
         GhostData pGhost2) {
      //Info area 1 is for the primary player
      mPrimaryAttacker = pPrimaryAttacker;
      PlayerInfoView pi1 = (PlayerInfoView)findViewById(R.id.info_area_1);
      pi1.setPlayerData(pPrimaryAttacker);
      pi1.setVisibility(VISIBLE);
      
      //Info area 2-4 are for any supporting players      
      mSecondaryAttackers = pSecondaryAttackers;  
      List<PlayerInfoView> views = new ArrayList<PlayerInfoView>();
      views.add((PlayerInfoView)findViewById(R.id.info_area_2));
      views.add((PlayerInfoView)findViewById(R.id.info_area_3));
      views.add((PlayerInfoView)findViewById(R.id.info_area_4));
      for(PlayerInfoView piv : views) {
         piv.setVisibility(INVISIBLE);
      }      
      for(int i = 0; i < mSecondaryAttackers.size(); ++i) {
         PlayerInfoView view = views.get(i);
         PlayerData data = mSecondaryAttackers.get(i);
         view.setVisibility(VISIBLE);
         view.setPlayerData(data);         
      }
      
      //Set the ghost data
      CombatGhostView ghost1 = (CombatGhostView)findViewById(R.id.ghost1);
      ghost1.setVisibility(GONE);
      CombatGhostView ghost2 = (CombatGhostView)findViewById(R.id.ghost2);
      ghost2.setVisibility(GONE);
      
      if(pGhost1 != null) {
         ghost1.setGhostData(pGhost1);        
      }
      if(pGhost2 != null) {
         ghost2.setGhostData(pGhost2);
      }
      
   }
   
   private PlayerData mPrimaryAttacker = null;
   private List<PlayerData> mSecondaryAttackers = null;

}
