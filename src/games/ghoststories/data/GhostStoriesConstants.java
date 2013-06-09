package games.ghoststories.data;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Holds constants used by the game
 */
public abstract class GhostStoriesConstants {
   /** Random number generator **/
   public static final Random sRandom = new SecureRandom();
   
   /** Font to use for labels **/
   public static final String sFont = "fonts/samurai.ttf";
}
