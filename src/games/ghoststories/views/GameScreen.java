package games.ghoststories.views;

import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.utils.AndroidUtils;

/**
 * The main game screen layout. Contains the game board and the aux area. When 
 * it becomes a new players turn, shows an animation to indicate the players turn.
 */
public class GameScreen extends RelativeLayout implements IGamePhaseListener {

   /**
    * Constructor
    * @param pContext
    */
   public GameScreen(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public GameScreen(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public GameScreen(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }   

   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(final EGamePhase pGamePhase) {
      if(AndroidUtils.isUIThread()) {
         //The EGamePhase.TurnStart phase indicates the beginning of a new 
         //players turn. Animate the change in turn view to show the current
         //player turn.
         if(pGamePhase == EGamePhase.TurnStart) {
            EColor currentPlayer = 
                  GhostStoriesGameManager.getInstance().getCurrentPlayerData().getColor();
            if(mTurnView == null) {
               mTurnView = new ImageView(getContext());
            }
            
            //We have a new player so show the new player animation            
            mTurnView.setImageBitmap(GhostStoriesBitmaps.sPlayerTurnBitmaps.get(
                  currentPlayer));
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
   
   /**
    * Initialize the view
    */
   private void init() {
      if(!isInEditMode()) {
         GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
      }
   }
   
   /**
    * Handler class used remove the change in turn view after a given period
    * of time.
    */
   private static class TurnHandler extends Handler  {
      /**
       * Constructor
       * @param pParentView The parent view to remove the turn view from
       */
      public TurnHandler(RelativeLayout pParentView) {
         mParentView = pParentView;
      }
      
      /*
       * (non-Javadoc)
       * @see android.os.Handler#handleMessage(android.os.Message)
       */
      public void handleMessage(Message pMsg) {         
         mParentView.removeView((View)pMsg.obj);
         GhostStoriesGameManager.getInstance().advanceGamePhase();
      }; 
      
      /** The parent view to remove the turn view from **/
      private final ViewGroup mParentView;
   }

   /** Handler used to remove the turn view after a given time period **/
   private Handler mTurnHandler = new TurnHandler(this);
   /** View that is used to indicate the change in turns **/
   private ImageView mTurnView = null;      
}
