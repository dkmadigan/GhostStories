package games.ghoststories.views.gameboard;

import games.ghoststories.data.GameBoardData;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * View representing a single players board. 
 */
//TODO Might be able to remove this
public class PlayerBoardView extends RelativeLayout {
   
   /**
    * Constructor
    * @param pContext
    */
   public PlayerBoardView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public PlayerBoardView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public PlayerBoardView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }   

   /** 
    * Sets the game board data model
    * @param pGameBoardData
    */
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData; 
   }   
  
   /** The data model **/
   private GameBoardData mGameBoardData = null;   
}
