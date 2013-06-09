package games.ghoststories.views.title;

import games.ghoststories.data.GhostStoriesConstants;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class TitleButton extends Button {

   public TitleButton(Context pContext) {
      super(pContext);
      init();
   }

   public TitleButton(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public TitleButton(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }

   /**
    * Initialize this view
    */
   private void init() {
      if(!isInEditMode()) {
         Typeface face=Typeface.createFromAsset(getContext().getAssets(),
               GhostStoriesConstants.sFont);
         setTypeface(face);
      }
   }

   @Override
   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if(!isPressed()) {
         setShadowLayer(8, -4.0f, -4.0f, Color.BLACK);
         setTranslationX(2.0f);
         setTranslationY(2.0f);
      } else {
         setShadowLayer(8, -1.0f, -1.0f, Color.BLACK);
         setTranslationX(-1.0f);
         setTranslationY(-1.0f);
      }
   }
}
