package games.ghoststories.enums;

import java.util.EnumSet;

/**
 * Defines the different types of items that can be dragged
 */
public enum EDragItem {
   GHOST_CARD,
   COMBAT_DICE,
   COMBAT_TAO;
   
   /** 
    * @return Whether or not this draggable item is related to combat
    */
   public boolean isCombatDragItem() {
      return sCombatItems.contains(this);
   }
   
   /** The set of draggable items related to combat **/
   private static final EnumSet<EDragItem> sCombatItems = 
         EnumSet.of(COMBAT_DICE, COMBAT_TAO);
}
