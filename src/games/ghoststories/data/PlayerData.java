package games.ghoststories.data;

import games.ghoststories.data.interfaces.ITokenListener;
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
   public PlayerData(String pName, EColor pColor, GameBoardData pGameBoardData) {
      mName = pName;
      mColor = pColor;
      mBoardData = pGameBoardData;
      
      //Initialize to zero tao tokens for all colors
      for(EColor color : EColor.values()) {
         mTaoTokens.put(color, 0);
      }
   }
   
   @Override
   public int hashCode() {
      return mColor.hashCode();
   }
   
   @Override
   public boolean equals(Object o) { 
      boolean equals = false;
      if(o instanceof PlayerData) {
         equals = ((PlayerData)o).mColor == mColor;
      }
      return equals; 
   }   
   
   public boolean isYinYangAvailable() {
      return mYinYangAvailable;
   }
   
   public EPlayerAbility getAbility() {
      return mBoardData.getAbility();
   }
   
   public boolean getAbilityActive() {
      return mAbilityActive;
   }
   
   public EBoardLocation getBoardLocation() {
      return mBoardData.getLocation();
   }
   
   public EColor getColor() {
      return mColor;
   }
   
   public ETileLocation getLocation() {
      return mLocation;
   }
   
   public String getName() {
      return mName;
   }
   
   public int getNumBuddhaTokens() {
      return mNumBuddhaTokens;
   }
        
   public void removePlayerDataListener(ITokenListener pListener) {
      mListeners.remove(pListener);
   }
   
   public void setAbilityActive(boolean pAbilityActive) {
      mAbilityActive = pAbilityActive;
      notifyListeners();
   }
   
   public void setLocation(ETileLocation pLocation) {
      mLocation = pLocation;
      notifyListeners();
   }
   
   public void setNumBuddhaTokens(int pNumBuddhaTokens) {
      mNumBuddhaTokens = pNumBuddhaTokens;
      notifyListeners();
   }      
   
   public void setYinYangAvailable(boolean pAvailable) {
      mYinYangAvailable = pAvailable;
      notifyListeners();
   } 
   
   private final String mName;
   private final EColor mColor;      
   private boolean mYinYangAvailable = true;
   private ETileLocation mLocation = ETileLocation.MIDDLE_CENTER;
   private final GameBoardData mBoardData;
   private int mNumBuddhaTokens;
   private boolean mAbilityActive = true;
}