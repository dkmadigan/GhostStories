package games.ghoststories.enums;

import games.ghoststories.R;

import java.util.EnumSet;

import android.graphics.Color;

/**
 * Defines the different colors used throughout the game
 */
public enum EColor {
   BLACK(R.id.black_tau_tokens, Color.BLACK),
   BLUE(R.id.blue_tau_tokens, Color.BLUE),
   GREEN(R.id.green_tau_tokens, Color.GREEN),
   RED(R.id.red_tau_tokens, Color.RED),
   YELLOW(R.id.yellow_tau_tokens, Color.YELLOW);   
   
   private EColor(int pTokenId, int pColor) {
      mTokenId = pTokenId;
      mColor = pColor;
   }

   public int getColor() {
      return mColor;
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
   private final int mTokenId;
}
