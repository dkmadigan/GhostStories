package games.ghoststories.data;

import games.ghoststories.R;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.BitmapUtils;

import java.util.EnumMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Holds onto references to common bitmaps used by the game. These bitmaps are
 * all loaded during the initialization of the game in order to allow for
 * faster access during the gameplay.
 */
public abstract class GhostStoriesBitmaps {
   /** The back of a ghost card **/
   public static Bitmap sGhostCardBitmap;
   /** The back of a wu feng card **/
   public static Bitmap sWuFengCardBitmap;

   /** Bitmap used for highlighting cards/tiles **/
   public static Bitmap sHighlightBitmap;
   /** Bitmap used for xing out a component **/
   public static Bitmap sXOutBitmap;

   /**
    * Bitmaps for the haunters. One for each side of the board to deal
    * with rotation issues.
    */
   public static Map<EBoardLocation, Bitmap> sHaunterBitmaps =
         new EnumMap<EBoardLocation, Bitmap>(EBoardLocation.class);

   /** The bitmaps for the player boards **/
   public static Map<EColor, Map<ECardLocation, Bitmap>> sPlayerBoardBitmaps =
         new EnumMap<EColor, Map<ECardLocation, Bitmap> >(EColor.class);
   /** The bitmaps for the player icons **/
   public static Map<EColor, Bitmap> sPlayerBitmaps =
         new EnumMap<EColor, Bitmap>(EColor.class);
   /** The bitmaps for the highlighted players icons **/
   public static Map<EColor, Bitmap> sPlayerTurnBitmaps =
         new EnumMap<EColor, Bitmap>(EColor.class);

   /**
    * Initialization method used load in all the bitmaps.
    * @param pResources
    */
   public static void initBitmaps(Resources pResources) {
      if(sGhostCardBitmap == null) {
         sGhostCardBitmap = BitmapFactory.decodeResource(pResources,
               R.drawable.ghost_card_back);
      }
      if(sWuFengCardBitmap == null) {
         sWuFengCardBitmap = BitmapFactory.decodeResource(pResources,
               R.drawable.wu_feng_card_back);
      }
      if(sHighlightBitmap == null) {
         sHighlightBitmap = BitmapFactory.decodeResource(pResources,
               R.drawable.card_halo);
      }
      if(sXOutBitmap == null) {
         sXOutBitmap = BitmapFactory.decodeResource(pResources,
               R.drawable.xout);
      }
      //TODO This should not recreate bitmaps if it doesn't need to
      initHaunterBitmaps(pResources);
      initPlayerBoardBitmaps(pResources);
      initPlayerBitmaps(pResources);
      initPlayerTurnBitmaps(pResources);
   }

   /**
    * Initialize the different haunter bitmaps
    * @param pResources
    */
   private static void initHaunterBitmaps(Resources pResources) {
      Bitmap topBottomBitmap = BitmapFactory.decodeResource(pResources,
            R.drawable.haunter_card);
      sHaunterBitmaps.put(EBoardLocation.TOP, topBottomBitmap);
      sHaunterBitmaps.put(EBoardLocation.BOTTOM, topBottomBitmap);
      sHaunterBitmaps.put(EBoardLocation.LEFT, BitmapUtils.rotateBitmap(
            BitmapFactory.decodeResource(pResources,  R.drawable.haunter_card), 90));
      sHaunterBitmaps.put(EBoardLocation.RIGHT, BitmapUtils.rotateBitmap(
            BitmapFactory.decodeResource(pResources,  R.drawable.haunter_card), 270));
   }

