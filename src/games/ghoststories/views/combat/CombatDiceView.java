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

/**
 * View that represents a single dice that is shown during the combat phase.
 * This dice is "rolled" when the player hits the ROLL button and supports
 * being dragged onto a ghost to attempt to "attack" it.
 */
public class CombatDiceView extends ToggledImageView implements IDraggable<DragData> {

   /**
    * Constructor
    * @param pContext
    */
   public CombatDiceView(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatDiceView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatDiceView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }
   
   /**
    * Cancels the dice rolling animation
    */
   public void cancelDiceAnimation() {
      mAnimating = false;            
      animate().cancel();
   }
   
   /*
    * (non-Javadoc)
    * @see com.interfaces.IDraggable#getDragData()
    */
   public DragData getDragData() {
      return new DragData(EDragItem.COMBAT_DICE, mDiceSide, this);
   }

   /**
    * Called to start the dice rolling animation for this dice
    * @param pRunnable The runnable to execute once the dice is done animating
    */
   public void startDiceAnimation(Runnable pRunnable) {      
      if(!mAnimating) {
         mAnimating = true;
         mAnimationCount = 0;
         animateDice();
         mHandler.sendMessageDelayed(Message.obtain(mHandler, 0, 0), 100);
         mRunnable = pRunnable;
      }       
   }
   
   /**
    * Called during the animation cycle to randomly get the next side
    * of the dice to show.
    * @return The drawable for the next side of the dice to show
    */
   protected int getNextDiceDrawable() {
      mDiceSide = sDiceSides.get(GhostStoriesConstants.sRandom.nextInt(
            sDiceSides.size()));
      return mDiceSide.getDiceDrawable();
   }

   /**
    * Performs one animation cycle on the dice. When this cycle is over will
    * call {@link #mEndAction}.
    */
   private void animateDice() {
      animate().setDuration(200).rotationBy(20*mFactor).withEndAction(mEndAction);        
   }
   
   /**
    * Initialize this view with random orientation and color
    */
   private void init() {      
      int rotation = GhostStoriesConstants.sRandom.nextInt(360);
      setRotation(rotation);
      
      //Pick a random dice color as the starting color          
      setImageResource(getNextDiceDrawable());
   }   
   
   /** List of possible dice sides **/
   protected static final List<EDiceSide> sDiceSides = 
         Arrays.asList(EDiceSide.values());
      
   /** The current face up dice side **/
   protected EDiceSide mDiceSide;
   
   /** Maximum number of animation cycles **/
   private static int sMaxAnimationCount = 10;
   
   /** Whether or not the dice is currently animating **/
   private boolean mAnimating = false;
   /** Number of animation cycles that have run in the current animation **/
   private int mAnimationCount = 0;
   /** Helper used to speed up/slow down the dice rolling rotation animation **/
   private int mFactor = 1;
   /** Handler used to send messages to trigger changing the dice sides **/
   private Handler mHandler = new DiceRollerHandler(this);      
   /** Runnable to trigger when animation completes **/
   private Runnable mRunnable;

   /**
    * Runnable that is triggered after every cycle of the dice rolling animation.
    * If there are still more cycles to run, then does it. If we have hit the
    * end of the animation then cancel the animation and notify the caller
    * via the {@link #mRunnable} runnable.
    */
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
   
   /**
    * Handler used to update the dice image during the animation
    */
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
