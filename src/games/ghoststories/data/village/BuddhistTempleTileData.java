package games.ghoststories.data.village;

import games.ghoststories.enums.EVillageTile;

/**
 * Data class representing the Buddhist Temple Village Tile
 */
public class BuddhistTempleTileData extends VillageTileData {

   /**
    * Constructor
    * @param pName The name of the tile
    * @param pTileType The type of tile
    * @param pActiveImageId The active image id for the tile
    * @param pHauntedImageId The haunted image id for the tile
    */
   public BuddhistTempleTileData(String pName, EVillageTile pTileType,
         int pActiveImageId, int pHauntedImageId) {
      super(pName, pTileType, pActiveImageId, pHauntedImageId);
   }
   
   /**
    * Gets the number of buddhas
    * @return The number of buddhas
    */
   public int getNumBuddhas() {
      return mNumBuddhas;
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
   
   /** The number of buddhas **/
   private int mNumBuddhas = 0;
}
