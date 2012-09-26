package games.ghoststories.enums;

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
   
   private final int mLayoutId;
}
