package games.ghoststories.enums;

import games.ghoststories.R;

import java.util.EnumSet;

import android.graphics.Color;

/**
 * Defines the different colors used throughout the game
 */
public enum EColor {
   BLACK(R.id.black_tao_tokens, Color.BLACK, Color.BLACK, Color.BLACK),
   BLUE(R.id.blue_tao_tokens, Color.BLUE, Color.rgb(0,153,255), Color.rgb(0,47,140)),
   GREEN(R.id.green_tao_tokens, Color.GREEN, Color.rgb(51, 204,0), Color.rgb(0,132,0)),
   RED(R.id.red_tao_tokens, Color.RED, Color.rgb(222,0,0), Color.rgb(132,0,0)),
   YELLOW(R.id.yellow_tao_tokens, Color.YELLOW, Color.rgb(247,247,49), Color.rgb(132,132,0));   
   
   private EColor(int pTokenId, int pColor, int pLightColor, int pDarkColor) {
      mTokenId = pTokenId;
      mColor = pColor;
      mLightColor = pLightColor;
      mDarkColor = pDarkColor;
   }

   public int getColor() {
      return mColor;
   }
   
   public int getLightColor() {
      return mLightColor;
   }
   
   public int getDarkColor() {
      return mDarkColor;
   }
   
   public int getTokenId() {
      return mTokenId;
   }
   
   public static EnumSet<EColor> getPlayerColors() {
      return sPlayerColors;
   }
   
   private static final EnumSet<EColor> sPlayerColors = 
         EnumSet.of(EColor.YELLOW, EColor.BLUE, EColor.RED, EColor.GREEN);
   
   private final int mColor;
   private final int mLightColor;
   private final int mDarkColor;
   private final int mTokenId;
}
