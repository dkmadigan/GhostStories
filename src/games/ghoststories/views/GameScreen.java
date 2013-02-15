package games.ghoststories.views;

import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameScreen extends RelativeLayout implements IGamePhaseListener {

   public GameScreen(Context pContext) {
      super(pContext);
      init();
   }

   public GameScreen(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public GameScreen(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }   

   public void gamePhaseUpdated(final EGamePhase pGamePhase) {
      if(GameUtils.isUIThread()) {
         EColor currentPlayer = 
               GhostStoriesGameManager.getInstance().getCurrentPlayerData().getColor();
         if(pGamePhase == EGamePhase.TurnStart) {
            if(mTurnView == null) {
               mTurnView = new ImageView(getContext());
            }
            
            //We have a new player so show the new player animation            
            mTurnView.setImageBitmap(GhostStoriesBitmaps.sPlayerTurnBitmaps.get(currentPlayer));
            mTurnView.setScaleX(0.0f);
            mTurnView.setScaleY(0.0f);
            addView(mTurnView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
                  LayoutParams.MATCH_PARENT));
            mTurnView.animate().setDuration(1500).scaleX(1.0f).scaleY(1.0f).withEndAction(
                  new Runnable() {
                     public void run() {
                        mTurnHandler.sendMessageDelayed(
                              Message.obtain(mTurnHandler, 0, mTurnView),
                              1000);                       
                     }
                  });
         }
      } else {
         post(new Runnable() {
            public void run() {
               gamePhaseUpdated(pGamePhase);
            }
         });
      }
   }
   
   private void init() {
      if(!isInEditMode()) {
         GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
      }
   }

   private ImageView mTurnView = null;
   private Handler mTurnHandler = new TurnHandler(this);
   private static class TurnHandler extends Handler  {
      public TurnHandler(RelativeLayout pParentView) {
         mParentView = pParentView;
      }
      
      public void handleMessage(Message pMsg) {         
         mParentView.removeView((View)pMsg.obj);
         GhostStoriesGameManager.getInstance().advanceGamePhase();
      }; 
      
      private final ViewGroup mParentView;
   }
}
