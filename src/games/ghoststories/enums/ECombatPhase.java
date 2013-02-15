package games.ghoststories.enums;

import games.ghoststories.R;

public enum ECombatPhase {
   ATTACK(R.string.combat_attack),
   ATTACK_2(R.string.combat_attack_2),
   RESOLUTION(R.string.combat_resolution);
   
   private ECombatPhase(int pCombatStringId) {
      mCombatStringId = pCombatStringId;          
   }
   
   public int getCombatStringId() {
      return mCombatStringId;
   }
   
   private int mCombatStringId;
}
