package games.ghoststories.controllers;

import android.view.View;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.village.VillageTileData;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.gameboard.VillageTileView;

/**
 * Controller class for the different village tiles. Handles moving the player
 * during the move phase and also the different village tile abilities. There
 * is a single {@link VillageTileController} per village tile.
 */
public class VillageTileController implements View.OnClickListener {

   /**
    * Constructor
    * @param pData The data for the village tile
    * @param pView The view for the village tile
    */
   public VillageTileController(VillageTileData pData, VillageTileView pView) {
      mTileData = pData;
      mTileView = pView;
      mTileView.setOnClickListener(this);
   }
   
   /*
    * (non-Javadoc)
    * @see android.view.View.OnClickListener#onClick(android.view.View)
    */
   public void onClick(View pView) {
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      if(gm.getCurrentGamePhase() == EGamePhase.YangPhase1 &&
            GameUtils.isVillageTileReachable(mTileData, gm.getCurrentPlayerData())) {
         //Move the current player to this tile and advance the game phase
         gm.getCurrentPlayerData().setLocation(mTileData.getLocation());
         gm.advanceGamePhase();
      }
   }
   
   /** The data for the village tile **/
   private final VillageTileData mTileData;
   /** The view for the village tile **/
   private final VillageTileView mTileView;
}
