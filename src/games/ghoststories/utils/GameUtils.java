package games.ghoststories.utils;

import android.os.Looper;

public class GameUtils {
   public static boolean isUIThread() {
      return (Looper.getMainLooper() == Looper.myLooper());
   }
}
