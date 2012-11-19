package games.ghoststories.utils;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.VillageTileData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGhostAbility;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.enums.EVillageTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

/**
 * Utility class that handles any xml related tasks
 */
public class XmlUtils {

   /**
    * Parses the game board xml file and returns the game boards that are 
    * defined in the xml file.
    * @param pContext The context containing the xml resource
    * @param pId The id of the xml resource to parse
    * @return A mapping of {@link EColor} to the {@link GameBoardData}s
    */
   public static Map<EColor, List<GameBoardData>> parseGameBoardXml(Context pContext, int pId) {
      Map<EColor, List<GameBoardData>> gameBoardMap = new HashMap<EColor, List<GameBoardData>>();
      try {
         XmlResourceParser parser = pContext.getResources().getXml(pId);      
         int eventType = parser.next();
         while (eventType != XmlPullParser.END_DOCUMENT){                        
            switch (eventType){
            case XmlPullParser.START_DOCUMENT:                                
               break;
            case XmlPullParser.START_TAG:
               String name = parser.getName();
               if(name.equalsIgnoreCase("GameBoard")) {
                  EColor color = EColor.valueOf(parser.getAttributeValue(null, "color"));
                  EPlayerAbility ability = EPlayerAbility.valueOf(
                        parser.getAttributeValue(null, "ability"));
                  int leftImageId = parser.getAttributeResourceValue(
                        null, "left_image", -1);
                  int middleImageId = parser.getAttributeResourceValue(
                        null, "middle_image", -1);
                  int rightImageId = parser.getAttributeResourceValue(
                        null, "right_image", -1);
                                   
                  if(!gameBoardMap.containsKey(color)) {
                     gameBoardMap.put(color, new ArrayList<GameBoardData>());
                  }
                  gameBoardMap.get(color).add(new GameBoardData(
                        color, ability, leftImageId, middleImageId, rightImageId));
                  
               }
               break;
            }
            eventType = parser.next();
         }
      } catch (Exception e) {
         Log.e("XmlUtils", "Exception", e);
      }
      return gameBoardMap;
   }
     
   /**
    * Parses the ghost xml file and returns the list of {@link GhostData}s that are
    * defined in the xml file.
    * @param pContext The context containing the xml resource
    * @param pId The id of the xml resource to parse
    * @return A list of {@link GhostData} objects as defined in the xml file
    */
   public static List<GhostData> parseGhostXml(Context pContext, int pId) {
      List<GhostData> ghostList = new ArrayList<GhostData>();

      String ghostName = null;
      int imageId = -1;
      int id = -1;
      EColor color = null;         
      boolean isWuFeng = false;
      Map<EColor, Integer> resistance =
            new HashMap<EColor, Integer>();
      List<EGhostAbility> enterAbilities = new ArrayList<EGhostAbility>();
      List<EGhostAbility> turnAbilities = new ArrayList<EGhostAbility>();
      List<EGhostAbility> exorciseAbilities = new ArrayList<EGhostAbility>();  

      try {
         XmlResourceParser parser = pContext.getResources().getXml(pId);      
         int eventType = parser.next();
         while (eventType != XmlPullParser.END_DOCUMENT){                        
            switch (eventType){
            case XmlPullParser.START_DOCUMENT:                                
               break;
            case XmlPullParser.START_TAG:
               String name = parser.getName();
               if(name.equalsIgnoreCase("Ghost")) {                              
                  ghostName = parser.getAttributeValue(null, "name");
                  id = parser.getAttributeIntValue(null, "id", -1);
                  color = EColor.valueOf(parser.getAttributeValue(null, "color"));                  
                  imageId = parser.getAttributeResourceValue(null, "image", -1);
                  isWuFeng = parser.getAttributeBooleanValue(null, "isWuFeng", false);
               } else if(name.equalsIgnoreCase("Resistance")) {
                  parseResistance(parser, resistance);
               } else if(name.equalsIgnoreCase("EnterAbility")) {
                  parseAbility(parser, enterAbilities);                     
               } else if(name.equalsIgnoreCase("TurnAbility")) {
                  parseAbility(parser, turnAbilities);
               } else if(name.equalsIgnoreCase("ExorciseAbility")) {
                  parseAbility(parser, exorciseAbilities);
               }         

               break;
            case XmlPullParser.END_TAG:
               if(parser.getName().equalsIgnoreCase("Ghost")) {
                  //Create the ghost and add it to the list of ghosts
                  GhostData ghost = new GhostData(ghostName, id, color, 
                        imageId, resistance, enterAbilities, turnAbilities, 
                        exorciseAbilities, isWuFeng);
                  ghostList.add(ghost);

                  ghostName = null;
                  id = -1;
                  color = null;
                  imageId = -1;
                  resistance.clear();
                  enterAbilities.clear();
                  turnAbilities.clear();
                  exorciseAbilities.clear();
               }           
               break;
            }
            eventType = parser.next();
         }
      } catch (Exception e) {
         Log.e("XmlUtils", "Exception", e);
      }
      return ghostList;
   }
   
