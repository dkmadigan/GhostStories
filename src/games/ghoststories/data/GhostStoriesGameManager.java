package games.ghoststories.data;

import games.ghoststories.R;
import games.ghoststories.enums.EBoardLocation;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDifficulty;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.enums.ETileLocation;
import games.ghoststories.utils.XmlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import android.content.Context;
import android.util.Log;

/**
 * Game manager responsible for managing and maintaining all necessary game
 * state. This state includes:
 * <li>Ghost Card Deck
 * <li>Exorcised Ghosts
 * <li>Player Data
 * <li>Game Board Information
 * <br>
 * 
 * The function {@link #initializeGame()} must be called at the start of the 
 * game to setup all necessary game data.
 */
public class GhostStoriesGameManager {
   /**
    * Holds the static instance of the game manager    
    */
   private static class INSTANCE_HOLDER {
      public static final GhostStoriesGameManager sInstance = 
            new GhostStoriesGameManager();
   }
   
   /**
    * @return The static instance of the {@link GhostStoriesGameManager}
    */
   public static GhostStoriesGameManager getInstance() {
      return INSTANCE_HOLDER.sInstance;
   }
   
   /**
    * Add a listener to receive notifications when the game phase is updated
    * @param pListener
    */
   public void addGamePhaseListener(IGamePhaseListener pListener) {
      mGamePhaseListeners.add(pListener);      
   }
   
   /**
    * Add a listener to receive notifications when the ghost deck is updated
    * @param pListener
    */
   public void addGhostDeckListener(IGhostDeckListener pListener) {
      mGhostDeckListeners.add(pListener);
   }
   
   /**
    * Advances the game to the next game phase. Will update to the next player's
    * turn if currently at the last phase.
    */
   public void advanceGamePhase() {
      switch(mGamePhase) {
      case EYinPhase1A:
         mGamePhase = EGamePhase.EYinPhase1B;
         break;
      case EYinPhase1B:
         mGamePhase = EGamePhase.EYinPhase2;
         break;
      case EYinPhase2:
         mGamePhase = EGamePhase.EYinPhase3;
         break;
      case EYinPhase3:
         mGamePhase = EGamePhase.EYangPhase1;
         break;
      case EYangPhase1:
         mGamePhase = EGamePhase.EYangPhase2;
         break;
      case EYangPhase2:
         mGamePhase = EGamePhase.EYangPhase3;
         break;
      case EYangPhase3:
         mGamePhase = EGamePhase.EYinPhase1A;
         mCurrentPlayerIndex++;
         if(mCurrentPlayerIndex >= mPlayerOrder.size()) {
            mCurrentPlayerIndex = 0;
         }
         break;
      }      
      
      //Notify listeners that the game phase has changed
      for(IGamePhaseListener listener : mGamePhaseListeners) {
         listener.gamePhaseUpdated(mGamePhase);
      }
   }
   
   /**
    * @return The ghost deck data
    */
   public GhostDeckData getGhostDeckData() {
      return mGhostDeck;
   }
   

   /**
    * Called to initialize the game. Sets up all necessary game data before
    * returning. It is recommended to call this on a background thread to avoid
    * locking up the UI thread while the game sets itself up.
    * @param pDifficulty The difficulty of the game to initialize
    */
   public void initializeGame(EDifficulty pDifficulty, Context pContext) {
      mDifficulty = pDifficulty;
      try {
         List<GhostData> ghosts = XmlUtils.parseGhostXml(pContext, R.xml.ghosts);
         List<GhostData> incarnations = XmlUtils.parseGhostXml(pContext, R.xml.wufeng);
         
         //Create the deck of ghost cards
         mGhostDeck = new GhostDeckData(mDifficulty, ghosts, incarnations);                 

         //Initialize the game boards      
         initGameBoards(pContext);      
         
         //Initialize the village tiles
         initVillageTiles(pContext);
         
         //Initialize the players
         initPlayerData();
         setupPlayerOrder();
         
      } catch (Exception pException) {
         Log.e("GhostStoriesGameManager", "Exception",  pException);
      }
   }
   
