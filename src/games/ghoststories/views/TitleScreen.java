package games.ghoststories.views;

import games.ghoststories.activities.GameLoadingActivity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class TitleScreen extends LinearLayout implements OnClickListener {

   public TitleScreen(Context pContext) {
      super(pContext);
      setOnClickListener(this);
   }
   
   public TitleScreen(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      setOnClickListener(this);
   }
   
   public TitleScreen(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      setOnClickListener(this);
   }

   public void onClick(View pView) {
      //Transition to the loading activity
      Intent intent = new Intent(getContext(), GameLoadingActivity.class);
      getContext().startActivity(intent);
   }
}
