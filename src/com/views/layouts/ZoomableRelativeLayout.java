package com.views.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.RelativeLayout;

public class ZoomableRelativeLayout extends RelativeLayout {
   public ZoomableRelativeLayout(Context pContext) {
      super(pContext);   
      createDetector();
  }

  public ZoomableRelativeLayout(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      createDetector();
  }

  public ZoomableRelativeLayout(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      createDetector();
  }
  /*
  protected void dispatchDraw(Canvas canvas) {     
     //canvas.scale(mScaleFactor, mScaleFactor, mScaleX, mScaleY);
     canvas.scale(mScaleFactor, mScaleFactor);
     canvas.save(Canvas.MATRIX_SAVE_FLAG);
     super.dispatchDraw(canvas);     
    canvas.restore();
 }
*/
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
     //super.onMeasure(widthMeasureSpec*4, heightMeasureSpec*4);
     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  public boolean onTouchEvent(MotionEvent pEvent) {
     mDetector.onTouchEvent(pEvent);
     return true;
  }
  
  private void createDetector() {
     mDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
  }
     
  private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
     @Override
   public boolean onScaleBegin(ScaleGestureDetector detector) {
      mScaleX = detector.getFocusX() * mScaleFactor;
      mScaleY = detector.getFocusY() * mScaleFactor;                 
      return true;
   }
     
     @Override
     public boolean onScale(ScaleGestureDetector detector) {
        Log.d("ZoomableRelativePanel", "scale factor = " + detector.getScaleFactor());
        mScaleFactor *= detector.getScaleFactor();
        mScaleFactor = Math.max(MIN_ZOOM, Math.min(mScaleFactor, MAX_ZOOM));
        invalidate();
        requestLayout();
        return true;
     }
  }
  
  private static float MIN_ZOOM = 0.25f;
  private static float MAX_ZOOM = 1f;
  
  public float mScaleFactor = 1.f;
  public float mScaleX = 0.0f;
  public float mScaleY = 0.0f;
  private ScaleGestureDetector mDetector;
  
}