   /**
    * @return The current phase of the game
    */
   public EGamePhase getCurrentGamePhase() {
      return mGamePhase;
   }
   
   /**
    * Gets the {@link PlayerData} for the player whose turn it currently is
    * @return The {@link PlayerData} for the current player
    */
   public PlayerData getCurrentPlayerData() {
      return mPlayerData.get(mPlayerOrder.get(mCurrentPlayerIndex));
   }
   
   /**
    * Gets the game board data for the passed in {@link EColor}
    * @param pColor The color to get the game board for
    * @return The {@link GameBoardData} for the specified color
    */
   public GameBoardData getGameBoard(EColor pColor) {
      return mGameBoards.get(pColor);
   }
   
   /**
    * Gets the game board at the specified {@link EBoardLocation}
    * @param pLocation The location to get the game board for
    * @return The {@link GameBoardData} at the specified location
    */
   public GameBoardData getGameBoard(EBoardLocation pLocation) {
      GameBoardData data = null;
      for(GameBoardData d : mGameBoards.values()) {
         if(d.getLocation() == pLocation) {
            data = d;
            break;
         }
      }
      return data;
   }
   
   /**
    * @return The map of game boards for the current game
    */
   public Map<EColor, GameBoardData> getGameBoards() {
      return Collections.unmodifiableMap(mGameBoards);
   }
   
   /**
    * Gets the player data for the specified {@link EColor}
    * @param pColor the color of the player data to get
    * @return The player data of the requested color 
    */
   public PlayerData getPlayerData(EColor pColor) {
      return mPlayerData.get(pColor);
   }
   
   /**
    * Gets the village tile at the passed in {@link ETileLocation}
    * @param pLocation The location to get the tile for
    * @return The tile at the requested location
    */
   public VillageTileData getVillageTile(ETileLocation pLocation) {
      return mVillageTiles.get(pLocation);
   }
   
   /**
    * @return The map of village tiles
    */
   public Map<ETileLocation, VillageTileData> getVillageTiles() {
      return Collections.unmodifiableMap(mVillageTiles);
   }
   
   /**
    * Initialize the game boards by parsing the xml of game boards and then
    * randomly choosing a single game board for each player color.
    * @param pContext The context used to initialize the game boards
    */
   private void initGameBoards(Context pContext) throws IOException {
      //Initialize the game boards 
      Map<EColor, List<GameBoardData>> boards = 
            XmlUtils.parseGameBoardXml(pContext, R.xml.gameboards);
      
      //Randomly choose a game board for each color
      Random r = new Random();
      List<GameBoardData> redBoards = boards.get(EColor.RED);
      List<GameBoardData> blueBoards = boards.get(EColor.BLUE);
      List<GameBoardData> greenBoards = boards.get(EColor.GREEN);
      List<GameBoardData> yellowBoards = boards.get(EColor.YELLOW);
      
      if(!redBoards.isEmpty() && !blueBoards.isEmpty() && 
            !greenBoards.isEmpty() && !yellowBoards.isEmpty()) {
         mGameBoards.put(EColor.RED, redBoards.get(r.nextInt(redBoards.size())));
         mGameBoards.put(EColor.BLUE, blueBoards.get(r.nextInt(blueBoards.size())));
         mGameBoards.put(EColor.GREEN, greenBoards.get(r.nextInt(greenBoards.size())));
         mGameBoards.put(EColor.YELLOW, yellowBoards.get(r.nextInt(yellowBoards.size())));
      }
      
      //Randomize the locations of the game boards
      List<EBoardLocation> locs = Arrays.asList(EBoardLocation.values());
      Collections.shuffle(locs);
      mGameBoards.get(EColor.RED).setLocation(locs.get(0));
      mGameBoards.get(EColor.BLUE).setLocation(locs.get(1));
      mGameBoards.get(EColor.GREEN).setLocation(locs.get(2));
      mGameBoards.get(EColor.YELLOW).setLocation(locs.get(3));
   }
   
