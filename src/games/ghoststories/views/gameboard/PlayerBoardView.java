package games.ghoststories.views.gameboard;

import com.drawable.shapes.GradientRectangle;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.enums.EColor;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class PlayerBoardView extends RelativeLayout {
   public PlayerBoardView(Context pContext) {
      super(pContext);
   }

   public PlayerBoardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public PlayerBoardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }   

   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData; 
   }   
  
   private GameBoardData mGameBoardData = null;   
}
