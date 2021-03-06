package games.ghoststories.activities;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EDifficulty;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Activity for the initial game loading screen. Initializes the game and then
 * starts the game activity.
 */
public class GameLoadingActivity extends Activity {
   /*
    * (non-Javadoc)
    * @see android.app.Activity#onCreate(android.os.Bundle)
    */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      //Initialize the game on a background thread
      new AsyncTask<Void, Void, Void>() {
         @Override
         protected Void doInBackground(Void... params) {
            GhostStoriesBitmaps.initBitmaps(getResources());
            GhostStoriesGameManager.getInstance().initializeGame(
                  EDifficulty.INITIATE, GameLoadingActivity.this);            
            return null;
         }

         @Override
         protected void onPostExecute(Void result) {          
            //Switch to the game activity
            Intent intent = new Intent(GameLoadingActivity.this, GameScreenActivity.class);
            startActivity(intent);
         }
      }.execute();

      setContentView(R.layout.game_load);
   }
}
