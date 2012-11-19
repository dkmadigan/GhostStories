package games.ghoststories.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public abstract class BitmapUtils {     
        
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
   
   public static Bitmap rotateBitmap(Bitmap pOriginal, float pDegrees) {
      Matrix matrix = new Matrix();
      matrix.postRotate(pDegrees);      
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }
   
   public static Bitmap scaleBitmap(Bitmap pOriginal, float pScaleX, float pScaleY) {
      Matrix matrix = new Matrix();
      matrix.postScale(pScaleX, pScaleY);
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }
   
   public static Bitmap rotateAndScaleBitmap(Bitmap pOriginal, float pDegrees,
         float pScaleX, float pScaleY) {
      Matrix matrix = new Matrix();
      matrix.postScale(pScaleX, pScaleY);
      matrix.postRotate(pDegrees);
      return Bitmap.createBitmap(pOriginal, 0, 0, 
            pOriginal.getWidth(), pOriginal.getHeight(), 
            matrix, true);
   }        
}
