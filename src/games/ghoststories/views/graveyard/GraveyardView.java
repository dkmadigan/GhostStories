package games.ghoststories.views.graveyard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class GraveyardView extends RelativeLayout {

   public GraveyardView(Context pContext) {
      super(pContext);
      init();
   }

   public GraveyardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public GraveyardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }
      
   private void init() {
      setOnTouchListener(new OnTouchListener() {         
         public boolean onTouch(View v, MotionEvent event) {
            return true;
         }
      });
   }
}

