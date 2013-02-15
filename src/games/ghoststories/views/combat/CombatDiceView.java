package games.ghoststories.views.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostStoriesConstants;
import games.ghoststories.enums.EDiceSide;
import games.ghoststories.enums.EDragItem;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import com.interfaces.IDraggable;
import com.views.ToggledImageView;

public class CombatDiceView extends ToggledImageView implements IDraggable<DragData> {

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

   public void startDiceAnimation(Runnable pRunnable) {      
      if(!mAnimating) {
         mAnimating = true;
         mAnimationCount = 0;
         animateDice();
         mHandler.sendMessageDelayed(Message.obtain(mHandler, 0, 0), 100);
         mRunnable = pRunnable;
      }       
   }

   private void animateDice() {
      animate().setDuration(200).rotationBy(20*mFactor).withEndAction(mEndAction);        
   }

   public void cancelDiceAnimation() {
      mAnimating = false;            
      animate().cancel();
   }
   
   public DragData getDragData() {
      return new DragData(EDragItem.COMBAT_DICE, mDiceSide, this);
   }

   private Runnable mEndAction = new Runnable() {
      public void run() {
         mFactor *= -1;
         mAnimationCount++;
         if(mAnimationCount >= sMaxAnimationCount) {
            cancelDiceAnimation();
            if(mRunnable != null) {
               mRunnable.run();
            }
         } else {
            animateDice();   
         }         
      }
   };

   private int mFactor = 1;
   private boolean mAnimating = false;
   protected static final List<EDiceSide> sDiceSides = 
         Arrays.asList(EDiceSide.values());
   
   protected int getNextDiceDrawable() {
      mDiceSide = sDiceSides.get(GhostStoriesConstants.sRandom.nextInt(
            sDiceSides.size()));
      return mDiceSide.getDiceDrawable();
   }
   
   private void init() {      
      int rotation = GhostStoriesConstants.sRandom.nextInt(360);
      setRotation(rotation);
      
      //Pick a random dice color as the starting color          
      setImageResource(getNextDiceDrawable());
   }         

   protected EDiceSide mDiceSide;
   private Handler mHandler = new DiceRollerHandler(this);
   private int mAnimationCount = 0;
   private static int sMaxAnimationCount = 10;
   private Runnable mRunnable;

   static class DiceRollerHandler extends Handler {
      private WeakReference<CombatDiceView> mRef;
      public DiceRollerHandler(CombatDiceView pView) {
         mRef = new WeakReference<CombatDiceView>(pView);
      }

      @Override
      public void handleMessage(Message msg) {  
         CombatDiceView view = mRef.get();
         if(view != null && view.mAnimating) {                   
            view.setImageResource(view.getNextDiceDrawable());                  
            sendMessageDelayed(Message.obtain(this, 0, 0), 100);
         }         
      }
   };
}
