package games.ghoststories.controllers;

import games.ghoststories.R;
import games.ghoststories.data.DragData;
import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.village.CircleOfPrayerTileData;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.enums.EGhostAbility;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.enums.EVillageTile;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.combat.CombatView;
import games.ghoststories.views.gameboard.PlayerBoardCardView;

import java.util.List;

import android.app.Activity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;

/**
 * Controller class that handles user interactions with the player game boards.
 * This includes dragging and dropping of ghosts onto valid game boards and
 * initiating combat by clicking on the ghosts. Each space on a player game
 * board is represented by a single {@link PlayerBoardCardController}.
 */
public class PlayerBoardCardController implements OnDragListener, OnClickListener {
   
   /**
    * Constructor
    * @param pGameBoardData The data for game board
    * @param pView The view for the game board
    * @param pCardLocation The location of the game board
    */
   public PlayerBoardCardController(GameBoardData pGameBoardData, 
         PlayerBoardCardView pView, ECardLocation pCardLocation) {
      mGameBoardData = pGameBoardData;
      mView = pView;
      mCardLocation = pCardLocation;
      mView.setOnDragListener(this);
      mView.setOnClickListener(this);
   }

   /*
    * (non-Javadoc)
    * @see android.view.View.OnClickListener#onClick(android.view.View)
    */
   public void onClick(View pView) {   
      //Only allow clicking on the ghost during the attack phase if there
      //is a ghost on this space and it is attackable
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();   
      PlayerData primaryPlayer = gm.getCurrentPlayerData();
      GhostData ghostData = mGameBoardData.getGhostData(mCardLocation);
      if(gm.getCurrentGamePhase() == EGamePhase.YangPhase2 && 
            ghostData != null &&
            GameUtils.isGhostAttackable(mGameBoardData.getLocation(), 
                  mCardLocation, primaryPlayer.getLocation())) {
         //Inflate the combat view
         Activity activity = (Activity)mView.getContext();
         LayoutInflater li = activity.getLayoutInflater();         
  
         CombatView view = (CombatView)li.inflate(R.layout.combat, null);

         //Find the other players who are on this tile
         List<PlayerData> secondaryPlayers = 
               gm.getPlayersOnTile(primaryPlayer.getLocation());
         secondaryPlayers.remove(primaryPlayer);

         //Find the adjacent ghost if there is one
         List<GhostData> ghosts = 
               GameUtils.getAdjacentGhosts(primaryPlayer.getLocation());
         ghosts.remove(ghostData);
         GhostData ghost2 = ghosts.size() > 0 ? ghosts.get(0) : null;

         //Set the combat data on the view
         view.setCombatData(primaryPlayer, secondaryPlayers, ghostData, ghost2);         

         activity.addContentView(view, new ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));         
      }
   }

   /*
    * (non-Javadoc)
    * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
    */
   public boolean onDrag(View pView, DragEvent pEvent) {
      boolean handle = false;
      Object data = pEvent.getLocalState();
      if(data instanceof DragData) {
         DragData dragData = (DragData)data;
         if(dragData.getDragItem() == EDragItem.GHOST_CARD) {
            switch(pEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
               handle = true;
               break;
            case DragEvent.ACTION_DROP:
               GhostData ghostData = (GhostData)dragData.getData();

               //Only allow dropping on this space if valid
               if(GameUtils.isSpaceValid(ghostData, mGameBoardData, mCardLocation)) {
                  mGameBoardData.addGhost(ghostData, mCardLocation);
                  handle = true;
                  handleEnterAbility(ghostData);               
               }                               
               break;
            }
         }
      }
      return handle;
   }

   /**
    * Perform the ghosts enter ability when dropped on the game board
    * @param pData The ghost to perform the enter ability for
    */
   private void handleEnterAbility(GhostData pData) {
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      boolean advanceGamePhase = true;
      for(EGhostAbility ability : pData.getEnterAbilities()) {
         switch(ability) {
         case ALL_LOSE_ABILITY: //TODO Handle this
            break;
         case ALL_LOSE_TAO: //TODO Handle this
            break;
         case CLEAR_CIRCLE_OF_PRAYER:
            CircleOfPrayerTileData tileData = 
            (CircleOfPrayerTileData)gm.getVillageTile(EVillageTile.CIRCLE_OF_PRAYER);
            tileData.setTokenColor(null);
            break;
         case HAUNT_BOARD:
            pData.setHaunterLocation(EHaunterLocation.BOARD, null);
            break;
         case HAUNT_TILE:
            GameUtils.hauntVillageTile(mGameBoardData.getLocation(), 
                  mCardLocation);
            break;
         case LOSE_ABILITY:
            gm.getPlayerData(mGameBoardData.getColor()).setAbilityActive(false);
            break;
         case LOSE_DIE:
            gm.removeDice();
            break;
         case SUMMON_GHOST:
            //TODO Probably need to make it obvious you need to redraw a ghost
            advanceGamePhase = false;
            break;
         default:
            break;
         }
      }
      if(advanceGamePhase) {
         gm.advanceGamePhase();
      }         
   }

   /** The location of the space this controller handles **/
   private final ECardLocation mCardLocation;
   /** The data for the game board **/
   private final GameBoardData mGameBoardData;
   /** The view for the game board **/
   private final PlayerBoardCardView mView;   
}
