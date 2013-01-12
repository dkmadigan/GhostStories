package games.ghoststories.views.combat;

import games.ghoststories.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class CombatDiceAreaView extends LinearLayout implements OnClickListener {

   public CombatDiceAreaView(Context pContext) {
      super(pContext);
      init();
   }

   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public CombatDiceAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }     
   
   public void onClick(View v) {
      //Start the rolling animation
      ((CombatDiceView)findViewById(R.id.dice_1)).startDiceAnimation();
      ((CombatDiceView)findViewById(R.id.dice_2)).startDiceAnimation();
      ((CombatDiceView)findViewById(R.id.dice_3)).startDiceAnimation();
      ((CombatDiceView)findViewById(R.id.extra_dice)).startDiceAnimation();
   }
   
   private void init() {
      setOnClickListener(this);
   }

}
