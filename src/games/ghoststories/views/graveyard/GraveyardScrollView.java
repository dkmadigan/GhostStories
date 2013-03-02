package games.ghoststories.views.graveyard;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.utils.AndroidUtils;

/**
 * View used for the graveyard scrollable area. This shows a horizontal 
 * scrollable area with all of the defeated ghosts. 
 */
public class GraveyardScrollView extends HorizontalScrollView {

   /**
    * Constructor
    * @param pContext
    */
   public GraveyardScrollView(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GraveyardScrollView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GraveyardScrollView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);    
      init();
   }     
   
   /**
    * Adds the set of ghosts to the view
    * @param pGhosts The ghosts to add to the view
    */
   public void addGhosts(final List<GhostData> pGhosts) {
      if(AndroidUtils.isUIThread()) {
         if(mLayout == null) {
            mLayout = (LinearLayout)findViewById(R.id.layout);
         }

         for(GhostData gd : pGhosts) {
            ImageView view = new ImageView(getContext());
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                  mImageWidth, mImageHeight);
            params.setMargins(sMargin, 0, 0, 0);
            
            //view.setLayoutParams(params);            
            view.setImageResource(gd.getImageId());              
            mLayout.addView(view, params);
         }
         //mLayout.invalidate();                 
      } else {
         post(new Runnable() {
            public void run() {
               addGhosts(pGhosts);               
            }
         });
      }
   }     
   
   /**
    * Initialize the view
    */
   private void init() {
      if(!isInEditMode()) {
         Resources r = getResources();
         mImageHeight = (int)r.getDimension(R.dimen.graveyard_card_height);
         mImageWidth = (int)r.getDimension(R.dimen.graveyard_card_width);
      }
   }

   /** Margin between ghosts **/
   //TODO Use dimension xml
   private static int sMargin = 30;
   
   /** The height of the ghost images **/
   private int mImageHeight = 0;
   /** The width of the ghost images **/
   private int mImageWidth = 0;
   /** The linear layout contained within this scroll view **/
   private LinearLayout mLayout = null;   
}
