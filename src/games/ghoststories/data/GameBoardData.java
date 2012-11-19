package games.ghoststories.data;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import games.ghoststories.enums.ECardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.EPlayerAbility;

/**
 * Data class representing one of the player game boards
 */
public class GameBoardData {

   /**
    * Constructor
    * @param pColor The color of the game board
    * @param pAbility The player ability associated with this game board
    * @param pLeftImageId The image resource id to use for the left space
    * @param pMiddleImageId The image resource id to use for the middle space
    * @param pRightImageId The image resource id to use for the right space
    */
   public GameBoardData(EColor pColor, EPlayerAbility pAbility, int pLeftImageId,
         int pMiddleImageId, int pRightImageId) {
      mColor = pColor;
      mAbility = pAbility;
      mEmptyImageIds.put(ECardLocation.LEFT, pLeftImageId);
      mEmptyImageIds.put(ECardLocation.MIDDLE, pMiddleImageId);
      mEmptyImageIds.put(ECardLocation.RIGHT, pRightImageId);     
   }   
   
   /**
    * Adds a listener for updates to this game board
    * @param pListener The listener to add
    */
   public void addGameBoardListener(IGameBoardListener pListener) {
      mListeners.add(pListener);
   }
   
   /**
    * Adds the ghost to this board at the given location
    * @param pGhost
    * @param pLocation
    */
   public void addGhost(GhostData pGhost, ECardLocation pLocation) {
      mGhosts.put(pLocation, pGhost);
      notifyListeners();
   }
   
   /**
    * @return The ability of this game board
    */
   public EPlayerAbility getAbility() {
      return mAbility;
   }
   
   /**
    * @return The color of this game board
    */
   public EColor getColor() {
      return mColor;
   }
   
   /**
    * @param pCardLocation The card location to get the ghost data for
    * @return The data for the ghost at the specified card location or null
    * if no ghost currently at the given location
    */
   public GhostData getGhostData(ECardLocation pCardLocation) {
      return mGhosts.get(pCardLocation);
   }
   
   /**
    * @param pCardLocation The card location to get the empty id for
    * @return The empty image resource id
    */
   public int getEmptyImageId(ECardLocation pCardLocation) {
      return mEmptyImageIds.get(pCardLocation);
   }
   
   /**
    * @param pCardLocation The card location to get the id for
    * @return The image id for the specified location
    */
   public int getImageId(ECardLocation pCardLocation) {
      GhostData data = mGhosts.get(pCardLocation);
      if(data == null) {
         return mEmptyImageIds.get(pCardLocation);
      } else {
         return data.getImageId();
      }           
   }
   
   /**
    * @return The location of this game board
    */
   public EBoardLocation getLocation() {
      return mLocation;
   }
   
   /**
    * Removes a listener for updates to this game board
    * @param pListener The listener to remove
    */
   public void removeGameBoardListener(IGameBoardListener pListener) {
      mListeners.remove(pListener);
   }
   
   /**
    * Removes the ghost from the given card location
    * @param pLocation The location to remove the card from
    */
   public void removeGhost(ECardLocation pLocation) {
      mGhosts.remove(pLocation);
      notifyListeners();
   }
   
   /**
    * @param pLocation The location of this game board
    */
   public void setLocation(EBoardLocation pLocation) {
      mLocation = pLocation;
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("GameBoard:");
      sb.append(" color = ").append(mColor);
      sb.append(" ability = ").append(mAbility);
      for(ECardLocation loc : ECardLocation.values()) {
         if(mGhosts.containsKey(loc)) {
            sb.append(" ").append(loc.toString()).append(" = ").append(
                  mGhosts.get(loc).toString());
         } else {
            sb.append(" ").append(loc.toString()).append(" = ").append(
                  mEmptyImageIds.get(loc));
         }
      }      
      return sb.toString();
   }
   
   /**
    * Notify listeners that the ghost deck has been updated
    */
   private void notifyListeners() {
      for(IGameBoardListener listener : mListeners) {
         listener.gameBoardUpdated();
      }
   }
   
   /** The player ability associated with this game board **/
   private final EPlayerAbility mAbility;
   /** The color of the game board **/
   private final EColor mColor;
   /** The image resource ids to use for this board when no card is present **/
   private final Map<ECardLocation, Integer> mEmptyImageIds = 
         new EnumMap<ECardLocation, Integer>(ECardLocation.class);   
   /** The card on the board **/
   private final Map<ECardLocation, GhostData> mGhosts = 
         new EnumMap<ECardLocation, GhostData>(ECardLocation.class);
   /** The location of the game board **/
   private EBoardLocation mLocation;
   /** The set of listeners for ghost deck updates **/
   private Set<IGameBoardListener> mListeners = 
         new HashSet<IGameBoardListener>();
}
