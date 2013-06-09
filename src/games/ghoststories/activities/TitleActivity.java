package games.ghoststories.activities;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesConstants;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.enums.EDifficulty;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Activity for the title screen of the game
 */
public class TitleActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.title);

      mLoadingTextView = (TextView)findViewById(R.id.loading);
      Typeface face = Typeface.createFromAsset(getAssets(),
            GhostStoriesConstants.sFont);
      mLoadingTextView.setTypeface(face);
      mLoadText = mLoadingTextView.getText().toString();
   }

   /*
    * (non-Javadoc)
    * @see android.app.Activity#onStart()
    */
   @Override
   protected void onStart() {
      mLoadingTextView.setVisibility(View.GONE);
      mLoadingTextView.setText(mLoadText);
      mLoadCounter = 0;

      findViewById(R.id.new_game).setVisibility(View.VISIBLE);
      findViewById(R.id.continue_game).setVisibility(View.VISIBLE);
      findViewById(R.id.settings).setVisibility(View.VISIBLE);
      super.onStart();
   }

   public void newGameSelected(View pView) {
      Log.d("TitleActivity", "NewGame");

      mLoadingTextView.setVisibility(View.VISIBLE);
      findViewById(R.id.new_game).setVisibility(View.GONE);
      findViewById(R.id.continue_game).setVisibility(View.GONE);
      findViewById(R.id.settings).setVisibility(View.GONE);

      final Future<?> future = mLoadingThread.scheduleAtFixedRate(new Runnable() {
         public void run() {
            String text = mLoadText;
            for(int i = 0; i < mLoadCounter; ++i) {
               text += ".";
            }
            mLoadCounter++;
            if(mLoadCounter > sMaxDots) {
               mLoadCounter = 0;
            }
            final String newLabel = text;
            TitleActivity.this.runOnUiThread(new Runnable() {
               public void run() {
                  mLoadingTextView.setText(newLabel);
               }
            });
         }
      }, 0, 500, TimeUnit.MILLISECONDS);

      //Initialize the game on a background thread
      new AsyncTask<Void, Void, Void>() {
         @Override
         protected Void doInBackground(Void... params) {
            GhostStoriesBitmaps.initBitmaps(getResources());
            GhostStoriesGameManager.getInstance().initializeGame(
                  EDifficulty.INITIATE, TitleActivity.this);
            return null;
         }

         @Override
         protected void onPostExecute(Void result) {
            future.cancel(true);
            //Switch to the game activity
            Intent intent = new Intent(TitleActivity.this, GameScreenActivity.class);
            startActivity(intent);
         }
      }.execute();

   }

   public void continueGameSelected(View pView) {
      Log.d("TitleActivity", "ContinueGame");
   }

   public void settingsSelected(View pView) {
      Log.d("TitleActivity", "Settings");
   }

   /** Maximum number of dots for the loading text **/
   private static int sMaxDots = 3;

   /** Counter used for the loading text **/
   private int mLoadCounter = 0;
   /** The initial loading text **/
   private String mLoadText;
   /** Thread used to update the loading text **/
   private final ScheduledExecutorService mLoadingThread =
         Executors.newSingleThreadScheduledExecutor();
   /** The loading text view **/
   private TextView mLoadingTextView;
}