package com.views.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class SquareGridLayout extends GridLayout {
   public SquareGridLayout(Context context) {
      super(context);
   }
   
   public SquareGridLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
   }
   
   public SquareGridLayout(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
   }
   
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {    
      super.onMeasure(heightMeasureSpec, heightMeasureSpec);
   }
     
}
