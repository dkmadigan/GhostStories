package games.ghoststories.views.aux_area;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.interfaces.IGamePhaseListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.common.BuddhaTokenView;
import games.ghoststories.views.common.QiTokenView;
import games.ghoststories.views.common.TaoTokenView;
import games.ghoststories.views.common.YinYangTokenView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.drawable.shapes.GradientRectangle;

/**
 * High level view that shows all of the info for a player. This includes:
 * <li>Qi
 * <li>Tao Tokens
 * <li>Buddhas
 * <li>Yin Yang 
 */
public class PlayerInfoView extends LinearLayout implements IGamePhaseListener {

   /**
    * Constructor
    * @param pContext
    */
   public PlayerInfoView(Context pContext) {
      super(pContext);
      initialize();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public PlayerInfoView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      initialize();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public PlayerInfoView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      initialize();
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGamePhaseListener#gamePhaseUpdated(games.ghoststories.enums.EGamePhase)
    */
   public void gamePhaseUpdated(EGamePhase pGamePhase) {  
      //When it is a new players turn invalidate the view and redraw
      GameUtils.invalidateView(this);
   }
   
   /**
    * Sets the data used to populate the contents of the views contained within
    * the player info area.
    * @param pPlayerData The data model
    */
   public void setPlayerData(PlayerData pPlayerData) {
      mPlayerData = pPlayerData;
            
      EColor color = mPlayerData.getColor();
      int lightColor = color.getLightColor();
      int darkColor = color.getDarkColor();
      setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
            Color.argb(125, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
            Color.argb(125, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
            sInstanceCount == 2 ? Color.WHITE : Color.BLACK));      
      //Update the different token views in this player area      
      BuddhaTokenView buddhaTokenView = (BuddhaTokenView)findViewById(R.id.buddha_tokens);
      if(buddhaTokenView != null) {
         buddhaTokenView.setData(mPlayerData);
      }
      
      QiTokenView qiTokenView = (QiTokenView)findViewById(R.id.qi_tokens);
      if(qiTokenView != null) {
         qiTokenView.setData(mPlayerData);
      }
      
      for(EColor c : EColor.values()) {
         TaoTokenView taoTokenView = (TaoTokenView)findViewById(c.getTokenId());
         taoTokenView.setData(mPlayerData);
      }
            
      YinYangTokenView yinYangTokenView = (YinYangTokenView)findViewById(R.id.yin_yang);
      if(yinYangTokenView != null) {
         yinYangTokenView.setData(mPlayerData);
      }
        
      GameUtils.invalidateView(this);
   }

   /*
    * (non-Javadoc)
    * @see android.widget.LinearLayout#onDraw(android.graphics.Canvas)
    */
   @Override
   protected void onDraw(Canvas pCanvas) {
      super.onDraw(pCanvas);

      if(!isInEditMode()) {
         //Highlight the player data if it is their turn
         GradientRectangle bg = ((GradientRectangle)getBackground());
         if(mPlayerData != null && mPlayerData.getColor() == 
               GhostStoriesGameManager.getInstance().getCurrentPlayerData().getColor()) {
            bg.setBorderColor(Color.WHITE);
         } else {           
            bg.setBorderColor(Color.BLACK);
         }
      }
   }
   
   /**
    * Initialize the view
    */
   private void initialize() {  
      if(!isInEditMode()) {
         GhostStoriesGameManager.getInstance().addGamePhaseListener(this);
      } else {
         //Cheap hack to test layouts in editor mode.         
         sInstanceCount = sInstanceCount % EColor.getPlayerColors().size();
         if(sInstanceCount < EColor.getPlayerColors().size()) {
            EColor color = EColor.getPlayerColors().toArray(new EColor[0])[sInstanceCount];
            int lightColor = color.getLightColor();
            int darkColor = color.getDarkColor();
            setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
                  //color.getLightColor(), color.getDarkColor(), 25));
                  Color.argb(125, Color.red(lightColor), Color.green(lightColor), Color.blue(lightColor)),
                  Color.argb(125, Color.red(darkColor), Color.green(darkColor), Color.blue(darkColor)), 25,
                  sInstanceCount == 0 ? Color.WHITE : Color.BLACK));    
            if(sInstanceCount == 3) {
               //setVisibility(View.GONE);
            }
         }
         sInstanceCount++;
      }
   }

   /** Debug field used to test layout in editor mode **/
   private static int sInstanceCount = 0;
   
   /** The data model **/
   private PlayerData mPlayerData = null;
}
