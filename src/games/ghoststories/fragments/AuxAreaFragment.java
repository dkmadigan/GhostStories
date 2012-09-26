package games.ghoststories.fragments;

import games.ghoststories.R;
import games.ghoststories.controllers.GhostDeckController;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.views.GhostDeckView;
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
      
      GhostDeckView ghostDeckView = 
            (GhostDeckView)auxAreaView.findViewById(R.id.ghost_deck);
      GhostDeckData ghostDeckData = 
            GhostStoriesGameManager.getInstance().getGhostDeckData();      
      ghostDeckView.setGhostDeckData(ghostDeckData);
      new GhostDeckController(ghostDeckData, ghostDeckView);

      return auxAreaView;
   }
}
