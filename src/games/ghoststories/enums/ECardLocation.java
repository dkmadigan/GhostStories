package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Represents the possible locations of the cards within a player board
 */
public enum ECardLocation {
   LEFT(R.id.left_space),
   MIDDLE(R.id.middle_space),
   RIGHT(R.id.right_space);
   
   /**
    * Constructor
    * @param pCardLocationId The layout id for the card location
    */
   private ECardLocation(int pCardLocationId) {
      mCardLocationId = pCardLocationId;
   }
   
   /**
    * @return The layout id for the card location
    */
   public int getCardLocationId() {
      return mCardLocationId;
   }
   
   /** The layout id for the card location **/
   private final int mCardLocationId;
}
