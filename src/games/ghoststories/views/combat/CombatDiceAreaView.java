package games.ghoststories.views.combat;

import java.util.EnumSet;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EDice;
import games.ghoststories.enums.EPlayerAbility;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class CombatDiceAreaView extends LinearLayout {

   public CombatDiceAreaView(Context pContext) {
      super(pContext);
   }

   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }     
   
   public void setPrimaryAttacker(PlayerData pPlayerData) {
      mPrimaryAttacker = pPlayerData;
      int numDice = GhostStoriesGameManager.getInstance().getNumDice();
      getDiceView(EDice.DICE_1).setVisibility(EDice.DICE_1.ordinal() < numDice ? VISIBLE : GONE);
      getDiceView(EDice.DICE_1).setVisibility(EDice.DICE_2.ordinal() < numDice ? VISIBLE : GONE);
      getDiceView(EDice.DICE_1).setVisibility(EDice.DICE_3.ordinal() < numDice ? VISIBLE : GONE);
                                       
      //The strength of the mountain ability gives the player an extra dice
      if(mPrimaryAttacker.getAbility() == EPlayerAbility.STRENGTH_OF_THE_MOUNTAIN &&
            mPrimaryAttacker.getAbilityActive()) {
         getDiceView(EDice.EXTRA_DICE).setVisibility(VISIBLE);
      } else {
         getDiceView(EDice.EXTRA_DICE).setVisibility(GONE);
      }
   }
   
   private CombatDiceView getDiceView(EDice pDice) {
      return ((CombatDiceView)findViewById(pDice.getViewId()));
   }
   
   private PlayerData mPrimaryAttacker;
}
