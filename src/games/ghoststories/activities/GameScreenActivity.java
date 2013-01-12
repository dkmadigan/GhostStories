package games.ghoststories.activities;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostDeckData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.utils.GameUtils;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
   }

   public void nextButtonCB(View pView) {
      new Thread(new Runnable() {
         public void run() {
            GhostStoriesGameManager.getInstance().advanceGamePhase();
         }
      }).start();      
   }
   
   public void cardInfoCB(View pView) { 
      //Only handle if the top card is showing
      GhostDeckData deck = 
            GhostStoriesGameManager.getInstance().getGhostDeckData();
      GhostData topCard = deck.getTopCard();
      if(topCard.isFlipped()) {
         LayoutInflater li = getLayoutInflater();
         View view = li.inflate(R.layout.card_info, null);         
         ImageView card = (ImageView)view.findViewById(R.id.card);
         /*
         Bitmap bmap =
               BitmapFactory.decodeResource(getResources(), topCard.getImageId());
         */ 
         card.setImageResource(topCard.getImageId());         
         addContentView(view, new ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
         GameUtils.runAnimation(R.anim.scale_card_info, view);
      }                     
   }
   
   public void closeCardInfoCB(View pView) {
      ViewGroup cardInfoView = (ViewGroup)pView.getParent();
      ViewGroup parent = (ViewGroup)cardInfoView.getParent();
      parent.removeView(cardInfoView);
   }
}
