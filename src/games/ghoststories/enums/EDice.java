package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Defines the different dice available during combat
 */
public enum EDice {
   DICE_1(R.id.dice_1),
   DICE_2(R.id.dice_2),
   DICE_3(R.id.dice_3),
   EXTRA_DICE(R.id.extra_dice);
   
   /**
    * Constructor
    * @param pViewId The view id for the dice
    */
   private EDice(int pViewId) {
      mViewId = pViewId;
   }   
   
   /**
    * @return The view id for the dice
    */
   public int getViewId() {
      return mViewId;
   }
   
   /** The view id for the dice **/
   private final int mViewId;
}
