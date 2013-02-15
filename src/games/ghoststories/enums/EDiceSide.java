package games.ghoststories.enums;

import games.ghoststories.R;

public enum EDiceSide {
   RED(EColor.RED, R.drawable.tao_dice_red, R.drawable.extra_tao_dice_red),
   BLUE(EColor.BLUE, R.drawable.tao_dice_blue, R.drawable.extra_tao_dice_blue),
   BLACK(EColor.BLACK, R.drawable.tao_dice_black, R.drawable.extra_tao_dice_black),
   YELLOW(EColor.YELLOW, R.drawable.tao_dice_yellow, R.drawable.extra_tao_dice_yellow),
   GREEN(EColor.GREEN, R.drawable.tao_dice_green, R.drawable.extra_tao_dice_green),
   WHITE(null, R.drawable.tao_dice_white, R.drawable.extra_tao_dice_white);
   
   private EDiceSide(EColor pColor, int pDiceDrawable, int pExtraDiceDrawable) {
      mColor = pColor;
      mDiceDrawable = pDiceDrawable;
      mExtraDiceDrawable = pExtraDiceDrawable;
   }
   
   public EColor getColor() {
      return mColor;
   }
   
   public int getDiceDrawable() {
      return mDiceDrawable;
   }
   
   public int getExtraDiceDrawable() {
      return mExtraDiceDrawable;
   }
   
   private final EColor mColor;
   private final int mDiceDrawable;
   private final int mExtraDiceDrawable;
}
