package games.ghoststories.enums;

import java.util.EnumSet;

import games.ghoststories.R;

public enum EDice {
   DICE_1(R.id.dice_1),
   DICE_2(R.id.dice_2),
   DICE_3(R.id.dice_3),
   EXTRA_DICE(R.id.extra_dice);
   
   private EDice(int pViewId) {
      mViewId = pViewId;
   }   
   
   public int getViewId() {
      return mViewId;
   }
   
   private final int mViewId;
}
