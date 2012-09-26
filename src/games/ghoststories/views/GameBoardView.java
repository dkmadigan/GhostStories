package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EBoardLocation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.views.layouts.SquareLinearLayout;

public class GameBoardView extends SquareLinearLayout {
   public GameBoardView(Context pContext) {
      super(pContext);
   }
   
   public GameBoardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }
   
   public GameBoardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   @Override
   protected void onDraw(Canvas pCanvas) {      
      super.onDraw(pCanvas);
      
      //Draw borders around the different player areas in the correct color
      drawBorder(pCanvas, R.id.top_board, R.id.top_token_area, EBoardLocation.TOP);
      drawBorder(pCanvas, R.id.right_board, R.id.right_token_area, EBoardLocation.RIGHT);
      drawBorder(pCanvas, R.id.bottom_board, R.id.bottom_token_area, EBoardLocation.BOTTOM);
      drawBorder(pCanvas, R.id.left_board, R.id.left_token_area, EBoardLocation.LEFT);
   }
   
   private void drawBorder(Canvas pCanvas, int pBoardId, int pTokenAreaId,
         EBoardLocation pBoardLocation) {
      PlayerBoardView boardView = 
            (PlayerBoardView)findViewById(pBoardId);
      PlayerTokenAreaView tokenAreaView = 
            (PlayerTokenAreaView)findViewById(pTokenAreaId);
      GameBoardData data = 
            GhostStoriesGameManager.getInstance().getGameBoard(pBoardLocation);
      Paint paint = new Paint();           
      paint.setColor(data.getColor().getColor());
      paint.setStyle(Style.STROKE);
      paint.setStrokeWidth(3.0f);
      paint.setAntiAlias(true);
      paint.setDither(true);
      paint.setStrokeCap(Paint.Cap.ROUND);
      paint.setStrokeJoin(Paint.Join.ROUND);
      LinearLayout boardParent = (LinearLayout)boardView.getParent();
      LinearLayout tokenAreaParent = (LinearLayout)tokenAreaView.getParent();
      switch(pBoardLocation) {
      case TOP:
         pCanvas.drawRect(tokenAreaView.getLeft(), tokenAreaView.getTop(), 
               boardView.getRight(), boardView.getBottom(), paint);
         break;         
      case BOTTOM:
         pCanvas.drawRect(boardView.getLeft(), boardParent.getTop(),
               tokenAreaView.getRight(), boardParent.getBottom(), paint);      
         break;         
      case LEFT:
         pCanvas.drawRect(boardView.getLeft(), boardParent.getTop(),
               tokenAreaView.getRight(), tokenAreaParent.getBottom(), paint);
         break;         
      case RIGHT:               
         pCanvas.drawRect(tokenAreaView.getLeft(), tokenAreaView.getTop(),
               tokenAreaView.getRight(), boardParent.getBottom(), paint);      
         break;                 
      }        
   }
}

