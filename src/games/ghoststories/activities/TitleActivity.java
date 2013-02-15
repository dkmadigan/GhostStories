package games.ghoststories.activities;

import games.ghoststories.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * Activity for the title screen of the game
 */
public class TitleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             
        setContentView(R.layout.title);        
    }
}