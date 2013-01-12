package games.ghoststories.views.gameboard;

import games.ghoststories.enums.EColor;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TableLayout;

public class VillageView extends TableLayout {

   public VillageView(Context pContext) {
      super(pContext);
   }

   public VillageView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   @Override
   protected void onDraw(Canvas pCanvas) {
      super.onDraw(pCanvas);
      /*
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      for(EColor c : EColor.getPlayerColors()) {
         VillageTileData villageData = gm.getVillageTile(c);
         VillageTileView view = 
               (VillageTileView)findViewById(villageData.getViewId());
         //view.setVisibility(View.INVISIBLE);
         float x = view.getX() + (view.getWidth() / 2);
         float y = view.getY() + (view.getHeight() / 2);
         pCanvas.drawBitmap(GhostStoriesBitmaps.sPlayerBitmaps.get(c), 
               x + getXOffset(c, view), 
               y + getYOffset(c, view), null);         
      } 
      */       
   }     
   
   private int getXOffset(EColor pColor, VillageTileView pView) {
      int xOffset = 0;
      switch(pColor) {
      case GREEN:
         xOffset = pView.getWidth() / 4;
         break;
      case YELLOW:
         xOffset = (pView.getWidth() / 4) * -1;
         break;
      case RED:
      case BLUE:
      default:
         xOffset = 0;
      }
      return xOffset;
   }
   
   private int getYOffset(EColor pColor, VillageTileView pView) {
      int yOffset = 0;
      switch(pColor) {
      case RED:
         yOffset = (pView.getHeight() / 4) * -1;
         break;
      case BLUE:
         yOffset = pView.getHeight() / 4;
         break;
      case GREEN:
      case YELLOW:
      default:
         yOffset = 0;
         break;
      }
      return yOffset;
   }
}
