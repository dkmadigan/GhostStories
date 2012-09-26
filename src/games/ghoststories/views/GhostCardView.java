package games.ghoststories.views;

import games.ghoststories.data.GhostData;
import games.ghoststories.utils.ImageLoadingTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * View representing a ghost card. The ghost card is an {@link ImageView} that 
 * is able to be dragged around the play surface until it is placed on one
 * of the game boards.
 */
public class GhostCardView extends ImageView {

   public GhostCardView(Context pContext) {
      super(pContext);
   }
   
   public GhostCardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }
   
   public GhostCardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /**
    * Constructor
    * @param pContext
    */
   public GhostCardView(Context pContext, GhostData pGhostData) {
      super(pContext);            
      mGhostData = pGhostData;
      /*
      ImageLoadingTask imageLoader = new ImageLoadingTask(this) {
         @Override
         protected void onPostExecute(Bitmap pResult) {          
            setImageBitmap(pResult);
         }
      };
      
      //Load the bitmap image for the ImageView on a background thread      
      new Thread(new Runnable() {
         public void run() {
            int id = getResources().getIdentifier(mGhostData.getImageName(), 
                  "drawable", getContext().getPackageName());            
            final Bitmap bitmap =
                  BitmapFactory.decodeResource(getResources(), id);
             GhostCardView.this.post(new Runnable() {
                 public void run() {
                     GhostCardView.this.setImageBitmap(bitmap);
                 }
             });
         }
     }).start();
     
     */
   }
   
   /** The ghost data for this card **/
   private GhostData mGhostData;
}