   /**
    * Parses the village xml file and returns the list of {@link GhostData}s that are
    * defined in the xml file.
    * @param pContext The context containing the xml resource
    * @param pId The id of the xml resource to parse
    * @return A list of {@link GhostData} objects as defined in the xml file
    */
   public static List<VillageTileData> parseVillageXml(Context pContext, int pId) {
      List<VillageTileData> villageTiles = new ArrayList<VillageTileData>();
      try {
         XmlResourceParser parser = pContext.getResources().getXml(pId);      
         int eventType = parser.next();
         while (eventType != XmlPullParser.END_DOCUMENT){                        
            switch (eventType){
            case XmlPullParser.START_DOCUMENT:                                
               break;
            case XmlPullParser.START_TAG:
               String name = parser.getName();
               if(name.equalsIgnoreCase("Tile")) {
                  String tileName = parser.getAttributeValue(null, "name");
                  EVillageTile tileType = EVillageTile.valueOf(
                        parser.getAttributeValue(null, "type"));
                  int activeImageId = parser.getAttributeResourceValue(
                        null, "active_image", -1);
                  int hauntedImageId = parser.getAttributeResourceValue(
                        null, "haunted_image", -1);
                  villageTiles.add(new VillageTileData(tileName, tileType,
                        activeImageId, hauntedImageId));                                                                     
               }
               break;
            }
            eventType = parser.next();
         }
      } catch (Exception e) {
         Log.e("XmlUtils", "Exception", e);
      }
      return villageTiles;
   }

   /**
    * Parses the ghost resistance into the provided map
    * @param pParser The parser to use
    * @param pResistanceMap The map to populate with the resistance data
    * @throws IOException
    * @throws XmlPullParserException
    */   
   private static void parseResistance(XmlResourceParser pParser,
         Map<EColor, Integer> pResistanceMap) throws IOException, XmlPullParserException {
      int eventType = pParser.next();
      boolean done = false;
      while(eventType != XmlPullParser.END_DOCUMENT && !done) { 
         switch(eventType) {
         case XmlPullParser.START_TAG:
            EColor color = EColor.valueOf(pParser.getName().toUpperCase());
            String resValueStr = pParser.nextText();
            if(!resValueStr.isEmpty()) {
               Integer value = Integer.parseInt(resValueStr);
               pResistanceMap.put(color, value);
            }
            break;
         case XmlPullParser.END_TAG:
            if(pParser.getName().equalsIgnoreCase("Resistance")) {
               return;
            }
            break;
         }
         eventType = pParser.next();
      }      
   }

   /**
    * Parse the ghost ability into the provided list
    * @param pParser The parser to use
    * @param pAbilityList The list to populate with the ability data
    * @throws IOException
    * @throws XmlPullParserException
    */
   private static void parseAbility(XmlResourceParser pParser,
         List<EGhostAbility> pAbilityList) throws IOException, XmlPullParserException {
      String value = pParser.nextText();
      if(value != null && !value.trim().isEmpty()) {
         pAbilityList.add(EGhostAbility.valueOf(value));
      }
   }   
}
