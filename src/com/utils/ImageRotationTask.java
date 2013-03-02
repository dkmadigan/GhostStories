package com.utils;

import games.ghoststories.utils.BitmapUtils;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

/**
 * <p>Generic class that can be extended to load and then rotate images on a 
 * background thread using the {@link AsyncTask}. 
 * <p>{@link #execute(Integer...)} takes the integer rotation value
 * <p>{@link #onPostExecute(Bitmap)} contains the loaded/rotated bitmap
 */
public abstract class ImageRotationTask extends AsyncTask<Integer, Void, Bitmap> {
   /**
    * Constructor
    * @param pView The view containing the image
    * @param pImageId The image id of the image
    */
   public ImageRotationTask(Resources pResources, int pImageId) {
      mResources = pResources;
      mImageId = pImageId;
   }
   
   /*
    * (non-Javadoc)
    * @see android.os.AsyncTask#doInBackground(Params[])
    */
   @Override
   protected Bitmap doInBackground(Integer... pParams) {      
      Bitmap bm = BitmapFactory.decodeResource(mResources, mImageId);
      Bitmap rotatedBitmap = BitmapUtils.rotateBitmap(bm, pParams[0]);
      return rotatedBitmap;
   }      
   
   /** The resources used to load the image **/
   private final Resources mResources;
   /** The image id **/
   private final int mImageId;
};
