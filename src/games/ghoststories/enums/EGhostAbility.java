package games.ghoststories.enums;

/**
 * Defines the different ghost and incarnation abilities
 */
public enum EGhostAbility {
   /*
    * No ghost ability
    */
   NONE,
   /*
    * All players lose their abilities
    */
   ALL_LOSE_ABILITY,
   /*
    * All players lose one tao token, if they have any
    */
   ALL_LOSE_TAO,
   /*
    * Discard the Tao token on the "Circle of Prayer" village tile
    */
   CLEAR_CIRCLE_OF_PRAYER,   
   /*
    * Dice cannot be used to exorcise this ghost
    */
   DICE_IMMUNITY,
   /* 
    * Incarnation Ability - Incarnation can only be exorcised if the tile 
    * opposite the incarnation is not occupied by a ghost.
    */
   EMPTY_OPPOSITE_TILE,
   /*
    * Player receives one tao token of their choice
    */
   GAIN_TAO,
   /*
    * Player receives two tao tokens of their choice
    */
   GAIN_TWO_TAO,
   /*
    * Adds a haunter to the card
    */
   HAUNT,
   /*
    * Adds a haunter immediately to the board instead of to the card 
    */
   HAUNT_BOARD,
   /*
    * Immediately haunts the first active village tile in front of this ghost
    */
   HAUNT_TILE,
   /*
    * The white side of the Tao dice no longer counts for any color
    */
   IGNORE_WHITE_DICE,
   /*
    * The power of the board where the ghost has been placed cannot be used
    */
   LOSE_ABILITY,
   /*
    * Ghost holds one die captive so players have one less die to perform exorcisms
    */
   LOSE_DIE,
   /*
    * Player loses one Qi
    */
   LOSE_QI,
   /*
    * Players cannot use their tao tokens while this ghost is not defeated
    */
   NO_TAO,
   /*
    * Incarnation reward. One Qi and 1 Yin-Yang token is returned to the group.
    * Players can decide whom they will assign these rewards.
    */
   QI_AND_YINYANG,
   /*
    * Player receives one Qi OR their Yin-Yang token back
    */
   QI_OR_YINYANG,
   /*
    * Incarnation Ability - Incarnation must be placed on a tile occupied by a 
    * Buddha before it can be exorcised.
    */
   REQUIRE_BUDDHA,
   /*
    * The player who's tile this ghost is on must roll the curse die
    */
   ROLL_CURSE_DIE,
   /*
    * The player must draw another ghost and put it into play
    */
   SUMMON_GHOST
}
