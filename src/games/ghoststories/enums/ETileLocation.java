package games.ghoststories.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import games.ghoststories.R;

public enum ETileLocation {
   TOP_LEFT(R.id.village_tile_1),
   TOP_CENTER(R.id.village_tile_2),
   TOP_RIGHT(R.id.village_tile_3),
   MIDDLE_LEFT(R.id.village_tile_4),
   MIDDLE_CENTER(R.id.village_tile_5),
   MIDDLE_RIGHT(R.id.village_tile_6),
   BOTTOM_LEFT(R.id.village_tile_7),
   BOTTOM_CENTER(R.id.village_tile_8),
   BOTTOM_RIGHT(R.id.village_tile_9);
   
   private ETileLocation(final int pLayoutId) {
      mLayoutId = pLayoutId;
   }
   
   public int getLayoutId() {
      return mLayoutId;
   }
   
   public EnumSet<ETileLocation> getAdjacentTiles() {
      return sAdjacentTileMap.get(this);
   }
   
   private final int mLayoutId;
   private static Map<ETileLocation, EnumSet<ETileLocation> > sAdjacentTileMap = 
         new EnumMap<ETileLocation, EnumSet<ETileLocation> >(ETileLocation.class);
   static {
      sAdjacentTileMap.put(TOP_LEFT, 
            EnumSet.of(TOP_CENTER, MIDDLE_LEFT, MIDDLE_CENTER));
      sAdjacentTileMap.put(TOP_CENTER, 
            EnumSet.of(TOP_LEFT, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT));
      sAdjacentTileMap.put(TOP_RIGHT, 
            EnumSet.of(TOP_CENTER, MIDDLE_CENTER, MIDDLE_RIGHT));
      sAdjacentTileMap.put(MIDDLE_LEFT, 
            EnumSet.of(TOP_LEFT, TOP_CENTER, MIDDLE_CENTER, BOTTOM_LEFT, BOTTOM_CENTER));
      sAdjacentTileMap.put(MIDDLE_CENTER, 
            EnumSet.complementOf(EnumSet.of(MIDDLE_CENTER)));
      sAdjacentTileMap.put(MIDDLE_RIGHT, 
            EnumSet.of(TOP_CENTER, TOP_RIGHT, MIDDLE_CENTER, BOTTOM_CENTER, BOTTOM_RIGHT));
      sAdjacentTileMap.put(BOTTOM_LEFT, 
            EnumSet.of(MIDDLE_LEFT, MIDDLE_CENTER, BOTTOM_CENTER));
      sAdjacentTileMap.put(BOTTOM_CENTER, 
            EnumSet.of(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT));
      sAdjacentTileMap.put(BOTTOM_RIGHT,
            EnumSet.of(MIDDLE_CENTER, MIDDLE_RIGHT, BOTTOM_CENTER));
   }
}
