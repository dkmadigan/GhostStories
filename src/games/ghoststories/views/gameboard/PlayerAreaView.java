package games.ghoststories.views.gameboard;

import com.drawable.shapes.GradientRectangle;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PlayerAreaView extends LinearLayout {
   public PlayerAreaView(Context context) {
      super(context);      
      initialize();
   }
   
   public PlayerAreaView(Context context, AttributeSet attrs) {
      super(context, attrs);
      initialize();
   }
   
   public PlayerAreaView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      initialize();
   } 
      
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;  
      EColor color = mGameBoardData.getColor();
      mPaint.setColor(color.getColor());
      
      int lightColor = color.getLightColor();
      int darkColor = color.getDarkColor();
      setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
            Color.argb(100, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
            Color.argb(100, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
            Color.BLACK));
      
      GameUtils.invalidateView(this);
   }
   
   @Override
   protected void onDraw(Canvas pCanvas) {      
      super.onDraw(pCanvas);      
      
      if(!isInEditMode()) {
         if(mRect == null) {                              
            mRect = new RectF(0, 0, getWidth(), getHeight());
         }
         //pCanvas.drawRoundRect(mRect, 15, 15, mPaint);
      }      
   }
   
   private void initialize() {
      setWillNotDraw(false);
      mPaint.setStyle(Style.STROKE);
      mPaint.setStrokeWidth(6.0f);
      mPaint.setAntiAlias(true);
      mPaint.setDither(true);
      mPaint.setStrokeCap(Paint.Cap.ROUND);
      mPaint.setStrokeJoin(Paint.Join.ROUND);
      
      if(isInEditMode()) {
         //Cheap hack to test layouts in editor mode.                
         if(sInstanceCount+1 < EColor.values().length) {
            EColor color = EColor.values()[sInstanceCount+1];
            int lightColor = color.getLightColor();
            int darkColor = color.getDarkColor();
            setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
                  Color.argb(100, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
                  Color.argb(100, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
                  Color.BLACK));
            sInstanceCount++; 
         }
      }      
   }
   
   private GameBoardData mGameBoardData = null;
   private Paint mPaint = new Paint();
   private RectF mRect = null;
   private static int sInstanceCount = 0;
}
