package games.ghoststories.data.village;

import games.ghoststories.enums.EVillageTile;

/**
 * Factory class used to create {@link VillageTileData} classes
 */
public abstract class VillageTileDataFactory {

   /**
    * Creates a {@link VillageTileData} of the appropriate type
    * @param pTileType The type of tile 
    * @param pTileName The name of the tile
    * @param pActiveImageId The active image id
    * @param pHauntedImageId The haunted image id
    * @return The {@link VillageTileData} of the appropriate type
    */
   public static VillageTileData createVillageTileData(EVillageTile pTileType, 
         String pTileName, int pActiveImageId, int pHauntedImageId) {
      VillageTileData vtd = null;
      switch(pTileType) {      
      case BUDDHIST_TEMPLE:
         vtd = new BuddhistTempleTileData(pTileName, pTileType, pActiveImageId, 
               pHauntedImageId);
         break;
      case CIRCLE_OF_PRAYER:
         vtd = new CircleOfPrayerTileData(pTileName, pTileType, pActiveImageId,
               pHauntedImageId);
         break;
      default:
         vtd = new VillageTileData(pTileName, pTileType, pActiveImageId, 
               pHauntedImageId);
         break;
      }
      return vtd;
   }

}
