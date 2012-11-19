package games.ghoststories.data;

import android.view.View;
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
    * Sets whether or not this village tile is active
    * @param pActive <code>true</code> for active, <code>false</code> for haunted
    */
   public void setIsActive(boolean pActive) {
      mIsActive = pActive;
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
      
   /** The active tile image id **/
   private final int mActiveImageId;
   /** The haunted tile image id **/
   private final int mHauntedImageId;
   /** Whether of not this village tile is active **/
   private boolean mIsActive;
   /** The tile location **/
   private ETileLocation mLocation;
   /** The name of this village tile **/
   private final String mName;
   /** The type of village tile **/
   private final EVillageTile mType;
   /** The village tile view id **/
   private int mViewId = View.NO_ID;
}
