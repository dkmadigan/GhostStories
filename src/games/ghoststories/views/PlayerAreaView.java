package games.ghoststories.views;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PlayerAreaView extends LinearLayout {
   public PlayerAreaView(Context context) {
      super(context);
      setWillNotDraw(false);
   }
   
   public PlayerAreaView(Context context, AttributeSet attrs) {
      super(context, attrs);
      setWillNotDraw(false);
   }
   
   public PlayerAreaView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      setWillNotDraw(false);
   } 
   
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;       
      
      //Update the background color
      if(GameUtils.isUIThread()) {
         invalidate();
         //setBackgroundColor(mGameBoardData.getColor().getColor());
      } else {
         postInvalidate();
         /*post(new Runnable() {
            public void run() {
               //setBackgroundColor(mGameBoardData.getColor().getColor());
            }
         });*/
      }
   }
   
   @Override
   protected void onDraw(Canvas pCanvas) {      
      super.onDraw(pCanvas);      
      
      Paint paint = new Paint();           
      paint.setColor(mGameBoardData.getColor().getColor());
      paint.setStyle(Style.STROKE);
      paint.setStrokeWidth(6.0f);
      paint.setAntiAlias(true);
      paint.setDither(true);
      paint.setStrokeCap(Paint.Cap.ROUND);
      paint.setStrokeJoin(Paint.Join.ROUND);      
      pCanvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), 15, 15, paint);
      //pCanvas.drawRect(0, 0, getWidth(), getHeight(), paint);
      
   }
   
   private GameBoardData mGameBoardData = null;
}
