package games.ghoststories.data.interfaces;

import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EColor;

/**
 * Listener interface triggered when the number of tao or qi tokens in the stash
 * has been updated. 
 * @see GhostStoriesGameManager#addGameTokenListener(IGameTokenListener)
 */
public interface IGameTokenListener {
   
   /**
    * Called when the number of qi tokens is updated
    * @param pNumTokens The new number of qi tokens
    */
   public void qiTokensUpdated(int pNumTokens);
   
   /**
    * Called when the number of tao tokens is updated
    * @param pColor The color of the updated token
    * @param pNumTokens The new number of tokens
    */
   public void taoTokensUpdated(EColor pColor, int pNumTokens);   
}
