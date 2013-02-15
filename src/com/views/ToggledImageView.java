package com.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * An {@link ImageView} that can be "toggled" on or off. When a view is toggled
 * off it is set to mostly transparent and disabled. When a view is toggled
 * on it is set to full opaque and enabled.
 */
public class ToggledImageView extends ImageView {

   /**
    * Constructor
    * @param pContext
    */
   public ToggledImageView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public ToggledImageView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public ToggledImageView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }

   /*
    * (non-Javadoc)
    * @see android.view.View#setEnabled(boolean)
    */
   @Override
   public void setEnabled(boolean pEnabled) {    
      super.setEnabled(pEnabled);
      setAlpha(pEnabled ? 1.0f : sDisabledAlpha);
   }
   
   /** Alpha value for a disabled image view **/
   private static final float sDisabledAlpha = 0.2f;
}
