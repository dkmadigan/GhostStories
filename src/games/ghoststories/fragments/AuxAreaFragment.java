package games.ghoststories.fragments;

import games.ghoststories.R;
import games.ghoststories.controllers.GhostDeckController;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.views.aux_area.GhostDeckView;
import games.ghoststories.views.aux_area.PlayerInfoView;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AuxAreaFragment extends Fragment {
   @Override
   public View onCreateView(LayoutInflater pInflater, ViewGroup pContainer,
         Bundle pSavedInstanceState) {      
      View auxAreaView = pInflater.inflate(R.layout.aux_area, pContainer, false);
      
      GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
      
      //Setup the ghost deck
      GhostDeckView ghostDeckView = 
            (GhostDeckView)auxAreaView.findViewById(R.id.ghost_deck);
      GhostDeckData ghostDeckData = gm.getGhostDeckData();      
      ghostDeckView.setGhostDeckData(ghostDeckData);
      new GhostDeckController(ghostDeckData, ghostDeckView);                 
      
      //Setup the player info area
      View playerInfoArea = auxAreaView.findViewById(R.id.player_info_area);
      List<EColor> playerOrder = gm.getPlayerOrder();
      for(int i = 0; i < playerOrder.size(); ++i) {
         setupPlayerInfoView(playerInfoArea, getPlayerInfoId(i), 
               gm.getPlayerData(playerOrder.get(i)));
      }
      
      return auxAreaView;
   }        
   
   private int getPlayerInfoId(int pPlayer) {
      int id = R.id.info_area_1;
      switch(pPlayer) {
      case 0:
         id = R.id.info_area_1;
         break;
      case 1:
         id = R.id.info_area_2;
         break;
      case 2:
         id = R.id.info_area_3;
         break;
      case 3:
         id = R.id.info_area_4;
         break;
      }
      return id;
   }
   
   private void setupPlayerInfoView(View pParent, int pId, PlayerData pData) {
      PlayerInfoView infoArea = 
            (PlayerInfoView)pParent.findViewById(pId);
      infoArea.setPlayerData(pData);
   }
}
