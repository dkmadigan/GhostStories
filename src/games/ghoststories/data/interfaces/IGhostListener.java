package games.ghoststories.data.interfaces;

import games.ghoststories.enums.EHaunterLocation;

public interface IGhostListener {
   public void haunterUpdated(EHaunterLocation pOldLocation, 
         EHaunterLocation pNewLocation, Runnable pRunnable);
}
