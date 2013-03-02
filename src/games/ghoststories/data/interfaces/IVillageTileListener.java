package games.ghoststories.data.interfaces;

import games.ghoststories.data.village.VillageTileData;

/**
 * Listener interface triggered when a village tile data is updated
 * @see VillageTileData#addVillageTileListener(IVillageTileListener)
 */
public interface IVillageTileListener {
   /**
    * Called when the data for a village tile has been updated
    */
   public void villageTileUpdated();
}
