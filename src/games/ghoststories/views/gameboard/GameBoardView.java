package games.ghoststories.views.gameboard;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EBoardLocation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.views.layouts.SquareLinearLayout;

public class GameBoardView extends GridLayout {
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
   }
   
   
}

