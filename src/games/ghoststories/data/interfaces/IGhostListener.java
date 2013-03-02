package games.ghoststories.data.interfaces;

import games.ghoststories.enums.EHaunterLocation;

/**
 * Listener interface triggered when a ghost has been updated.
 */
public interface IGhostListener {
   /**
    * Called when the ghost has been killed
    */
   public void ghostKilled();
   
   /**
    * Called when the haunter position for this ghost has been updated
    * @param pOldLocation The old location of the haunter
    * @param pNewLocation The new location of the haunter
    * @param pRunnable Runnable to execute when the ghost update is complete
    */
   public void haunterUpdated(EHaunterLocation pOldLocation, 
         EHaunterLocation pNewLocation, Runnable pRunnable);
   
   /**
    * Called when the resistance of a ghost is updated during combat
    */
   public void resistanceUpdated();
}
