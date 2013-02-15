package games.ghoststories.views.aux_area;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class CardInfoView extends RelativeLayout {

   public CardInfoView(Context pContext) {
      super(pContext);
      init();
   }

   public CardInfoView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public CardInfoView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
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