   /**
    * Initialize the player board bitmaps
    * @param pResources
    */
   private static void initPlayerBoardBitmaps(Resources pResources) {
      Map<ECardLocation, Bitmap> blueBitmaps =
            new EnumMap<ECardLocation, Bitmap>(ECardLocation.class);
      blueBitmaps.put(ECardLocation.LEFT,
            BitmapFactory.decodeResource(pResources, R.drawable.blue_left));
      blueBitmaps.put(ECardLocation.MIDDLE,
            BitmapFactory.decodeResource(pResources, R.drawable.blue_middle));
      blueBitmaps.put(ECardLocation.RIGHT,
            BitmapFactory.decodeResource(pResources, R.drawable.blue_right));
      sPlayerBoardBitmaps.put(EColor.BLUE, blueBitmaps);

      Map<ECardLocation, Bitmap> redBitmaps =
            new EnumMap<ECardLocation, Bitmap>(ECardLocation.class);
      redBitmaps.put(ECardLocation.LEFT,
            BitmapFactory.decodeResource(pResources, R.drawable.red_left));
      redBitmaps.put(ECardLocation.MIDDLE,
            BitmapFactory.decodeResource(pResources, R.drawable.red_middle));
      redBitmaps.put(ECardLocation.RIGHT,
            BitmapFactory.decodeResource(pResources, R.drawable.red_right));
      sPlayerBoardBitmaps.put(EColor.RED, redBitmaps);

      Map<ECardLocation, Bitmap> yellowBitmaps =
            new EnumMap<ECardLocation, Bitmap>(ECardLocation.class);
      yellowBitmaps.put(ECardLocation.LEFT,
            BitmapFactory.decodeResource(pResources, R.drawable.yellow_left));
      yellowBitmaps.put(ECardLocation.MIDDLE,
            BitmapFactory.decodeResource(pResources, R.drawable.yellow_middle));
      yellowBitmaps.put(ECardLocation.RIGHT,
            BitmapFactory.decodeResource(pResources, R.drawable.yellow_right));
      sPlayerBoardBitmaps.put(EColor.YELLOW, yellowBitmaps);

      Map<ECardLocation, Bitmap> greenBitmaps =
            new EnumMap<ECardLocation, Bitmap>(ECardLocation.class);
      greenBitmaps.put(ECardLocation.LEFT,
            BitmapFactory.decodeResource(pResources, R.drawable.green_left));
      greenBitmaps.put(ECardLocation.MIDDLE,
            BitmapFactory.decodeResource(pResources, R.drawable.green_middle));
      greenBitmaps.put(ECardLocation.RIGHT,
            BitmapFactory.decodeResource(pResources, R.drawable.green_right));
      sPlayerBoardBitmaps.put(EColor.GREEN, greenBitmaps);
   }

   /**
    * Initialize the player bitmaps
    * @param pResources
    */
   private static void initPlayerBitmaps(Resources pResources) {
      sPlayerBitmaps.put(EColor.RED,
            BitmapFactory.decodeResource(pResources, R.drawable.monk_red));
      sPlayerBitmaps.put(EColor.BLUE,
            BitmapFactory.decodeResource(pResources, R.drawable.monk_blue));
      sPlayerBitmaps.put(EColor.YELLOW,
            BitmapFactory.decodeResource(pResources, R.drawable.monk_yellow));
      sPlayerBitmaps.put(EColor.GREEN,
            BitmapFactory.decodeResource(pResources, R.drawable.monk_green));
   }

   /**
    * Initialize the highlighted player bitmaps
    * @param pResources
    */
   private static void initPlayerTurnBitmaps(Resources pResources) {
      sPlayerTurnBitmaps.put(EColor.RED,
            BitmapFactory.decodeResource(pResources, R.drawable.red_turn));
      sPlayerTurnBitmaps.put(EColor.BLUE,
            BitmapFactory.decodeResource(pResources, R.drawable.blue_turn));
      sPlayerTurnBitmaps.put(EColor.YELLOW,
            BitmapFactory.decodeResource(pResources, R.drawable.yellow_turn));
      sPlayerTurnBitmaps.put(EColor.GREEN,
            BitmapFactory.decodeResource(pResources, R.drawable.green_turn));
   }
}
