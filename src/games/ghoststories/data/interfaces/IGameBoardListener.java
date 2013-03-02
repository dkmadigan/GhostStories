package games.ghoststories.data.interfaces;

import games.ghoststories.data.GameBoardData;

/**
 * Listener interface triggered when one of the player game boards is updated.
 * @see GameBoardData#addGameBoardListener(IGameBoardListener)
 */
public interface IGameBoardListener {
   /**
    * Called when a player game board is updated.
    */
   public void gameBoardUpdated();
}
