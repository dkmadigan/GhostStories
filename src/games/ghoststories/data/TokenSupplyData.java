package games.ghoststories.data;

import games.ghoststories.data.interfaces.ITokenListener;
import games.ghoststories.enums.EColor;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TokenSupplyData {

   /**
    * Adds a Qi to the supply
    */
   public void addQi() {
      addQi(1);
   }
   
   /**
    * Adds the specified number of Qi to the supply
    * @param pNumQi The number of Qi to add to the supply
    */
   public void addQi(int pNumQi) {
      mNumQi += pNumQi;
   }
   
   /**
    * Adds a single Tao Token of the given color to the supply
    * @param pColor The color of token to add 
    */
   public void addTaoToken(EColor pColor) {
      addTaoTokens(pColor, 1);      
   }
   
   /**
    * Adds the specified number of Tao Tokens of the given color to the supply
    * @param pColor The color of token to add
    * @param pNum The number of tokens to add
    */
   public void addTaoTokens(EColor pColor, int pNum) {
      if(!mTaoTokens.containsKey(pColor)) {
         mTaoTokens.put(pColor, pNum);
      } else {
         int numTokens = mTaoTokens.get(pColor);
         mTaoTokens.put(pColor, numTokens + pNum);
      }
   }   
   
   public void addTokenListener(ITokenListener pListener) {    
      mListeners.add(pListener);
   }
   
   public int getNumTaoTokens(EColor pColor) {
      return mTaoTokens.get(pColor);
   }
   
   public int getNumQi() {
      return mNumQi;
   }  
   
   /**
    * Removes a single Qi from the supply
    */
   public void removeQi() {
      removeQi(1);
   }
   
   /**
    * Removes the specified number of Qi from the supply
    * @param pNum The number of Qi to remove
    */
   public void removeQi(int pNum) {
      mNumQi = Math.max(0, mNumQi - pNum);
   }
   
   /**
    * Removes a single Tao Token of the given color from the supply
    * @param pColor The color of the token to remove
    */
   public void removeTaoToken(EColor pColor) {
      removeTaoTokens(pColor, 1);
   }
   
   /**
    * Removes the specified number of Tao Tokens from the supply for the 
    * given color
    * @param pColor The color of token to remove
    * @param pNum The number of tokens to remove
    */
   public void removeTaoTokens(EColor pColor, int pNum) {
      if(mTaoTokens.containsKey(pColor)) {
         int numTokens = mTaoTokens.get(pColor);
         mTaoTokens.put(pColor, Math.max(0, numTokens - pNum));
      }
   }
   
   public void setNumTaoTokens(EColor pColor, int pNumTokens) {
      mTaoTokens.put(pColor, pNumTokens);
      notifyListeners();
   }
   
   public void setNumQi(int pNumQi) {
      mNumQi = pNumQi;
      notifyListeners();
   }
   
   protected void notifyListeners() {
      for(ITokenListener listener : mListeners) {
         listener.tokenDataUpdated();
      }
   }
   
   protected Set<ITokenListener> mListeners = new HashSet<ITokenListener>();
   protected int mNumQi = 0;
   protected final Map<EColor, Integer> mTaoTokens = 
         new EnumMap<EColor, Integer>(EColor.class);
}
