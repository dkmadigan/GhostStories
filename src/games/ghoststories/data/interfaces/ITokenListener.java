package games.ghoststories.data.interfaces;

import games.ghoststories.data.TokenSupplyData;

/**
 * Interface to listen for individual token updates.
 * @see TokenSupplyData#addTokenListener(ITokenListener)
 */
public interface ITokenListener {
   
   /**
    * Called when the token data has been updated 
    */
   public void tokenDataUpdated();
}
