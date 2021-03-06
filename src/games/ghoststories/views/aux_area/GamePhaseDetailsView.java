package games.ghoststories.views.aux_area;

import com.utils.AndroidUtils;

import games.ghoststories.data.GhostStoriesConstants;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EGamePhase;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * View that shows the details about the current game phase
 */
public class GamePhaseDetailsView extends TextView implements IGamePhaseListener {
   
   /**
    * Constructor 
    * @param pContext
    */
   public GamePhaseDetailsView(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GamePhaseDetailsView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GamePhaseDetailsView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }

   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase) {
      updateText(pGamePhase);
   }
   
   /**
    * Initialize this view
    */
   private void init() {
      if(isInEditMode()) {
         setText(EGamePhase.YangPhase2.getText());
      } else {
         GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
         Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), 
           GhostStoriesConstants.sFont);
         setTypeface(myTypeface);         
         updateText(GhostStoriesGameManager.getInstance().getCurrentGamePhase());
      }
   }
   
   /**
    * Updates the text of this view using the passed in game phase
    * @param pGamePhase The current game phase
    */
   private void updateText(final EGamePhase pGamePhase) {
      if(AndroidUtils.isUIThread()) {         
         setText(Html.fromHtml(pGamePhase.getText()));
      } else {
         post(new Runnable() {
            public void run() {
               setText(Html.fromHtml(pGamePhase.getText()));
            }});
      }
   }
}
