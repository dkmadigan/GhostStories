package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Defines the different player abilities 
 */
public enum EPlayerAbility {
   
   /*
    * Before his move, the player can place or move the Enfeeblement Mantra 
    * token onto any ghost in play. A ghost under the effect of the Enfeeblement
    * Mantra shows a resistance reduced by 1. When the ghost is removed from the 
    * game, the token is recovered and can be used on the next turn. Note: When
    * fighting against multicolored incarnations, the color of the Mantra may 
    * be chosen after the dice roll. 
    */
   ENFEEBLEMENT_MANTRA(R.drawable.enfeeblement_mantra),
   /*
    * Before his move, the player may take a Tao token of any color among those 
    * available in the supply.
    */
   BOTTOMLESS_POCKETS(R.drawable.bottomless_pockets),
   /*
    * Before his move, the player may move one other player according to the 
    * move rules.
    */
   DANCE_OF_THE_TWIN_WINDS(R.drawable.dance_of_the_twin_winds),
   /*
    * During his move, the player can move to any village tile, not only
    * adjacent ones.
    */
   DANCE_OF_THE_PEAKS(R.drawable.dance_of_the_peaks),
   /*
    * From his current village tile, the player may use the village tile twice
    * OR attempt two exorcisms. These two exorcism are independent, which means
    * that all dice must roll twice.
    */
   SECOND_WIND(R.drawable.second_wind),
   /*
    * The player may use a village tile and attempt an exorcism in the same
    * turn, in any order.
    */
   HEAVENLY_GUST(R.drawable.heavenly_gust),
   /*
    * The player gets to roll a fourth die when performing exorcisms. In 
    * addition he never rolls the Curse die.
    */
   STRENGTH_OF_THE_MOUNTAIN(R.drawable.strength_of_the_mountain),
   /*
    * The player may roll the Tao dice a second time (he may keep some of the 
    * dice and roll the others). He may also roll the Curse die a second time.
    * Only the second result is taken into account. 
    */
   GODS_FAVORITE(R.drawable.gods_favorite);
   
   /**
    * Constructor
    * @param pId The id of the drawable for the ability
    */
   private EPlayerAbility(int pId) {
      mId = pId;
   }
   
   /**
    * @return The id for the drawable
    */
   public int getDrawableId() {
      return mId;
   }
   
   /** The id of the drawable for the ability **/
   private int mId;
}
