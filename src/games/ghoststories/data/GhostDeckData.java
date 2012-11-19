package games.ghoststories.data;

import games.ghoststories.enums.EDifficulty;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Data representation of the current deck of ghost cards. Upon creation,
 * the Ghost Deck is built based on the passed in {@link EDifficulty} using the
 * contents of the {@link #sGhostXmlFile} and assets/xml/wufeng.xml file
 * in order to build the deck.
 * <p>The deck is built as follows for each of the different difficulty levels:
 * <li>{@link EDifficulty#INITIATE} : 45 ghosts, 1 incarnation, 10 ghosts
 * <li>{@link EDifficulty#NORMAL} : 45 ghosts, 1 incarnation, 10 ghosts 
 * (same as Initiate)
 * <li>{@link EDifficulty#NIGHTMARE} : 10 ghosts, 1 incarnation, 10 ghosts, 
 * 1 incarnation, 10 ghosts, 1 incarnation, 10 ghosts, 1 incarnation, rest of 
 * the ghost deck
 * <li>{@linke EDifficulty#HELL} : 10 ghosts, 1 incarnation, 10 ghosts, 
 * 1 incarnation, 10 ghosts, 1 incarnation, 10 ghosts, 1 incarnation, rest of 
 * the ghost deck (Same as Nightmare)
 */
public class GhostDeckData {
   /**
    * Constructor
    * @param pDifficulty The difficulty used to build the deck
    */
   public GhostDeckData(EDifficulty pDifficulty, List<GhostData> pGhosts, 
         List<GhostData> pIncarnations) {       

      //Shuffle the lists
      Collections.shuffle(pGhosts);
      Collections.shuffle(pIncarnations);

      //Build the final ghost deck based on the current difficulty
      switch(pDifficulty) {
      case INITIATE:
      case NORMAL:
         mGhostDeck = new ArrayDeque<GhostData>(pGhosts.size() + 1); //1 wu feng
         mGhostDeck.addAll(pGhosts.subList(0, 45));
         mGhostDeck.add(pIncarnations.get(0));
         mGhostDeck.addAll(pGhosts.subList(45, pGhosts.size()));
         break;
      case NIGHTMARE:
      case HELL:
         mGhostDeck = new ArrayDeque<GhostData>(pGhosts.size() + 4); //4 wu feng
         mGhostDeck.addAll(pGhosts.subList(0, 10));
         mGhostDeck.add(pIncarnations.get(0));
         mGhostDeck.addAll(pGhosts.subList(10, 20));
         mGhostDeck.add(pIncarnations.get(1));
         mGhostDeck.addAll(pGhosts.subList(20, 30));
         mGhostDeck.add(pIncarnations.get(2));
         mGhostDeck.addAll(pGhosts.subList(30, 40));
         mGhostDeck.add(pIncarnations.get(3));
         mGhostDeck.addAll(pGhosts.subList(40, pGhosts.size()));
         break;
      default:
         mGhostDeck = new ArrayDeque<GhostData>();
         break;
      }
   }   
   
   /**
    * Add a listener for updates to the ghost deck
    * @param pListener The listener
    */
   public void addGhostDeckListener(IGhostDeckListener pListener) {
      mListeners.add(pListener);
   }
   
   /**
    * Adds a card to the top of the deck.
    * @param pGhostData The data of the card to add
    */
   public void addTopCard(GhostData pGhostData) {
      mGhostDeck.push(pGhostData);
      notifyListeners();
   }
   
   /**
    * Sets the status of the top card to flipped
    */
   public void flipTopCard() {
      GhostData data = mGhostDeck.peek();
      if(data != null) {
         data.setIsFlipped(!data.isFlipped());         
         notifyListeners();
      }
   }

   /**
    * @return The number of ghosts remaining in the deck
    */
   public int getNumGhosts() {
      return mGhostDeck.size();
   }

   /**
    * @return The current top card of the deck
    */
   public GhostData getTopCard() {
      return mGhostDeck.peek();
   }   
   
   /**
    * Removes a listener from the listener list
    * @param pListener The listener to remove
    */
   public void removeGhostDeckListener(IGhostDeckListener pListener) {
      mListeners.remove(pListener);
   }

   /**
    * @return The new top card of the deck
    */
   public GhostData removeTopCard() {
      GhostData topCard = mGhostDeck.pop();
      notifyListeners();
      return topCard;
   }
   
   /**
    * Sets the dragging state of the top card
    * @param pDragging 
    */
   public void setTopCardDragging(boolean pDragging) {
      GhostData topCard = mGhostDeck.peek();
      topCard.setIsDragging(pDragging);
      notifyListeners();      
   }

   /**
    * Notify listeners that the ghost deck has been updated
    */
   private void notifyListeners() {
      for(IGhostDeckListener listener : mListeners) {
         listener.ghostDeckUpdated();
      }
   }
   
   /** The ghost deck **/
   private final Deque<GhostData> mGhostDeck;
   
   /** The set of listeners for ghost deck updates **/
   private Set<IGhostDeckListener> mListeners = 
         new HashSet<IGhostDeckListener>();
}
