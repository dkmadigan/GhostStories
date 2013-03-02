package games.ghoststories.views.aux_area;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Top level view for the Card Info overlay view. 
 */
public class CardInfoView extends RelativeLayout {

   /**
    * Constructor
    * @param pContext
    */
   public CardInfoView(Context pContext) {      
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CardInfoView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CardInfoView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }
      
   /**
    * Initialize the view. Sets a touch listener to consume touch events 
    * so they don't propagate into the background views.
    */
   private void init() {
      setOnTouchListener(new OnTouchListener() {         
         public boolean onTouch(View v, MotionEvent event) {
            return true;
         }
      });
   }
}
