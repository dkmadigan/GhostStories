package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.PlayerData;
import games.ghoststories.utils.ImageRotationTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

public class BuddhaTokenView extends AbstractNumberedTokenView {
   /**
    * Constructor 
    * @param pContext The context the view is running in
    */
   public BuddhaTokenView(Context pContext) {
      super(pContext);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    */
   public BuddhaTokenView(Context pContext, AttributeSet pAttrs) {
      this(pContext, pAttrs, 0);
   }
   
   /**
    * Constructor
    * @param pContext The context the view is running in
    * @param pAttrs The attributes of this view
    * @param pDefStyle The default style applied to this view
    */
   public BuddhaTokenView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.views.AbstractNumberedTokenView#setPlayerData(games.ghoststories.data.PlayerData)
    */
   @Override
   public void setPlayerData(final PlayerData pData) {      
      super.setPlayerData(pData);        
      setNumber(mPlayerData.getNumBuddhaTokens());                 
            
      Integer rotateDegrees = null;
      
      //Rotate the buddha based on the current player location.
      //TOP and BOTTOM are already rotated in the layout
      switch(pData.getBoardLocation()) {      
         case RIGHT:       
         rotateDegrees = 270;      
         break;  
      case LEFT:
         rotateDegrees = 90;               
         break; 
      }            
      
      if(rotateDegrees != null) {
         ImageRotationTask rotateTask = new ImageRotationTask(this, R.drawable.buddha) {
            @Override
            protected void onPostExecute(Bitmap pResult) {          
               setImageBitmap(pResult);
            }
         };
         rotateTask.execute(rotateDegrees);
      }
   }     
}
