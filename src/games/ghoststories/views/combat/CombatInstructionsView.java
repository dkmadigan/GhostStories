package games.ghoststories.views.combat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * View that shows the instructions for the current combat phase.
 */
//TODO Maybe can get rid of this
public class CombatInstructionsView extends TextView {

   /**
    * Constructor
    * @param pContext
    */
   public CombatInstructionsView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatInstructionsView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatInstructionsView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
}
