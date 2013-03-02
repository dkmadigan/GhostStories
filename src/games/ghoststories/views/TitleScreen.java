package games.ghoststories.views;

import games.ghoststories.activities.GameLoadingActivity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * The title screen view.
 */
public class TitleScreen extends LinearLayout implements OnClickListener {

   /**
    * Constructor
    * @param pContext
    */
   public TitleScreen(Context pContext) {
      super(pContext);
      setOnClickListener(this);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public TitleScreen(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      setOnClickListener(this);
   }
   
   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public TitleScreen(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      setOnClickListener(this);
   }

   /*
    * (non-Javadoc)
    * @see android.view.View.OnClickListener#onClick(android.view.View)
    */
   public void onClick(View pView) {
      //Transition to the loading activity
      Intent intent = new Intent(getContext(), GameLoadingActivity.class);
      getContext().startActivity(intent);
   }
}
