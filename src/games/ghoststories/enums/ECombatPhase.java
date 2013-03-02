package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Defines the different phases of combat
 */
public enum ECombatPhase {
   ATTACK(R.string.combat_attack),
   ATTACK_2(R.string.combat_attack_2),
   RESOLUTION(R.string.combat_resolution);
   
   /**
    * Constructor
    * @param pCombatStringId The instruction string for the combat phase
    */
   private ECombatPhase(int pCombatStringId) {
      mCombatStringId = pCombatStringId;          
   }
   
   /**
    * @return The instruction string for the combat phase
    */
   public int getCombatStringId() {
      return mCombatStringId;
   }
   
   /** The instruction string for the combat phase **/
   private final int mCombatStringId;
}
