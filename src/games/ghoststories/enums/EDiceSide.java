package games.ghoststories.enums;

import games.ghoststories.R;

/**
 * Defines the six different sides of the dice
 */
public enum EDiceSide {
   RED(EColor.RED, R.drawable.tao_dice_red, R.drawable.extra_tao_dice_red),
   BLUE(EColor.BLUE, R.drawable.tao_dice_blue, R.drawable.extra_tao_dice_blue),
   BLACK(EColor.BLACK, R.drawable.tao_dice_black, R.drawable.extra_tao_dice_black),
   YELLOW(EColor.YELLOW, R.drawable.tao_dice_yellow, R.drawable.extra_tao_dice_yellow),
   GREEN(EColor.GREEN, R.drawable.tao_dice_green, R.drawable.extra_tao_dice_green),
   WHITE(null, R.drawable.tao_dice_white, R.drawable.extra_tao_dice_white);
   
   /**
    * Constructor
    * @param pColor The color  
    * @param pDiceDrawable The drawable id for this dice side
    * @param pExtraDiceDrawable The drawable id for the extra dice side
    */
   private EDiceSide(EColor pColor, int pDiceDrawable, int pExtraDiceDrawable) {
      mColor = pColor;
      mDiceDrawable = pDiceDrawable;
      mExtraDiceDrawable = pExtraDiceDrawable;
   }
   
   /**
    * @return The color
    */
   public EColor getColor() {
      return mColor;
   }
   
   /**
    * @return The id for the dice drawable
    */
   public int getDiceDrawable() {
      return mDiceDrawable;
   }
   
   /**
    * @return The id for the extra dice drawable
    */
   public int getExtraDiceDrawable() {
      return mExtraDiceDrawable;
   }
   
   /** The color **/
   private final EColor mColor;
   /** The id for the dice drawable **/
   private final int mDiceDrawable;
   /** The id of the extra dice drawable **/
   private final int mExtraDiceDrawable;
}