   /** 
    * Initializes the player data
    */
   private void initPlayerData() {
      mPlayerData.put(EColor.RED, createPlayerData("Red Player", EColor.RED));
      mPlayerData.put(EColor.BLUE, createPlayerData("Blue Player", EColor.BLUE));
      mPlayerData.put(EColor.YELLOW, createPlayerData("Yellow Player", EColor.YELLOW));
      mPlayerData.put(EColor.GREEN, createPlayerData("Green Player", EColor.GREEN));
   }
   
   /**
    * Initialize the village tiles
    * @param pContext The context used to initialize the village tiles
    */
   private void initVillageTiles(Context pContext) {
      //Initialize the village tiles 
      List<VillageTileData> tiles = 
            XmlUtils.parseVillageXml(pContext, R.xml.village);
      //Randomly order the tiles 
      Collections.shuffle(tiles);
      
      //TODO Error check this
      ETileLocation tileLocs[] = ETileLocation.values();
      for(int i = 0; i < tileLocs.length; ++i) {
         ETileLocation loc = tileLocs[i];
         VillageTileData villageTile = tiles.get(i);
         villageTile.setLocation(loc);
         mVillageTiles.put(loc, villageTile);
      }
   }
      
   /**
    * Creates a {@link PlayerData} using the given color and name and based on
    * the currently set difficulty level.
    * @param pName The name of the player to create
    * @param pColor The color of the player
    * @return The created player data
    */
   private PlayerData createPlayerData(String pName, EColor pColor) {
      PlayerData pd = new PlayerData(pName, pColor, mGameBoards.get(pColor));
      
      //Initialize the player data based on the current difficulty
      switch(mDifficulty) {
      case INITIATE:
         pd.setQi(4);
         pd.setNumTauTokens(EColor.BLACK, 1);
         pd.setNumTauTokens(pColor, 1);
         break;
      case NORMAL:
      case NIGHTMARE:
         pd.setQi(3);
         pd.setNumTauTokens(pColor, 1);
         break;      
      case HELL:     
         pd.setQi(3);
         pd.setNumTauTokens(pColor, 1);
         pd.setYinYangAvailable(false);
         break;
      }
            
      return pd;
   }
   
   /**
    * Sets up the order of the player's turns
    */
   private void setupPlayerOrder() {
      mPlayerOrder.add(getGameBoard(EBoardLocation.BOTTOM).getColor());
      mPlayerOrder.add(getGameBoard(EBoardLocation.LEFT).getColor());
      mPlayerOrder.add(getGameBoard(EBoardLocation.TOP).getColor());
      mPlayerOrder.add(getGameBoard(EBoardLocation.RIGHT).getColor());
   }
        
   /**
    * Constructor
    */
   private GhostStoriesGameManager() {      
   }
   
   /** The current difficulty level **/
   private EDifficulty mDifficulty;
   /** The deck of ghost cards **/
   private GhostDeckData mGhostDeck;
   /** The player game boards **/
   private Map<EColor, GameBoardData> mGameBoards = 
         new EnumMap<EColor, GameBoardData>(EColor.class);
   /** The player data **/
   private Map<EColor, PlayerData> mPlayerData = 
         new EnumMap<EColor, PlayerData>(EColor.class);   
   /** The village tiles **/   
   private Map<ETileLocation, VillageTileData> mVillageTiles = 
      new EnumMap<ETileLocation, VillageTileData>(ETileLocation.class);
   
   /** The current game phase **/
   private EGamePhase mGamePhase = EGamePhase.EYinPhase1A;
   /** Listeners for game phase updates **/
   private Set<IGamePhaseListener> mGamePhaseListeners = 
         new CopyOnWriteArraySet<IGamePhaseListener>();
   /** Listeners for ghost deck updates **/
   private Set<IGhostDeckListener> mGhostDeckListeners = 
         new CopyOnWriteArraySet<IGhostDeckListener>();   
   /** The index of the current player **/
   private int mCurrentPlayerIndex = 0;
   /** The order of player turns **/
   private List<EColor> mPlayerOrder = new ArrayList<EColor>();
}
