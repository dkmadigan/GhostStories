package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.IGameBoardListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.BitmapUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayerBoardView extends LinearLayout {
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
