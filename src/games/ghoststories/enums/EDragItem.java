package games.ghoststories.enums;

import java.util.EnumSet;

/**
 * Defines the different types of items that can be dragged
 */
public enum EDragItem {
   GHOST_CARD,
   COMBAT_DICE,
   COMBAT_TAO,
   DAMAGE_DICE,
   DAMAGE_TAO;
   
   /** 
    * @return Whether or not this draggable item is related to combat
    */
   public boolean isCombatDragItem() {
      return sCombatItems.contains(this);
   }
   
   /**
    * @return Whether or not this draggable item is related to the combat damage
    *         views
    */
   public boolean isCombatDamageDragItem() {
      return sDamageItems.contains(this);
   }
   
   /** The set of draggable items related to combat **/
   private static final EnumSet<EDragItem> sCombatItems = 
         EnumSet.of(COMBAT_DICE, COMBAT_TAO);
   
   /** The set of draggable items related to combat damage views **/
   private static final EnumSet<EDragItem> sDamageItems =
         EnumSet.of(DAMAGE_DICE, DAMAGE_TAO);
}
