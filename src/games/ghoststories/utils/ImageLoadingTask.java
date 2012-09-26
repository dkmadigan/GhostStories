package games.ghoststories.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

public abstract class ImageLoadingTask extends AsyncTask<Integer, Void, Bitmap> {

   public ImageLoadingTask(View pView) {
      mView = pView;
   }
   
   @Override
   protected Bitmap doInBackground(Integer... pParams) {
      Bitmap bitmap = 
            BitmapFactory.decodeResource(mView.getResources(), pParams[0]);
      return bitmap;
   }

   private final View mView;   
}
