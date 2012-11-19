package com.views.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ScaledLinearLayout extends LinearLayout {
   public ScaledLinearLayout(Context pContext) {
      super(pContext);   
  }

  public ScaledLinearLayout(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
  }

  public ScaledLinearLayout(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
     super.onMeasure(widthMeasureSpec*4, heightMeasureSpec*4);
  }
}
