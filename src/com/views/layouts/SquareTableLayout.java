package com.views.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

public class SquareTableLayout extends TableLayout {

   public SquareTableLayout(Context context) {
      super(context);
   }
   
   public SquareTableLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
   }
  
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {    
      super.onMeasure(heightMeasureSpec, heightMeasureSpec);
   }
}
