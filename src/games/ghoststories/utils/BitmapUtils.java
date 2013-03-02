package games.ghoststories.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Utility class containing methods to manipulate bitmaps
 */
public abstract class BitmapUtils {     

   /**
    * 
    * @param pOptions
    * @param pReqWidth
    * @param pReqHeight
    * @return
    */
   public static int calculateInSampleSize(
         BitmapFactory.Options pOptions, int pReqWidth, int pReqHeight) {
      // Raw height and width of image
      final int height = pOptions.outHeight;
      final int width = pOptions.outWidth;
      int inSampleSize = 1;

      if (height > pReqHeight || width > pReqWidth) {
         if (width > height) {
            inSampleSize = Math.round((float)height / (float)pReqHeight);
         } else {
            inSampleSize = Math.round((float)width / (float)pReqWidth);
         }
      }
      return inSampleSize;
   }

   /**
    * Decode a bitmap from the given file
    * @param pPath The path to the bitmap to load
    * @param pReqWidth The width of the loaded bitmap
    * @param pReqHeight The height of the loaded bitmap
    * @return The decoded bitmap
    */
   public static Bitmap decodeSampledBitmapFromFile(String pPath,
         int pReqWidth, int pReqHeight) {

      // First decode with inJustDecodeBounds=true to check dimensions
      final BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(pPath, options);     

      // Calculate inSampleSize
      options.inSampleSize = calculateInSampleSize(options, pReqWidth, pReqHeight);

      // Decode bitmap with inSampleSize set
      options.inJustDecodeBounds = false;
      return BitmapFactory.decodeFile(pPath, options);     
   }

   /**
    * Decode a bitmap from the given resource id
    * @param pRes Reference to the resources
    * @param pResId The id of the bitmap resource to decode
    * @param pReqWidth The width of the loaded bitmap
    * @param pReqHeight The height of the loaded bitmap
    * @return The decoded bitmap
    */
   public static Bitmap decodeSampledBitmapFromResource(Resources pRes, int pResId,
         int pReqWidth, int pReqHeight) {

      // First decode with inJustDecodeBounds=true to check dimensions
      final BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeResource(pRes, pResId, options);

      // Calculate inSampleSize
      options.inSampleSize = calculateInSampleSize(options, pReqWidth, pReqHeight);

      // Decode bitmap with inSampleSize set
      options.inJustDecodeBounds = false;
      return BitmapFactory.decodeResource(pRes, pResId, options);
   }

   /**
    * Rotates the given bitmap the number of degrees. Allocates a new 
    * bitmap.
    * @param pOriginal The bitmap to rotate
    * @param pDegrees The degrees to rotate the bitmap.
    * @return A new rotated bitmap
    */
   public static Bitmap rotateBitmap(Bitmap pOriginal, float pDegrees) {
      Matrix matrix = new Matrix();
      matrix.postRotate(pDegrees);      
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }

   /**
    * Rotates and scales the specified bitmap by the given amount. Allocates
    * a new bitmap.
    * @param pOriginal The original bitmap
    * @param pDegrees The degrees to rotate the bitmap
    * @param pScaleX The scale x factor
    * @param pScaleY The scale y factor
    * @return A new rotated and scaled bitmap
    */
   public static Bitmap rotateAndScaleBitmap(Bitmap pOriginal, float pDegrees,
         float pScaleX, float pScaleY) {
      Matrix matrix = new Matrix();
      matrix.postScale(pScaleX, pScaleY);
      matrix.postRotate(pDegrees);
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }        

   /**
    * Scales the specified bitmap by the given amount.
    * @param pOriginal The original bitmap
    * @param pScaleX The scale x factor
    * @param pScaleY The scale y factor
    * @return A new scaled bitmap
    */
   public static Bitmap scaleBitmap(Bitmap pOriginal, float pScaleX, float pScaleY) {
      Matrix matrix = new Matrix();
      matrix.postScale(pScaleX, pScaleY);
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }      
}
