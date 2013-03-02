package games.ghoststories.views.combat;

import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EDice;
import games.ghoststories.enums.EPlayerAbility;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Container view for the dice that are part of the combat phase.
 */
public class CombatDiceAreaView extends LinearLayout {

   /**
    * Constructor
    * @param pContext
    */
   public CombatDiceAreaView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }     
   
   /**
    * Set the primary attacker during this combat phase. If the primary attacker
    * has the {@link EPlayerAbility#STRENGTH_OF_THE_MOUNTAIN} ability and that
    * ability is active then they get an extra dice to roll.
    * @param pPlayerData The primary attacker data
    */
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
   
   /**
    * Helper method to get the dice view for the given {@link EDice} value
    * @param pDice The dice to get the view for
    * @return The view for the specified dice
    */
   private CombatDiceView getDiceView(EDice pDice) {
      return ((CombatDiceView)findViewById(pDice.getViewId()));
   }
   
   /** The primary attacker data **/
   private PlayerData mPrimaryAttacker;
}
