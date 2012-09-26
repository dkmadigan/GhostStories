package games.ghoststories.enums;

/**
 * Defines the different game phases to each player turn.
 */
public enum EGamePhase {
   EYinPhase1A("Yin Phase 1A:\nGhosts' Actions", 
         "-Update haunter positions\n-Haunt tiles"),
         
   EYinPhase1B("Yin Phase 1B:\nGhosts' Actions",
         "-Roll cursed die and apply effects"),
         
   EYinPhase2("Yin Phase 2:\nBoard Overrun",
         "-Check for board overrun\n-Lose 1 Qi if all 3 ghost spaces are occupied"),
         
   EYinPhase3("Yin Phase 3:\nGhost Arrival",
         "-Draw a ghost card by tapping on the ghost deck\n-Drag the ghost card from the deck to a valid space on the board"),
         
   EYangPhase1("Yang Phase 1:\nMove Taoist",
         "-Move taoist by tapping on the tile you wish to move to"),
         
   EYangPhase2("Yang Phase 2:\nTaoist Action",
         "-Request help from villager by tapping on current village tile OR perform exorcism by tapping on an adjacent ghost"),
         
   EYangPhase3("Yang Phase 3:\nPlace Buddha",
         "-Place Buddha by tapping on an adjacent empty ghost space");

   private EGamePhase(String pPhaseName, String pPhaseText) {
      mPhaseName = pPhaseName;
      mPhaseText = pPhaseText;
   }
   
   public String getName() {
      return mPhaseName;
   }
   
   public String getText() {
      return mPhaseText;
   }
   
   private String mPhaseName;
   private String mPhaseText;
}
