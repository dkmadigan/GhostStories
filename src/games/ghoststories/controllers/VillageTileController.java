package games.ghoststories.controllers;

import android.view.View;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.gameboard.VillageTileView;

public class VillageTileController implements View.OnClickListener {

   public VillageTileController(VillageTileData pData, VillageTileView pView) {
      mTileData = pData;
      mTileView = pView;
      mTileView.setOnClickListener(this);
   }
   
   public void onClick(View pView) {
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      if(gm.getCurrentGamePhase() == EGamePhase.YangPhase1 &&
            GameUtils.isVillageTileReachable(mTileData, gm.getCurrentPlayerData())) {
         //Move the current player to this tile and advance the game phase
         gm.getCurrentPlayerData().setLocation(mTileData.getLocation());
         gm.advanceGamePhase();
      }
   }
   
   private final VillageTileData mTileData;
   private final VillageTileView mTileView;
}
