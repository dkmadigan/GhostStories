package com.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Utility class relating to {@link ImageView}s
 */
public abstract class ImageViewUtils {
   
   /**
    * Sets the bitmap image on the {@link ImageView} on the UI Thread
    * @param pView The view to set the bitmap on
    * @param pBitmap The bitmap
    */
   public static void setImageBitmap(final ImageView pView, final Bitmap pBitmap) {
      if(AndroidUtils.isUIThread()) {
         pView.setImageBitmap(pBitmap);
      } else {
         pView.post(new Runnable() {
            public void run() {                
               pView.setImageBitmap(pBitmap);
            }
         });
      }
   }
   
   /**
    * Sets the image resource on the {@link ImageView} on the UI Thread
    * @param pView The view to set the resource on 
    * @param pResourceId The resource id
    */
   public static void setImageResource(final ImageView pView, final int pResourceId) {
      if(AndroidUtils.isUIThread()) {
         pView.setImageResource(pResourceId);
      } else {
         pView.post(new Runnable() {
            public void run() {                
               pView.setImageResource(pResourceId);
            }
         });
      }
   }
   

}
