package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.IGameBoardListener;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.utils.BitmapUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayerBoardView extends LinearLayout implements IGameBoardListener {
   public PlayerBoardView(Context pContext) {
      super(pContext);
   }

   public PlayerBoardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public PlayerBoardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   public void highlightCard(ECardLocation pCard) {
      int cardId;
      switch(pCard) {
      case LEFT:
         cardId = R.id.left_space;
         break;
      case MIDDLE:
         cardId = R.id.middle_space;
         break;
      case RIGHT:
         cardId = R.id.right_space;
         break;
      default:
         return;
      }
      ImageView card = (ImageView)findViewById(cardId);

      //card.setColorFilter(cf)
   }

   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;

      //Update the view based on the game board data
      updateBoardImage(R.id.left_space, mGameBoardData.getImageId(ECardLocation.LEFT));
      updateBoardImage(R.id.middle_space, mGameBoardData.getImageId(ECardLocation.MIDDLE));
      updateBoardImage(R.id.right_space, mGameBoardData.getImageId(ECardLocation.RIGHT));      
   }

   private void updateBoardImage(int pSpaceId, int pImageId) {
      ImageView space = (ImageView)findViewById(pSpaceId);
      //If this is a top or bottom board then just add the resource, 
      //else we need to rotate it and add it as a bitmap
      switch(mGameBoardData.getLocation()) {
      case BOTTOM:
      case TOP:
         space.setImageResource(pImageId);
         break;
      case RIGHT: 
      {
         Bitmap bm = BitmapFactory.decodeResource(getResources(), pImageId);
         Bitmap rotatedBitmap = BitmapUtils.rotateBitmap(bm, 270);
         space.setImageBitmap(rotatedBitmap);
      }
      break;  
      case LEFT:
      {
         Bitmap bm = BitmapFactory.decodeResource(getResources(), pImageId);
         Bitmap rotatedBitmap = BitmapUtils.rotateBitmap(bm, 90);
         space.setImageBitmap(rotatedBitmap);
      }
      break; 
      }      
   }

   private GameBoardData mGameBoardData = null;

   public void gameBoardUpdated() {
      // TODO Auto-generated method stub
      
   }
}
