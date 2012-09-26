package games.ghoststories.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

public abstract class ImageRotationTask extends AsyncTask<Integer, Void, Bitmap> {
   /**
    * Constructor
    * @param pView The view containing the image
    * @param pImageId The image id of the image
    */
   public ImageRotationTask(View pView, int pImageId) {
      mView = pView;
      mImageId = pImageId;
   }
   
   /*
    * (non-Javadoc)
    * @see android.os.AsyncTask#doInBackground(Params[])
    */
   @Override
   protected Bitmap doInBackground(Integer... pParams) {      
      Bitmap bm = BitmapFactory.decodeResource(mView.getResources(), mImageId);
      Bitmap rotatedBitmap = BitmapUtils.rotateBitmap(bm, pParams[0]);
      return rotatedBitmap;
   }      
   
   /** The view containing the image **/
   private final View mView;
   /** The image id **/
   private final int mImageId;
};
