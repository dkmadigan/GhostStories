package games.ghoststories.data;

import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGhostAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Data class representing a single ghost card. 
 */
public class GhostData {
   
   /**
    * Constructor
    * @param pName The name of the ghost
    * @param pId The card id of the ghost card
    * @param pColor The color of the card
    * @param pImageId The id of the image to use for the ghost
    * @param pResistance Mapping from color to resistance level for the ghost
    * @param pEnterAbilities The abilities of the ghost upon entry
    * @param pTurnAbilities The abilities of the ghost executed every turn
    * @param pExorciseAbilities The abilities of the ghost upon exorcism
    */
   public GhostData(String pName, int pId, EColor pColor, int pImageId,
         Map<EColor, Integer> pResistance, 
         List<EGhostAbility> pEnterAbilities,
         List<EGhostAbility> pTurnAbilities, 
         List<EGhostAbility> pExorciseAbilities) {
      mName = pName;
      mId = pId;
      mColor = pColor;
      mImageId = pImageId;
      mResistance = new HashMap<EColor, Integer>(pResistance);
      mEnterAbilities = new ArrayList<EGhostAbility>(pEnterAbilities);
      mTurnAbilities =  new ArrayList<EGhostAbility>(pTurnAbilities);
      mExorciseAbilities =  new ArrayList<EGhostAbility>(pExorciseAbilities);            
   }   
        
   /**
    * @return Color of the ghost
    */
   public EColor getColor() {
      return mColor;
   }

   /**
    * @return The enter abilities of the ghost
    */
   public List<EGhostAbility> getEnterAbilities() {
      return mEnterAbilities;
   }

   /**
    * @return The exorcise abilities of the ghost
    */
   public List<EGhostAbility> getExorciseAbilities() {
      return mExorciseAbilities;
   }

   /**
    * @return The id of the ghost
    */
   public int getId() {
      return mId;
   }

   /**
    * @return The id of the image file for this ghost
    */
   public int getImageId() {
      return mImageId;
   }

   /**
    * @return The name of the ghost
    */
   public String getName() {
      return mName;
   }

   /**
    * @return The resistance map for this ghost
    */
   public Map<EColor, Integer> getResistance() {
      return mResistance;
   }

   /**
    * @return The turn abilities for this ghost
    */
   public List<EGhostAbility> getTurnAbilities() {
      return mTurnAbilities;
   }
   
   /**
    * @return Whether or not this card is flipped over
    */
   public boolean isFlipped() {
      return mIsFlipped;
   }
   
   /**
    * Sets whether or not this card is flipped over
    * @param pFlipped 
    */
   public void setIsFlipped(boolean pFlipped) {
      mIsFlipped = pFlipped;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {      
      StringBuilder sb = new StringBuilder("---------------------------------\n");
      sb.append("id = ").append(mId).append("\n");
      sb.append("name = ").append(mName).append("\n");
      sb.append("color = ").append(mColor).append("\n");
      sb.append("image = ").append(mImageId).append("\n");
      sb.append("resistance = ").append(mResistance).append("\n");
      sb.append("enter abilities = ").append(mEnterAbilities).append("\n");
      sb.append("turn abilities = ").append(mTurnAbilities).append("\n");
      sb.append("exorcise abilities = ").append(mExorciseAbilities).append("\n");
      sb.append("---------------------------------").append("\n");
      return sb.toString();
   }

   /** The color of the ghost card **/
   private final EColor mColor;
   /** The abilities of the ghost upon entry **/
   private final List<EGhostAbility> mEnterAbilities;
   /** The abilities of the ghost upon exorcism **/
   private final List<EGhostAbility> mExorciseAbilities;
   /** The card id of the ghost **/
   private final int mId;
   /** The id of the image file for the ghost card **/
   private final int mImageId;
   /** Whether or not this ghost card has been flipped over **/
   private boolean mIsFlipped = false;
   /** The name of the ghost **/
   private final String mName;
   /** Mapping of the resistance of this ghost **/
   private final Map<EColor, Integer> mResistance;
   /** The abilities of the ghost that occurs every turn **/
   private final List<EGhostAbility> mTurnAbilities;
}
