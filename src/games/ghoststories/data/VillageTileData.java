package games.ghoststories.data;

import java.util.HashSet;
import java.util.Set;

import android.graphics.Point;
import android.view.View;
import games.ghoststories.data.interfaces.IGameBoardListener;
import games.ghoststories.data.interfaces.IVillageTileListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.ETileLocation;
import games.ghoststories.enums.EVillageTile;

/**
 * Data class representing a single village tile
 * TODO Probably should make this immutable
 */
public class VillageTileData {
   /**
    * Constructor
    * @param pName The name of the tile
    * @param pTileType The type of the tile
    * @param pActiveImageId The image id to use when active
    * @param pHauntedImageId The image id to use when haunted
    */
   public VillageTileData(String pName, EVillageTile pTileType, 
         int pActiveImageId, int pHauntedImageId) {
      mName = pName;
      mType = pTileType;
      mActiveImageId = pActiveImageId;
      mHauntedImageId = pHauntedImageId;
      mIsActive = true;
   }
   
   /**
    * @param pListener The listener to add
    */
   public void addVillageTileListener(IVillageTileListener pListener) {
      mListeners.add(pListener);
   }
   
   /**
    * @return The active tile image id
    */
   public int getActiveImageId() {
      return mActiveImageId;
   }
   
   /**
    * @return The haunted tile image id
    */
   public int getHauntedImageId() {
      return mHauntedImageId;
   }
   
   /**
    * @return The location of this tile
    */
   public ETileLocation getLocation() {
      return mLocation;
   }
   
   /**
    * @return The name of the tile
    */
   public String getName() {
      return mName;
   }
   
   /**
    * Gets the number of buddhas - only valid for buddhist temple tile
    * @return The number of buddhas
    */
   public int getNumBuddhas() {
      return mType == EVillageTile.BUDDHIST_TEMPLE ? mNumBuddhas : 0;
   }
   
   /**
    * Gets the token color - only valid for circle of prayer tile
    * @return The token color
    */
   public EColor getTokenColor() {
      return mType == EVillageTile.CIRCLE_OF_PRAYER ? mTokenColor : null;
   }
   
   /**
    * @return The type of the tile
    */
   public EVillageTile getType() {
      return mType;
   }
   
   /**
    * @return The village tile view id
    */
   public int getViewId() {
      return mViewId;
   }
   
   /**
    * @return Whether or not this village tile is active
    */
   public boolean isActive() {
      return mIsActive;
   }
   
   /**
    * @param pListener The listener to remove
    */
   public void removeVillageTileListener(IVillageTileListener pListener) {
      mListeners.remove(pListener);
   }
   
   /**
    * Sets whether or not this village tile is active
    * @param pActive <code>true</code> for active, <code>false</code> for haunted
    */
   public void setIsActive(boolean pActive) {
      mIsActive = pActive;
      notifyListeners();
   }
   
   /**
    * @param pLocation The location of the tile
    */
   public void setLocation(ETileLocation pLocation) {
      mLocation = pLocation;
   }
   
   /**
    * Sets the number of buddhas on this tile - only valid for the buddhist 
    * temple tile.
    * @param pNumBuddhas The number of buddhas on the tile.
    */
   public void setNumBuddhas(int pNumBuddhas) {
      mNumBuddhas = pNumBuddhas;
      notifyListeners();
   }
   
   /**
    * Sets the token color for the circle of prayer village tile.
    * @param pTokenColor The color of the token
    */
   public void setTokenColor(EColor pTokenColor) {
      mTokenColor = pTokenColor;
      notifyListeners();
   }
   
   /**
    * @param pId The village tile view id
    */
   public void setViewId(int pId) {
      mViewId = pId;
   }
   
   /**
    * Notifies listeners that the data has changed
    */
   private void notifyListeners() {
      for(IVillageTileListener listener : mListeners) {
         listener.villageTileUpdated();
      }
   }
      
   /** The active tile image id **/
   private final int mActiveImageId;
   /** The haunted tile image id **/
   private final int mHauntedImageId;
   /** Whether of not this village tile is active **/
   private boolean mIsActive;
   /** The set of listeners for ghost deck updates **/
   private Set<IVillageTileListener> mListeners = 
         new HashSet<IVillageTileListener>();
   /** The tile location **/
   private ETileLocation mLocation;
   /** The name of this village tile **/
   private final String mName;
   /** The number of buddhas - used for buddhist temple tile **/
   private int mNumBuddhas = 0;
   /** The token - used for circle of prayer tile **/
   private EColor mTokenColor = null;
   /** The type of village tile **/
   private final EVillageTile mType;
   /** The village tile view id **/
   private int mViewId = View.NO_ID;
}
