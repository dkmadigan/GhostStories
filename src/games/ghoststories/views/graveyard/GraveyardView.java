package games.ghoststories.views.graveyard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * View for the graveyard overlay. Contains a scrollable area that shows all
 * of the defeated ghosts.
 */
public class GraveyardView extends RelativeLayout {

   /**
    * Constructor
    * @param pContext
    */
   public GraveyardView(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GraveyardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GraveyardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }
      
   /**
    * Initialize the view
    */
   private void init() {
      //Ignore touches so they don't propagate to the background
      setOnTouchListener(new OnTouchListener() {         
         public boolean onTouch(View v, MotionEvent event) {
            return true;
         }
      });
   }
}
