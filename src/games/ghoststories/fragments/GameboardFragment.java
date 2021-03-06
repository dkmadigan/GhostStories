package games.ghoststories.fragments;

import games.ghoststories.R;
import games.ghoststories.controllers.HaunterController;
import games.ghoststories.controllers.PlayerBoardCardController;
import games.ghoststories.controllers.VillageTileController;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.village.VillageTileData;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.ETileLocation;
import games.ghoststories.views.gameboard.PlayerAreaView;
import games.ghoststories.views.gameboard.PlayerBoardCardView;
import games.ghoststories.views.gameboard.PlayerBoardView;
import games.ghoststories.views.gameboard.PlayerTokenAreaView;
import games.ghoststories.views.gameboard.VillageTileView;

import java.util.Map;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;

/**
 * Fragment representing the game board area. The game board contains the four
 * different player areas and the village tile area. The player areas include
 * the card locations and player token areas.
 */
//TODO This class is pretty ugly in the way it builds the layout. Look to try
//and improve this.
public class GameboardFragment extends Fragment {
   
   /*
    * (non-Javadoc)
    * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
    */
   @Override
   public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
         Bundle pSavedInstanceState) {            
      Point size = new Point();
      getActivity().getWindowManager().getDefaultDisplay().getSize(size);
      //final int screenHeight = size.y*4;
      final int screenHeight = size.y;

      //final int boardHeight = (int)((float)screenHeight * (2.0f/7.0f));
      //final int boardWidth = (int)((float)screenHeight * (5.0f/7.0f));
      //int villageSize = (int)((float)screenHeight * (3.0f/7.0f));
      
      final int boardHeight = (int)((float)screenHeight * (13.0f/56.0f));
      final int boardWidth = (int)((float)screenHeight * (43.0f/56.0f));
      int villageSize = (int)((float)screenHeight * (30.0f/56.0f));
      
      GridLayout gridLayout = new GridLayout(getActivity());
      gridLayout.setClipChildren(false);
      gridLayout.setColumnCount(sNumCols);
      gridLayout.setColumnCount(sNumRows);
           
      //1280x752      Log.d("GameboardFragment", "screenHeight = " + screenHeight);
      Log.d("GameboardFragment", "screenWidth = " + size.x);
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      
      /*
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.top_player_area, 
            GridLayout.spec(0, 2), GridLayout.spec(0, 5), boardWidth, boardHeight,
            gm.getGameBoard(EBoardLocation.TOP));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.left_player_area,
            GridLayout.spec(2, 5), GridLayout.spec(0, 2), boardHeight, boardWidth,
            gm.getGameBoard(EBoardLocation.LEFT));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.right_player_area, 
            GridLayout.spec(0, 5), GridLayout.spec(5, 2), boardHeight, boardWidth,
            gm.getGameBoard(EBoardLocation.RIGHT));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.bottom_player_area, 
            GridLayout.spec(5, 2), GridLayout.spec(2, 5), boardWidth, boardHeight,
            gm.getGameBoard(EBoardLocation.BOTTOM));      
      createVillageArea(pInflater, pContainer, gridLayout, R.layout.village, 
            GridLayout.spec(2, 3), GridLayout.spec(2, 3), villageSize, villageSize);
      */
      
      createVillageArea(pInflater, pContainer, gridLayout, R.layout.village, 
            GridLayout.spec(13, 30), GridLayout.spec(13, 30), villageSize, villageSize);
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.top_player_area, 
            GridLayout.spec(0, 13), GridLayout.spec(0, 43), boardWidth, boardHeight,
            gm.getGameBoard(EBoardLocation.TOP));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.left_player_area,
            GridLayout.spec(13, 43), GridLayout.spec(0, 13), boardHeight, boardWidth,
            gm.getGameBoard(EBoardLocation.LEFT));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.right_player_area, 
            GridLayout.spec(0, 43), GridLayout.spec(43, 13), boardHeight, boardWidth,
            gm.getGameBoard(EBoardLocation.RIGHT));
      createPlayerArea(pInflater, pContainer, gridLayout, R.layout.bottom_player_area, 
            GridLayout.spec(43, 13), GridLayout.spec(13, 43), boardWidth, boardHeight,
            gm.getGameBoard(EBoardLocation.BOTTOM));      
      
      //Set the game board graphics
      initGameBoards(gridLayout);
      
      //Set the village tile graphics
      initVillageTiles(gridLayout);
      
      //Setup the player token areas
      initPlayerTokenAreas(gridLayout);         
      
      return gridLayout;
   }
   
   private void createPlayerArea(LayoutInflater pInflater, ViewGroup pContainer, 
         GridLayout pLayout, int pPlayerAreaId, Spec pRowSpec, Spec pColSpec, 
         int pWidth, int pHeight, GameBoardData pData) {
      PlayerAreaView view = 
            (PlayerAreaView)pInflater.inflate(pPlayerAreaId, pContainer, false);
      GridLayout.LayoutParams parms = 
            new GridLayout.LayoutParams(pRowSpec, pColSpec);
      parms.width = pWidth;
      parms.height = pHeight;
      view.setLayoutParams(parms);      
      pLayout.addView(view, parms);
      view.setGameBoardData(pData);
   }
   
   private void createVillageArea(LayoutInflater pInflater, ViewGroup pContainer,
         GridLayout pLayout, int pVillageAreaId, Spec pRowSpec, Spec pColSpec,
         int pWidth, int pHeight) {
      View view = pInflater.inflate(pVillageAreaId, pContainer, false);
      GridLayout.LayoutParams parms = 
            new GridLayout.LayoutParams(pRowSpec, pColSpec);
      parms.width = pWidth;
      parms.height = pHeight;
      view.setLayoutParams(parms);
      pLayout.addView(view, parms);
   }
   
   /**
    * Initialize the game board views
    * @param pView The parent view
    */
   private void initGameBoards(View pView) {      
      Map<EColor, GameBoardData> gameBoards =
            GhostStoriesGameManager.getInstance().getGameBoards();
      for(GameBoardData gb : gameBoards.values()) {
         PlayerBoardView board = 
               (PlayerBoardView)pView.findViewById(gb.getLocation().getBoardId());
         board.setGameBoardData(gb);   
         for(ECardLocation cardLoc : ECardLocation.values()) {
            PlayerBoardCardView view = (PlayerBoardCardView)board.findViewById(
                  cardLoc.getCardLocationId());
            view.setGameBoardData(gb);            
            new PlayerBoardCardController(gb, view, cardLoc);
         }                  
      }     
      new HaunterController();
   }
   
   /**
    * Initialize the player token area views
    * @param pView The parent view
    */
   private void initPlayerTokenAreas(View pView) {
      for(EColor color : EColor.getPlayerColors()) {
         PlayerData pd = 
               GhostStoriesGameManager.getInstance().getPlayerData(color);
         PlayerTokenAreaView view = (PlayerTokenAreaView)pView.findViewById(
               pd.getBoardLocation().getTokenAreaId());
         view.setPlayerData(pd);
      }
   }
   
   /**
    * Initialize the village tile views
    * @param pView The parent view
    */
   private void initVillageTiles(View pView) {
      Map<ETileLocation, VillageTileData> villageTiles = 
            GhostStoriesGameManager.getInstance().getVillageTiles();
      for(VillageTileData tileData : villageTiles.values()) {
         VillageTileView tileView = 
               (VillageTileView)pView.findViewById(tileData.getLocation().getLayoutId());
         tileData.setViewId(tileView.getId());         
         tileView.setVillageTileData(tileData);
         new VillageTileController(tileData, tileView);
      }
      
   }
   
   //private static final int sNumRows = 7;
   //private static final int sNumCols = 7;
   
   private static final int sNumRows = 56;
   private static final int sNumCols = 56;
}
