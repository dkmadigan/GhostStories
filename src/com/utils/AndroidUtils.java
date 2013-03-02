package com.utils;

import android.os.Looper;

/**
 * Generic utility methods related to the android software kit
 */
public abstract class AndroidUtils {

   /**
    * @return Whether or not the current thread is the UI thread
    */
   public static boolean isUIThread() {
      return (Looper.getMainLooper() == Looper.myLooper());
   }
}
