package games.ghoststories.data.village;
import games.ghoststories.data.interfaces.IVillageTileListener;
import games.ghoststories.enums.ETileLocation;
import games.ghoststories.enums.EVillageTile;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import android.view.View;

/**
 * Data class representing a single village tile.
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
    * Dispose of the village tile
    */
   public void dipose() {
      mListeners.clear();
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
    * @param pId The village tile view id
    */
   public void setViewId(int pId) {
      mViewId = pId;
   }

   /**
    * Notifies listeners that the data has changed
    */
   protected void notifyListeners() {
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
   private final Set<IVillageTileListener> mListeners =
         new CopyOnWriteArraySet<IVillageTileListener>();
   /** The tile location **/
   private ETileLocation mLocation;
   /** The name of this village tile **/
   private final String mName;
   /** The type of village tile **/
   private final EVillageTile mType;
   /** The village tile view id **/
   private int mViewId = View.NO_ID;
}
