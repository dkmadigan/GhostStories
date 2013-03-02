package games.ghoststories.data;

import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.enums.ETileLocation;

/**
 * Data class representing the data for a single player. This data includes: 
 * <li>Name
 * <li>Color
 * <li>Number of Qi
 * <li>Number of Tao Tokens of each color
 * <li>Number of buddha tokens
 * <li>Yin Yang availability
 * <li>Player ability
 * <li>Game Board Data
 * <li>Player location
 */
public class PlayerData extends TokenSupplyData {
   
   /**
    * Constructor
    * @param pName Name of the player
    * @param pColor The color of the player
    * @param pGameBoardData Game board info for the player
    */
   public PlayerData(String pName, EColor pColor, GameBoardData pGameBoardData) {
      mName = pName;
      mColor = pColor;
      mBoardData = pGameBoardData;
      
      //Initialize to zero tao tokens for all colors
      for(EColor color : EColor.values()) {
         mTaoTokens.put(color, 0);
      }
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      return mColor.hashCode();
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object o) { 
      boolean equals = false;
      if(o instanceof PlayerData) {
         equals = ((PlayerData)o).mColor == mColor;
      }
      return equals; 
   }   
   
   /**
    * Adds a buddha token to the player 
    */
   public void addBuddhaToken() {
      mNumBuddhaTokens++;
      notifyListeners();
   }  
   
   /**
    * @return Whether or not the player yin yang is available
    */
   public boolean isYinYangAvailable() {
      return mYinYangAvailable;
   }
   
   /**
    * @return The player ability
    */
   public EPlayerAbility getAbility() {
      return mBoardData.getAbility();
   }
   
   /**
    * @return Whether or not the ability is active
    */
   public boolean getAbilityActive() {
      return mAbilityActive;
   }
   
   /**
    * @return The board player board location
    */
   public EBoardLocation getBoardLocation() {
      return mBoardData.getLocation();
   }
   
   /**
    * @return The color of the player
    */
   public EColor getColor() {
      return mColor;
   }
   
   /**
    * @return The tile location of the player
    */
   public ETileLocation getLocation() {
      return mLocation;
   }
   
   /**
    * @return The name of the player
    */
   public String getName() {
      return mName;
   }
   
   /**
    * @return The number of buddha tokens that the player has
    */
   public int getNumBuddhaTokens() {
      return mNumBuddhaTokens;
   }       
   
   /**
    * Removes a buddha token from the player
    */
   public void removeBuddhaToken() {
      mNumBuddhaTokens--;
      notifyListeners();
   }
   
   /**
    * Sets whether or not the player ability is active
    * @param pAbilityActive <code>true</code> if active, <code>false</code> if
    *        inactive
    */
   public void setAbilityActive(boolean pAbilityActive) {
      mAbilityActive = pAbilityActive;
      notifyListeners();
   }
   
   /**
    * Sets the location of the player
    * @param pLocation The new tile location of the player
    */
   public void setLocation(ETileLocation pLocation) {
      mLocation = pLocation;
      notifyListeners();
   }
   
   /**
    * Sets whether or not the yin yang is available to the player
    * @param pAvailable <code>true</code> for available, <code>false</code> for
    *        unavailable
    */
   public void setYinYangAvailable(boolean pAvailable) {
      mYinYangAvailable = pAvailable;
      notifyListeners();
   } 
   
   /** Whether or not the player ability is active **/
   private boolean mAbilityActive = true;
   /** The game board data for the player **/
   private final GameBoardData mBoardData;
   /** The color of the player **/
   private final EColor mColor;
   /** The tile location of the player **/
   private ETileLocation mLocation = ETileLocation.MIDDLE_CENTER;
   /** The name of the player **/
   private final String mName;
   /** The number of buddha tokens held by the player **/
   private int mNumBuddhaTokens;
   /** Whether or not the yin yang is available **/
   private boolean mYinYangAvailable = true;            
}