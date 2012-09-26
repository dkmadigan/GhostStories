package games.ghoststories.enums;

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
   ENFEEBLEMENT_MANTRA,
   /*
    * Before his move, the player may take a Tao token of any color among those 
    * available in the supply.
    */
   BOTTOMLESS_POCKETS,
   /*
    * Before his move, the player may move one other player according to the 
    * move rules.
    */
   DANCE_OF_THE_TWIN_WINDS,
   /*
    * During his move, the player can move to any village tile, not only
    * adjacent ones.
    */
   DANCE_OF_THE_PEAKS,
   /*
    * From his current village tile, the player may use the village tile twice
    * OR attempt two exorcisms. These two exorcism are independent, which means
    * that all dice must roll twice.
    */
   SECOND_WIND,
   /*
    * The player may use a village tile and attempt an exorcism in the same
    * turn, in any order.
    */
   HEAVENLY_GUST,
   /*
    * The player gets to roll a fourth die when performing exorcisms. In 
    * addition he never rolls the Curse die.
    */
   STRENGTH_OF_THE_MOUNTAIN,
   /*
    * The player may roll the Tao dice a second time (he may keep some of the 
    * dice and roll the others). He may also roll the Curse die a second time.
    * Only the second result is taken into account. 
    */
   GODS_FAVORITE;
}
