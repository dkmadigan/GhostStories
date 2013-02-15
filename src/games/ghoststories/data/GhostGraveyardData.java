package games.ghoststories.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GhostGraveyardData {
   
   public void addGhost(GhostData pGhostData) {
      mGhosts.add(pGhostData);
   }
   
   public List<GhostData> getGhosts() {
      return Collections.unmodifiableList(mGhosts);
   }

   private List<GhostData> mGhosts = new ArrayList<GhostData>();
}
