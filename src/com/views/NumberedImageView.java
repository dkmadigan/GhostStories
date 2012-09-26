package com.views;

import games.ghoststories.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * {@link ImageView} that overlays a number on top of the image. By default the
 * number is centered on the image and the image is made semi-transparent when
 * the number count reaches zero.  
 */
public class NumberedImageView extends ImageView {

   /**
    * Constructor
    * @param pContext The context the view is running in
    */
   public NumberedImageView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor 
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public NumberedImageView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor 
    * @param pContext The context used to build the view
    * @param pAttrs The attributes of the view
    * @param pDefStyle The default style applied to this view
    */
   public NumberedImageView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      this(pContext, pAttrs, pDefStyle, sDefaultXOffset, sDefaultYOffset, -1);      
   }
   
   /**
    * Constructor
    * @param pContext The context used to build the view
    * @param pAttrs The attributes of the view
    * @param pDefStyle The default style applied to this view
    * @param pDefaultXOffset The default value of the x offset if not set as one
    *                        of the attributes
    * @param pDefaultYOffset The default value of the y offset if not set as one
    *                        of the attributes
    * @param pDefaultTextSize The default value of the text size if not set as
    *                         one of the attributes
    */
   public NumberedImageView(Context pContext, AttributeSet pAttrs, int pDefStyle,
         float pDefaultXOffset, float pDefaultYOffset, int pDefaultTextSize) {
      super(pContext, pAttrs, pDefStyle);
      mXOffset = pDefaultXOffset;
      mYOffset = pDefaultYOffset;
      mTextSize = pDefaultTextSize;
      
      //Read in the attributes and set the values if specified
      TypedArray a = pContext.obtainStyledAttributes(pAttrs,
            R.styleable.NumberedImageView);
      for (int i = 0; i < a.getIndexCount(); ++i)
      {
         int attr = a.getIndex(i);
         switch (attr)
         {
         case R.styleable.NumberedImageView_text_size:            
            mTextSize = a.getInteger(attr, pDefaultTextSize);            
            break;
         case R.styleable.NumberedImageView_x_offset:
            mXOffset = a.getFloat(attr, pDefaultXOffset);
            break;
         case R.styleable.NumberedImageView_y_offset:
            mYOffset = a.getFloat(attr, pDefaultYOffset);
            break;
         }
      }
      a.recycle();
   }
   
   /**
    * @return The text size of the number overlay
    */
   public int getTextSize() {
      return mTextSize;
   }
   
   /**
    * @return The x offset of the number overlay
    */
   public float getXOffset() {
      return mXOffset;
   }
   
   /**
    * @return The y offset of the number overlay
    */
   public float getYOffset() {
      return mYOffset;
   }
   
   /**
    * @return The overlay number
    */
   public int getNumber() {
      return mNumber;
   }
   
   /**
    * Sets the number shown in the overlay
    * @param pNumber The number shown in the overlay
    */
   public void setNumber(int pNumber) {
      mNumber = pNumber;
      //Trigger a repaint of this component when the number changes
      postInvalidate();      
   }
   
   /**
    * Sets the text size for the number overlay
    * @param pTextSize The text size of the number overlay
    */
   public void setTextSize(int pTextSize) {
      mTextSize = pTextSize;
      //Trigger a repaint of this component when the text size changes
      postInvalidate();
   }
   
   /**
    * Sets the x offset for the number overlay. This is a percentage of the
    * total width of the component.
    * @param pXOffset The x offset for the number overlay
    */
   public void setXOffset(float pXOffset) {
      mXOffset = pXOffset;
      //Trigger a repaint of this component when the offset changes
      postInvalidate();
   }
   
   /**
    * Sets the y offset for the number overlay. This is a percentage of the
    * total height of the component.
    * @param pYOffset The y offset for the number overlay
    */
   public void setYOffset(float pYOffset) {
      mYOffset = pYOffset;
      //Trigger a repaint of this component when the offset changes
      postInvalidate();
   }
   
   /**
    * Called during the draw operation to update the alpha value based on the
    * current number. By default makes the image semi-transparent if the number
    * is zero.
    */
   protected void updateAlpha() {      
      //If 0 then make semi-transparent
      if(mNumber == 0) {
         setAlpha(sZeroAlpha);
      } else {
         setAlpha(1.0f);
      }
   }
   
   /*
    * (non-Javadoc)
    * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {
      updateAlpha();
      super.onDraw(pCanvas);           
      
      //Draw the number on top of the image
      Paint paint = new Paint();
      paint.setColor(Color.WHITE);
      paint.setStyle(Style.FILL);      
      if(mTextSize != -1) {
         paint.setTextSize(mTextSize);
      }      
      paint.setTextAlign(Align.CENTER);
      paint.setTypeface(Typeface.SERIF);
      pCanvas.drawText(Integer.toString(mNumber), 
            getWidth() * mXOffset, getHeight() * mYOffset, paint);
   }
   
   /** The text size of the overlay **/
   protected int mTextSize = -1;
   
   /** The default x offset **/
   private static final float sDefaultXOffset = 0.5f;
   /** The default y offset **/
   private static final float sDefaultYOffset = 0.5f;
   /** The alpha value when the number is zero **/
   private static final float sZeroAlpha = 0.3f;
   
   /** The number to show in the overlay **/
   private int mNumber;
   /** The x offset of the number overlay **/     
   private float mXOffset;
   /** The y offset of the number overlay **/
   private float mYOffset;   
}
