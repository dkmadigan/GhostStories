package games.ghoststories.views.aux_area;

import com.drawable.shapes.GradientRectangle;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
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
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayerInfoView extends LinearLayout implements IGamePhaseListener {

   public PlayerInfoView(Context pContext) {
      super(pContext);
      initialize();
   }

   public PlayerInfoView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      initialize();
   }

   public PlayerInfoView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      initialize();
   }
   
   public void gamePhaseUpdated(EGamePhase pGamePhase) {  
      //When it is a new players turn invalidate the view and redraw
      GameUtils.invalidateView(this);
   }
   
   public void setPlayerData(PlayerData pPlayerData) {
      mPlayerData = pPlayerData;
            
      EColor color = mPlayerData.getColor();
      int lightColor = color.getLightColor();
      int darkColor = color.getDarkColor();
      setBackground(new GradientRectangle(Orientation.TOP_BOTTOM, 
            //color.getLightColor(), color.getDarkColor(), 25));
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
      
      mPaint.setColor(color.getColor());      
      GameUtils.invalidateView(this);
   }

   @Override
   protected void onDraw(Canvas pCanvas) {
      super.onDraw(pCanvas);
      
      if(mRect == null) {
         mRect = new RectF(0, 0, getWidth(), getHeight());
      }

      if(isInEditMode()) {    

      } else {
         //Highlight the player data if it is their turn
         GradientRectangle bg = ((GradientRectangle)getBackground());
         if(mPlayerData != null && mPlayerData.getColor() == 
               GhostStoriesGameManager.getInstance().getCurrentPlayerData().getColor()) {
            //pCanvas.drawRoundRect(mRect, 15, 15, mPaint);
            bg.setBorderColor(Color.WHITE);
         } else {           
            bg.setBorderColor(Color.BLACK);
         }
      }
   }
   
   private void initialize() {
      mPaint.setStyle(Style.STROKE);
      mPaint.setStrokeWidth(6.0f);
      mPaint.setAntiAlias(true);
      mPaint.setDither(true);
      mPaint.setStrokeCap(Paint.Cap.ROUND);
      mPaint.setStrokeJoin(Paint.Join.ROUND);  
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
   
   private Paint mPaint = new Paint();
   private PlayerData mPlayerData = null;
   private RectF mRect = null;
   private static int sInstanceCount = 0;
}
