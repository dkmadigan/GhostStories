package games.ghoststories.views.aux_area;

import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

public class GamePhaseView extends TextView implements IGamePhaseListener {

   public GamePhaseView(Context pContext) {
      super(pContext);
      init();
   }

   public GamePhaseView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public GamePhaseView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }

   public void gamePhaseUpdated(EGamePhase pGamePhase) {
      updateText(pGamePhase);
   }
   
   private void init() {
      GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
      updateText(GhostStoriesGameManager.getInstance().getCurrentGamePhase());
   }

   private void updateText(final EGamePhase pGamePhase) {
      final String currentPlayerName; 
      if(isInEditMode()) {
         currentPlayerName = "Dave";
      } else {
         currentPlayerName = 
               GhostStoriesGameManager.getInstance().getCurrentPlayerData().getName();
      }

      if(GameUtils.isUIThread()) {         
         setText(Html.fromHtml(pGamePhase.getName()));                
      } else {
         post(new Runnable() {
            public void run() {
               setText(Html.fromHtml(pGamePhase.getName()));
            }});
      }
   }
}
