package games.ghoststories.enums;

/**
 * Defines the different game phases to each player turn
 */
//TODO Use strings.xml
public enum EGamePhase {
   TurnStart("", ""),

   YinPhase1A("Yin Phase 1A: Ghosts' Actions",
         "\u2694Update haunter positions<br />\u2694Haunt tiles"),

   YinPhase1B("Yin Phase 1B: Ghosts' Actions",
         "\u2694Roll cursed die and apply effects"),

   YinPhase2("Yin Phase 2: Board Overrun",
         "\u2694Check for board overrun<br />\u2694Lose 1 Qi if all 3 ghost spaces are occupied"),

   YinPhase3("Yin Phase 3: Ghost Arrival",
         "\u2694Draw a ghost card by tapping on the ghost deck<br />\u2694Drag the ghost card from the deck to the board"),

   YangPhase1("Yang Phase 1: Move Taoist",
         "\u2694Move taoist by tapping on the tile you wish to move to"),

   YangPhase2("Yang Phase 2: Taoist Action",
         "\u2694Request help from villager by tapping on current village tile OR perform exorcism by tapping on an adjacent ghost"),

   TaoistResolution("", ""),

   YangPhase3("Yang Phase 3: Place Buddha",
         "\u2694Place Buddha by tapping on an adjacent empty ghost space");

   /**
    * Constructor
    * @param pPhaseName The name of the phase
    * @param pPhaseText The instructional text for the phase
    */
   private EGamePhase(String pPhaseName, String pPhaseText) {
      mPhaseName = pPhaseName;
      mPhaseText = pPhaseText;
   }

   /**
    * @return The name of the phase
    */
   public String getName() {
      return mPhaseName;
   }

   /**
    * @return The instructional text for the phase
    */
   public String getText() {
      return mPhaseText;
   }

   /** The name of the phase **/
   private String mPhaseName;
   /** The instructional text for the phase **/
   private String mPhaseText;
}
