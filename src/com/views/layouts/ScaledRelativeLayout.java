package com.views.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ScaledRelativeLayout extends RelativeLayout {
   public ScaledRelativeLayout(Context pContext) {
      super(pContext);   
  }

  public ScaledRelativeLayout(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
  }

  public ScaledRelativeLayout(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
     super.onMeasure(widthMeasureSpec*4, heightMeasureSpec*4);
  }
}
