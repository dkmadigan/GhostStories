package games.ghoststories.data;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import games.ghoststories.R;
import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Holds onto references to common bitmaps used by the game.
 */
public abstract class GhostStoriesBitmaps {
   public static Bitmap sGhostCardBitmap;
   public static Bitmap sWuFengCardBitmap;
   public static Bitmap sHighlightCardBitmap;
   public static Map<EColor, Map<ECardLocation, Bitmap>> sPlayerBoardBitmaps = 
         new EnumMap<EColor, Map<ECardLocation, Bitmap> >(EColor.class);
   public static Map<EColor, Bitmap> sPlayerBitmaps = 
         new EnumMap<EColor, Bitmap>(EColor.class);
   
   public static void initBitmaps(Resources pResources) {
      if(sGhostCardBitmap == null) {
         sGhostCardBitmap = BitmapFactory.decodeResource(pResources, 
               R.drawable.ghost_card_back);
      }
      if(sWuFengCardBitmap == null) {
         sWuFengCardBitmap = BitmapFactory.decodeResource(pResources, 
               R.drawable.wu_feng_card_back);
      }
      if(sHighlightCardBitmap == null) {         
         sHighlightCardBitmap = BitmapFactory.decodeResource(pResources, 
               R.drawable.card_halo);
      }      
      initPlayerBoardBitmaps(pResources);
      initPlayerBitmaps(pResources);
   }  
   
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
}
