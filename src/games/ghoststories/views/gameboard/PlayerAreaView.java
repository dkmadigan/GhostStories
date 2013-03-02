package games.ghoststories.views.gameboard;

import com.drawable.shapes.GradientRectangle;

import games.ghoststories.data.GameBoardData;
import games.ghoststories.enums.EColor;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * View representing a single player area. A player area is composed of the
 * player board and the player token area.
 */
public class PlayerAreaView extends LinearLayout {
   
   /**
    * Constructor
    * @param context
    */
   public PlayerAreaView(Context context) {
      super(context);      
      initialize();
   }
   
   /**
    * Constructor
    * @param context
    * @param attrs
    */
   public PlayerAreaView(Context context, AttributeSet attrs) {
      super(context, attrs);
      initialize();
   }
   
   /**
    * Constructor
    * @param context
    * @param attrs
    * @param defStyle
    */
   public PlayerAreaView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      initialize();
   } 
      
   /**
    * Sets the game board data for this player. Used to draw the correct 
    * background color based on the player color.
    * @param pGameBoardData The game board data
    */
   public void setGameBoardData(GameBoardData pGameBoardData) {
      mGameBoardData = pGameBoardData;  
      EColor color = mGameBoardData.getColor();     
      int lightColor = color.getLightColor();
      int darkColor = color.getDarkColor();
      setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
            Color.argb(100, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
            Color.argb(100, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
            Color.BLACK));
      
      GameUtils.invalidateView(this);
   }
   
   /**
    * Initialize the view
    */
   private void initialize() {
      setWillNotDraw(false);          
      if(isInEditMode()) {
         //Cheap hack to test layouts in editor mode.                
         if(sInstanceCount+1 < EColor.values().length) {
            EColor color = EColor.values()[sInstanceCount+1];
            int lightColor = color.getLightColor();
            int darkColor = color.getDarkColor();
            setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
                  Color.argb(100, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
                  Color.argb(100, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
                  Color.BLACK));
            sInstanceCount++; 
         }
      }      
   }
  
   /** Used to debug in editor mode **/
   private static int sInstanceCount = 0;
   
   /** The game board data associated with this view **/
   private GameBoardData mGameBoardData = null;
  
}
