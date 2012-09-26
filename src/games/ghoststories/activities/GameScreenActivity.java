package games.ghoststories.activities;

import games.ghoststories.R;
import games.ghoststories.R.layout;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDifficulty;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;

public class GameScreenActivity extends FragmentActivity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
     
       //Remove title bar
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       //Remove notification bar
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                
       GhostStoriesGameManager.getInstance().initializeGame(EDifficulty.INITIATE, 
             getBaseContext());                           
       
       setContentView(R.layout.gamescreen);       
   }

   public void nextButtonCB(View pView) {
      new Thread(new Runnable() {
         public void run() {
            GhostStoriesGameManager.getInstance().advanceGamePhase();
         }
      }).start();      
   }
}
