package games.ghoststories.views.combat;

import games.ghoststories.data.GhostStoriesConstants;
import games.ghoststories.enums.EPlayerAbility;
import android.content.Context;
import android.util.AttributeSet;

/**
 * View that represents the "extra" dice that is shown during the combat phase.
 * This is applicable only for players with the 
 * {@link EPlayerAbility#STRENGTH_OF_THE_MOUNTAIN} ability.
 */
public class ExtraCombatDiceView extends CombatDiceView {

   /**
    * Constructor
    * @param pContext
    */
   public ExtraCombatDiceView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public ExtraCombatDiceView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public ExtraCombatDiceView(Context pContext, AttributeSet pAttrs,
         int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.views.combat.CombatDiceView#getNextDiceDrawable()
    */
   @Override
   protected int getNextDiceDrawable() {
      mDiceSide = sDiceSides.get(GhostStoriesConstants.sRandom.nextInt(
            sDiceSides.size()));
      return mDiceSide.getExtraDiceDrawable();
   }
}
