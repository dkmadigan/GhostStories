package games.ghoststories.views.aux_area;

import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

public class GamePhaseDetailsView extends TextView implements IGamePhaseListener {
   public GamePhaseDetailsView(Context pContext) {
      super(pContext);
      init();
   }

   public GamePhaseDetailsView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public GamePhaseDetailsView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
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
      if(GameUtils.isUIThread()) {         
         setText(Html.fromHtml(pGamePhase.getText()));
      } else {
         post(new Runnable() {
            public void run() {
               setText(Html.fromHtml(pGamePhase.getText()));
            }});
      }
   }
}
