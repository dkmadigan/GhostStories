package games.ghoststories.views.combat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * {@link ImageView} representing a single damage token that has been placed
 * on a ghost during the combat phase.
 */
//TODO Maybe can get rid of this
public class CombatDamageView extends ImageView {

   /**
    * Constructor
    * @param pContext
    */
   public CombatDamageView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatDamageView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatDamageView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
}
