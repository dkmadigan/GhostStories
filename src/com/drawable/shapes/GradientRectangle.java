package com.drawable.shapes;

import android.graphics.drawable.GradientDrawable;

/**
 * Extends the {@link GradientDrawable} and draws a rectangle with rounded 
 * corners and a gradient.
 */
public class GradientRectangle extends GradientDrawable {

   /**
    * Constructor
    * @param pOrientation The orientation of the gradient
    * @param pStartColor The starting color of the gradient
    * @param pEndColor The ending color of the gradient
    * @param pRadius The radius of the corners of the rectangle. O for "squared"
    *                corners.
    * @param pBorderColor The color of the border of the rectangle
    */
   public GradientRectangle(Orientation pOrientation, int pStartColor, 
         int pEndColor, float pRadius, int pBorderColor) {
      setShape(RECTANGLE);
      setOrientation(pOrientation);
      setCornerRadius(pRadius);
      int[] colors = new int[] {pStartColor, pEndColor};
      setColors(colors);      
      setGradientCenter(0.5f, 0.5f);
      setGradientType(LINEAR_GRADIENT);
      setStroke(sBorderWidth, pBorderColor);      
   }
   
   /**
    * Sets the border color
    * @param pBorderColor
    */
   public void setBorderColor(int pBorderColor) {
      setStroke(sBorderWidth, pBorderColor);
   }
   
   /** Default width of the border **/
   private static final int sBorderWidth = 2;
}
