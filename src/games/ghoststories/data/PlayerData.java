package games.ghoststories.data;

import java.util.EnumMap;
import java.util.Map;

import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.enums.ETileLocation;

/**
 * Data class representing the data for a single player. This data includes: 
 * <li>Name
 * <li>Color
 * <li>Number of Qi
 * <li>Number of Tau Tokens of each color
 * <li>Number of buddha tokens
 * <li>Yin Yang availability
 * <li>Player ability
 * <li>Game Board Data
 * <li>Player location
 */
public class PlayerData {
   public PlayerData(String pName, EColor pColor, GameBoardData pGameBoardData) {
      mName = pName;
      mColor = pColor;
      mBoardData = pGameBoardData;
      
      //Initialize to zero tau tokens for all colors
      for(EColor color : EColor.values()) {
         mTauTokens.put(color, 0);
      }
   }
   
   public boolean isYinYangAvailable() {
      return mYinYangAvailable;
   }
   
   public EPlayerAbility getAbility() {
      return mBoardData.getAbility();
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
   
   public int getNumTauTokens(EColor pColor) {
      return mTauTokens.get(pColor);
   }
   
   public int getQi() {
      return mQi;
   }
   
   public void setLocation(ETileLocation pLocation) {
      mLocation = pLocation;
   }
   
   public void setNumBuddhaTokens(int pNumBuddhaTokens) {
      mNumBuddhaTokens = pNumBuddhaTokens;
   }
   
   public void setNumTauTokens(EColor pColor, int pNumTokens) {
      mTauTokens.put(pColor, pNumTokens);
   }
   
   public void setQi(int pQi) {
      mQi = pQi;
   }
   
   public void setYinYangAvailable(boolean pAvailable) {
      mYinYangAvailable = pAvailable;
   }
   
   private final String mName;
   private final EColor mColor;
   private int mQi = 0;
   private final Map<EColor, Integer> mTauTokens = 
         new EnumMap<EColor, Integer>(EColor.class);
   private boolean mYinYangAvailable = true;
   private ETileLocation mLocation = ETileLocation.MIDDLE_CENTER;
   private final GameBoardData mBoardData;
   private int mNumBuddhaTokens;
}