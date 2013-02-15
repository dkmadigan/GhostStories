package games.ghoststories.views.combat;

import games.ghoststories.data.GhostStoriesConstants;
import android.content.Context;
import android.util.AttributeSet;

public class ExtraCombatDiceView extends CombatDiceView {

   public ExtraCombatDiceView(Context pContext) {
      super(pContext);
   }

   public ExtraCombatDiceView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public ExtraCombatDiceView(Context pContext, AttributeSet pAttrs,
         int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   @Override
   protected int getNextDiceDrawable() {
      mDiceSide = sDiceSides.get(GhostStoriesConstants.sRandom.nextInt(
            sDiceSides.size()));
      return mDiceSide.getExtraDiceDrawable();
   }

}
