package games.ghoststories.enums;

import java.util.EnumSet;

public enum EDragItem {
   GHOST_CARD,
   COMBAT_DICE,
   COMBAT_TAO;
   
   public boolean isCombatDragItem() {
      return sCombatItems.contains(this);
   }
   
   private static final EnumSet<EDragItem> sCombatItems = 
         EnumSet.of(COMBAT_DICE, COMBAT_TAO);
}
