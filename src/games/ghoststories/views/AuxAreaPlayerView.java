package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.IGamePhaseListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AuxAreaPlayerView extends ImageView  implements IGamePhaseListener {

   public AuxAreaPlayerView(Context pContext) {
      super(pContext);
      initialize();
   }

   public AuxAreaPlayerView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      initialize();
   }

   public AuxAreaPlayerView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      initialize();
   }
   
   private void initialize() {
      GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
      updatePlayerImage();
   }
   
   public void gamePhaseUpdated(EGamePhase pGamePhase) {      
      if(pGamePhase == EGamePhase.EYinPhase1A) {
         updatePlayerImage();
      }
   }     
   
   private void updatePlayerImage() {
      EColor c = 
            GhostStoriesGameManager.getInstance().getCurrentPlayerData().getColor();
      ImageViewUtils.setImageBitmapEDT(this, 
            GhostStoriesBitmaps.sPlayerBitmaps.get(c));
   }

}
