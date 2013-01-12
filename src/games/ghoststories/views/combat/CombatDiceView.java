package games.ghoststories.views.combat;

import games.ghoststories.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CombatDiceView extends ImageView {

   public CombatDiceView(Context pContext) {
      super(pContext);
      init();
   }

   public CombatDiceView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   public CombatDiceView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }

   public void startDiceAnimation() {
      if(!mAnimating) {
         mAnimating = true;
         mAnimationCount = 0;
         animateDice();
         mHandler.sendMessageDelayed(Message.obtain(mHandler, 0, 0), 100);
      }
   }

   private void animateDice() {
      animate().setDuration(200).rotationBy(20*mFactor).withEndAction(mEndAction);        
   }

   public void cancelDiceAnimation() {
      mAnimating = false;            
      animate().cancel();
   }

   private Runnable mEndAction = new Runnable() {
      public void run() {
         mFactor *= -1;
         mAnimationCount++;
         if(mAnimationCount >= sMaxAnimationCount) {
            cancelDiceAnimation();
         } else {
            animateDice();   
         }         
      }
   };

   private int mFactor = 1;
   private boolean mAnimating = false;
   private void init() {
      Random r = new Random();
      int rotation = r.nextInt() % 360;
      setRotation(rotation);
      
      //Pick a random dice color as the starting color
      setImageResource(sDiceResources.get(sRandom.nextInt(sDiceResources.size())));
   }         

   private static Random sRandom = new Random();
   private Handler mHandler = new DiceRollerHandler(this);
   private int mAnimationCount = 0;
   private static int sMaxAnimationCount = 10;

   static class DiceRollerHandler extends Handler {
      private WeakReference<CombatDiceView> mRef;
      public DiceRollerHandler(CombatDiceView pView) {
         mRef = new WeakReference<CombatDiceView>(pView);
      }

      @Override
      public void handleMessage(Message msg) {           
         if(mRef.get() != null && mRef.get().mAnimating) {
            int value = sRandom.nextInt(sDiceResources.size());        
            mRef.get().setImageResource(sDiceResources.get(value));                  
            sendMessageDelayed(Message.obtain(this, 0, 0), 100);
         }         
      }
   };
   
   private static List<Integer> sDiceResources = new ArrayList<Integer>();
   static {
      sDiceResources.add(R.drawable.tao_dice_black);
      sDiceResources.add(R.drawable.tao_dice_blue);
      sDiceResources.add(R.drawable.tao_dice_green);
      sDiceResources.add(R.drawable.tao_dice_red);
      sDiceResources.add(R.drawable.tao_dice_yellow);
      sDiceResources.add(R.drawable.tao_dice_white);
   };
}
