package games.ghoststories.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data representation of the ghost graveyard. The ghost graveyard contains
 * all of the ghosts that have been defeated by the players.
 */
public class GhostGraveyardData {
   
   /**
    * Adds a ghost to the ghost graveyard
    * @param pGhostData The ghost to add
    */
   public void addGhost(GhostData pGhostData) {
      mGhosts.add(pGhostData);
   }
   
   /**
    * @return A list of ghosts in the ghost graveyard
    */
   public List<GhostData> getGhosts() {
      return Collections.unmodifiableList(mGhosts);
   }

   /** The ghosts in the graveyard **/
   private List<GhostData> mGhosts = new ArrayList<GhostData>();
}
