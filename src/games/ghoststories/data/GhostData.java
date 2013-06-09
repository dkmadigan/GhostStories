package games.ghoststories.data;

import games.ghoststories.data.interfaces.IGhostListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGhostAbility;
import games.ghoststories.enums.EHaunterLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


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
    * @param pIsWuFeng Whether or not this ghost is a WuFeng incarnation
    */
   public GhostData(String pName, int pId, EColor pColor, int pImageId,
         Map<EColor, Integer> pResistance,
         List<EGhostAbility> pEnterAbilities,
         List<EGhostAbility> pTurnAbilities,
         List<EGhostAbility> pExorciseAbilities,
         boolean pIsWuFeng) {
      mName = pName;
      mId = pId;
      mColor = pColor;
      mImageId = pImageId;
      mResistance = new HashMap<EColor, Integer>(pResistance);
      mFullHealth = new HashMap<EColor, Integer>(pResistance);
      mEnterAbilities = new ArrayList<EGhostAbility>(pEnterAbilities);
      mTurnAbilities =  new ArrayList<EGhostAbility>(pTurnAbilities);
      mExorciseAbilities =  new ArrayList<EGhostAbility>(pExorciseAbilities);
      mIsWuFeng = pIsWuFeng;
      if(pTurnAbilities.contains(EGhostAbility.HAUNT)) {
         mHaunterLocation = EHaunterLocation.CARD;
      } else if(pTurnAbilities.contains(EGhostAbility.HAUNT_BOARD)) {
         mHaunterLocation = EHaunterLocation.BOARD;
      } else if(pTurnAbilities.contains(EGhostAbility.HAUNT_TILE)) {
         mHaunterLocation = EHaunterLocation.TILE;
      }
      updateHealth();
   }

   /**
    * Dispose of this ghost model
    */
   public void dispose() {
      mGhostListeners.clear();
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      return mId;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object o) {
      boolean equals = false;
      if(o instanceof GhostData) {
         equals = ((GhostData)o).mId == mId;
      }
      return equals;
   }

   /**
    * Adds a listener for updates to this ghost data
    * @param pListener The listener to add
    */
   public void addGhostListener(IGhostListener pListener) {
      mGhostListeners.add(pListener);
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
    * @return The haunter location for the ghost
    */
   public EHaunterLocation getHaunterLocation() {
      return mHaunterLocation;
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
    * @return The total amount of health this ghost has left
    */
   public int getHealth() {
      return mHealth;
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
    * @return true if this ghost has no more health, false otherwise
    */
   public boolean isDead() {
      return mIsDead;
   }

   /**
    * @return Whether or not this card is being dragged
    */
   public boolean isDragging() {
      return mIsDragging;
   }

   /**
    * @return Whether or not this card is flipped over
    */
   public boolean isFlipped() {
      return mIsFlipped;
   }

   /**
    * @return Whether or not this is a wu feng incarnation
    */
   public boolean isWuFeng() {
      return mIsWuFeng;
   }

   /**
    * Sets the status of this ghost to killed and notifies listeners
    */
   public void killGhost() {
      mIsDead = true;
      for(IGhostListener listener : mGhostListeners) {
         listener.ghostKilled();
      }
   }

   /**
    * Removes a listener for updates to this ghost data
    * @param pListener The listener to remove
    */
   public void removeGhostListener(IGhostListener pListener) {
      mGhostListeners.remove(pListener);
   }

   /**
    * Reset the resistance of this ghost to full health
    */
   public void resetResistance() {
      mResistance = new HashMap<EColor, Integer>(mFullHealth);
   }

   /**
    * Sets the haunter location for this ghost.
    * @param pHaunterLocation
    * @param pRunnable
    */
   public void setHaunterLocation(EHaunterLocation pHaunterLocation,
         Runnable pRunnable) {
      EHaunterLocation oldHaunterLocation = mHaunterLocation;
      mHaunterLocation = pHaunterLocation;
      for(IGhostListener listener : mGhostListeners) {
         listener.haunterUpdated(oldHaunterLocation, mHaunterLocation, pRunnable);
      }
   }

   /**
    * Sets whether or not this card is being dragged
    * @param pDragging
    */
   public void setIsDragging(boolean pDragging) {
      mIsDragging = pDragging;
   }

   /**
    * Sets whether or not this card is flipped over
    * @param pFlipped
    */
   public void setIsFlipped(boolean pFlipped) {
      mIsFlipped = pFlipped;
   }

   /**
    * Increments or decrements the resistance value for a given color by the
    * specified amount
    * @param pColor The color of the resistance to update
    * @param pIncOrDec The increment/decrement value
    */
   public void updateResistance(EColor pColor, int pIncOrDec) {
      Integer resistance = mResistance.get(pColor);
      if(resistance != null) {
         resistance = Math.max(0, resistance + pIncOrDec);
         mResistance.put(pColor, resistance);
         updateHealth();
         for(IGhostListener listener : mGhostListeners) {
            listener.resistanceUpdated();
         }
      }
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

   /**
    * Compute and set the health for this ghost. The health is the total amount
    * of resistance across all colors.
    */
   private void updateHealth() {
      mHealth = 0;
      for(Integer health : mResistance.values()) {
         mHealth += health;
      }
   }

   /** The color of the ghost card **/
   private final EColor mColor;
   /** Listeners for updates to this ghost data**/
   private final Set<IGhostListener> mGhostListeners =
         new CopyOnWriteArraySet<IGhostListener>();
   /** The location of the haunter for this ghost **/
   private EHaunterLocation mHaunterLocation = EHaunterLocation.NONE;
   /** The card id of the ghost **/
   private final int mId;
   /** The id of the image file for the ghost card **/
   private final int mImageId;
   /** Whether or not this ghost is dead **/
   private boolean mIsDead = false;
   /** Whether or not this ghost card is being dragged **/
   private boolean mIsDragging = false;
   /** Whether or not this ghost card has been flipped over **/
   private boolean mIsFlipped = false;
   /** Whether or not this ghost is a wu feng incarnation **/
   private final boolean mIsWuFeng;
   /** The name of the ghost **/
   private final String mName;

   /** The abilities of the ghost upon entry **/
   private final List<EGhostAbility> mEnterAbilities;
   /** The abilities of the ghost upon exorcism **/
   private final List<EGhostAbility> mExorciseAbilities;
   /** The abilities of the ghost that occurs every turn **/
   private final List<EGhostAbility> mTurnAbilities;

   /** Current resistance of this ghost **/
   private Map<EColor, Integer> mResistance;
   /** The total resistance count **/
   private int mHealth = 0;
   /** Full health resistance for this ghost **/
   private final Map<EColor, Integer> mFullHealth;
}
