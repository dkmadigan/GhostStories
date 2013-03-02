package games.ghoststories.activities;

import com.utils.AnimationUtils2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostGraveyardData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.views.GameScreen;
import games.ghoststories.views.combat.CombatView;
import games.ghoststories.views.graveyard.GraveyardScrollView;

/**
 * Activity for the main game screen.
 */
public class GameScreenActivity extends FragmentActivity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
     
       //Remove title bar
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       //Remove notification bar
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
             WindowManager.LayoutParams.FLAG_FULLSCREEN);                                        
       
       setContentView(R.layout.gamescreen); 
       
       GameScreen gs = (GameScreen)findViewById(R.id.gamescreen);
       gs.gamePhaseUpdated(EGamePhase.TurnStart);
   }

   /**
    * Callback for the card info button. Brings up the card info view on top
    * of the current view.
    * @param pView
    */
   public void cardInfoCB(View pView) { 
      //Only handle if the top card is showing
      GhostDeckData deck = 
            GhostStoriesGameManager.getInstance().getGhostDeckData();
      GhostData topCard = deck.getTopCard();
      if(topCard.isFlipped()) {
         LayoutInflater li = getLayoutInflater();
         View view = li.inflate(R.layout.card_info, null);         
         ImageView card = (ImageView)view.findViewById(R.id.card);
         card.setImageResource(topCard.getImageId());         
         addContentView(view, new ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
         AnimationUtils2.runAnimation(R.anim.expand_view, view);
      }                     
   }
   
   /**
    * Called to close the card info view
    * @param pView
    */
   public void closeCardInfo(View pView) {
      ViewGroup cardInfoView = (ViewGroup)pView.getParent();
      ViewGroup parent = (ViewGroup)cardInfoView.getParent();
      parent.removeView(cardInfoView);
   }
   
   /**
    * Called when the close button is pressed on the combat view
    * @param pView
    */
   public void closeCombatView(View pView) {      
      CombatView combatView = (CombatView)pView.getParent();
      combatView.destroy();
      ViewGroup vg = (ViewGroup)(combatView.getParent());
      vg.removeView(combatView);
   }
   
   /**
    * Called to close the ghost graveyard view
    * @param pView
    */
   public void closeGraveyard(View pView) {
      ViewGroup graveyardView = (ViewGroup)pView.getParent();
      ViewGroup parent = (ViewGroup)graveyardView.getParent();
      parent.removeView(graveyardView);
   }
   
   /**
    * Callback for the ghost graveyard image. Brings up the graveyard view.
    * @param pView
    */
   public void ghostGraveyardCB(View pView) {
      GhostGraveyardData graveyardData = 
            GhostStoriesGameManager.getInstance().getGhostGraveyardData();
      LayoutInflater li = getLayoutInflater();
      View view = li.inflate(R.layout.graveyard, null);
      GraveyardScrollView graveyard = 
            (GraveyardScrollView)view.findViewById(R.id.graveyard);      
      graveyard.addGhosts(graveyardData.getGhosts());
      addContentView(view, new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
   }
         
   /**
    * Called when the "NEXT" button is pressed. Debug function only.
    * @param pView
    */
   public void nextButtonCB(View pView) {
      new Thread(new Runnable() {
         public void run() {
            GhostStoriesGameManager.getInstance().advanceGamePhase();
         }
      }).start();      
   }
}
