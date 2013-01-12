package com.drawable.shapes;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class GradientRectangle extends GradientDrawable {

   public GradientRectangle() {      
   }

   public GradientRectangle(Orientation orientation, int[] colors) {
      super(orientation, colors);
   }
   
   public GradientRectangle(Orientation pOrientation, int pStartColor, 
         int pEndColor, float pRadius, int pBorderColor) {
      setOrientation(pOrientation);
      setCornerRadius(pRadius);
      int[] colors = new int[] {pStartColor, pEndColor};
      setColors(colors);      
      setGradientCenter(0.5f, 0.5f);
      setGradientType(LINEAR_GRADIENT);
      setStroke(2, pBorderColor);      
   }
}
