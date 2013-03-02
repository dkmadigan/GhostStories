package games.ghoststories.views.combat;

import com.views.ToggledImageView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * View for the ROLL button during the combat phase.
 */
//TODO Maybe can get rid of this
public class CombatRollView extends ToggledImageView {

   /**
    * Constructor
    * @param pContext
    */
   public CombatRollView(Context pContext) {
      super(pContext);      
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatRollView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);      
   }

   public CombatRollView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }        
}
