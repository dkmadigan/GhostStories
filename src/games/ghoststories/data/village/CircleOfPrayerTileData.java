package games.ghoststories.data.village;

import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EVillageTile;

/**
 * Data class representing the Circle of Prayer Village Tile.   
 */
public class CircleOfPrayerTileData extends VillageTileData {

   /**
    * Constructor
    * @param pName The name of the tile
    * @param pTileType The type of tile
    * @param pActiveImageId The active image id for the tile
    * @param pHauntedImageId The haunted image id for the tile
    */
   public CircleOfPrayerTileData(String pName, EVillageTile pTileType, int pActiveImageId,
         int pHauntedImageId) {
      super(pName, pTileType, pActiveImageId, pHauntedImageId);
   }
   
   /**
    * Gets the token color
    * @return The token color
    */
   public EColor getTokenColor() {
      return mTokenColor;
   }
   
   /**
    * Sets the token color for the circle of prayer village tile.
    * @param pTokenColor The color of the token
    */
   public void setTokenColor(EColor pTokenColor) {
      mTokenColor = pTokenColor;
      notifyListeners();
   }
   
   /** The token color **/
   private EColor mTokenColor = null;
}
