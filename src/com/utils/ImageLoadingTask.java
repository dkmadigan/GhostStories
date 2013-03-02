package com.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/**
 * <p>Generic class that can be extended to load images on a background thread 
 * using the {@link AsyncTask}. 
 * <p>{@link #execute(Integer...)} takes the integer id of the bitmap resource to load
 * <p>{@link #onPostExecute(Bitmap)} contains the loaded bitmap
 */
public abstract class ImageLoadingTask extends AsyncTask<Integer, Void, Bitmap> {

   /**
    * Constructor
    * @param pResources Resources used to decode
    */
   public ImageLoadingTask(Resources pResources) {
      mResources = pResources;
   }
   
   /*
    * (non-Javadoc)
    * @see android.os.AsyncTask#doInBackground(Params[])
    */
   @Override
   protected Bitmap doInBackground(Integer... pParams) {
      Bitmap bitmap = 
            BitmapFactory.decodeResource(mResources, pParams[0]);
      return bitmap;
   }

   /** Resources used to load bitmap **/
   private final Resources mResources;   
}
