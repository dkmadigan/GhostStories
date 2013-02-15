package games.ghoststories.views.graveyard;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.utils.GameUtils;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GraveyardScrollView extends HorizontalScrollView {

   public GraveyardScrollView(Context pContext) {
      super(pContext);
      init();
   }

   public GraveyardScrollView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public GraveyardScrollView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);    
      init();
   }     
   
   public void addGhosts(final List<GhostData> pGhosts) {
      if(GameUtils.isUIThread()) {
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
   
   private void init() {
      if(!isInEditMode()) {
         Resources r = getResources();
         mImageHeight = (int)r.getDimension(R.dimen.graveyard_card_height);
         mImageWidth = (int)r.getDimension(R.dimen.graveyard_card_width);
      }
   }
   
   private LinearLayout mLayout = null;
   private int mImageHeight = 0;
   private int mImageWidth = 0;
   private static int sMargin = 30;
}
