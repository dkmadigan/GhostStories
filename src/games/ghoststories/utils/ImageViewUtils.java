package games.ghoststories.utils;

import games.ghoststories.enums.EBoardLocation;
import android.graphics.Bitmap;
import android.widget.ImageView;

public abstract class ImageViewUtils {
   public static void setImageBitmapEDT(final ImageView pView, final Bitmap pBitmap) {
      if(GameUtils.isUIThread()) {
         pView.setImageBitmap(pBitmap);
      } else {
         pView.post(new Runnable() {
            public void run() {                
               pView.setImageBitmap(pBitmap);
            }
         });
      }
   }
   
   public static void setImageResourceEDT(final ImageView pView, final int pResourceId) {
      if(GameUtils.isUIThread()) {
         pView.setImageResource(pResourceId);
      } else {
         pView.post(new Runnable() {
            public void run() {                
               pView.setImageResource(pResourceId);
            }
         });
      }
   }
   
   public static void setBoardLocationResource(final ImageView pView, 
         final EBoardLocation pLocation, final int pImageId) {
      //The TOP and BOTTOM card locations are already rotated...only need to rotate
      //the LEFT and RIGHT ones.
      switch(pLocation) {
      case TOP:
      case BOTTOM:
         setImageResourceEDT(pView, pImageId);
         break;
      case RIGHT:
         new ImageRotationTask(pView, pImageId) {
            @Override
            protected void onPostExecute(Bitmap pResult) {
               setImageBitmapEDT(pView, pResult);
            }
         }.execute(270);
         break;
      case LEFT:
         new ImageRotationTask(pView, pImageId) {
            @Override
            protected void onPostExecute(Bitmap pResult) {
               setImageBitmapEDT(pView, pResult);
            }
         }.execute(90);
         break;
      }
   }
}
