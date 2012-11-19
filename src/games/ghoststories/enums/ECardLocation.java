package games.ghoststories.enums;

import games.ghoststories.R;

public enum ECardLocation {
   LEFT(R.id.left_space),
   MIDDLE(R.id.middle_space),
   RIGHT(R.id.right_space);
   
   private ECardLocation(int pCardLocationId) {
      mCardLocationId = pCardLocationId;
   }
   
   public int getCardLocationId() {
      return mCardLocationId;
   }
   
   private int mCardLocationId;
}
