package games.ghoststories.data.interfaces;

import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EGamePhase;

/**
 * Listener interface triggered when the game phase is updated.
 * @see GhostStoriesGameManager#addGamePhaseListener(IGamePhaseListener)
 */
public interface IGamePhaseListener {
   /**
    * Called when the game phase is updated
    * @param pGamePhase The new game phase
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase);
}
