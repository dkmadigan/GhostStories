package games.ghoststories.data;

import games.ghoststories.data.interfaces.ITokenListener;
import games.ghoststories.enums.EColor;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Data model for a token supply. This is used for the main game token supply
 * as well as each player token supply.
 */
public class TokenSupplyData {

   /**
    * Dispose of the data
    */
   public void dispose() {
      mListeners.clear();
   }

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
      notifyListeners();
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
      notifyListeners();
   }

   /**
    * Adds a listener for token supply updates
    * @param pListener The listener to add
    */
   public void addTokenListener(ITokenListener pListener) {
      mListeners.add(pListener);
   }

   /**
    * @return The number of Qi in this supply
    */
   public int getNumQi() {
      return mNumQi;
   }

   /**
    * Gets the number of tao tokens of the specified color
    * @param pColor The color of token to get
    * @return The number of tao tokens of the given color
    */
   public int getNumTaoTokens(EColor pColor) {
      return mTaoTokens.get(pColor);
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
      notifyListeners();
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
         notifyListeners();
      }
   }

   /**
    * Removes a token listener
    * @param pListener The listener to remove
    */
   public void removeTokenListener(ITokenListener pListener) {
      mListeners.remove(pListener);
   }

   /**
    * Sets the number of tao tokens of a particular color
    * @param pColor The color of the token to set
    * @param pNumTokens The number of tokens
    */
   public void setNumTaoTokens(EColor pColor, int pNumTokens) {
      mTaoTokens.put(pColor, pNumTokens);
      notifyListeners();
   }

   /**
    * Sets the number of qi
    * @param pNumQi The number of qi
    */
   public void setNumQi(int pNumQi) {
      mNumQi = pNumQi;
      notifyListeners();
   }

   /**
    * Sets the tao tokens to use for the supply
    * @param pTokenMap The new set of tao tokens to use
    */
   public void setTaoTokens(Map<EColor, Integer> pTokenMap) {
      mTaoTokens.putAll(pTokenMap);
      notifyListeners();
   }

   /**
    * Notify listeners that the token supply was updated
    */
   protected void notifyListeners() {
      for(ITokenListener listener : mListeners) {
         listener.tokenDataUpdated();
      }
   }

   /** Set of token supply listeners **/
   protected Set<ITokenListener> mListeners =
         new CopyOnWriteArraySet<ITokenListener>();
   /** The number of qi in the supply **/
   protected int mNumQi = 0;
   /** The number of tao tokens in the supply **/
   protected final Map<EColor, Integer> mTaoTokens =
         new EnumMap<EColor, Integer>(EColor.class);
}
