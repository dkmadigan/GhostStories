package games.ghoststories.controllers.combat;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.data.TokenSupplyData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.ECombatPhase;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.views.aux_area.PlayerInfoView;
import games.ghoststories.views.combat.CombatDamageView;
import games.ghoststories.views.combat.CombatDiceView;
import games.ghoststories.views.combat.CombatGhostView;
import games.ghoststories.views.combat.CombatInstructionsView;
import games.ghoststories.views.combat.CombatRollView;
import games.ghoststories.views.combat.CombatView;
import games.ghoststories.views.common.TaoTokenView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.views.NumberedImageView;
import com.views.listeners.DragTouchListener;

/**
 * Controller that handles interactions during on the combat view during
 * the combat phase of the game.
 */
public class CombatAreaController {

   /**
    * Constructor
    * @param pView The combat view
    * @param pPrimaryAttacker The primary attacking player
    * @param pSecondaryAttackers Any secondary attackers
    * @param pGhost1 The first ghost being attacked
    * @param pGhost2 The second ghost being attacked if applicable
    */
   public CombatAreaController(CombatView pView, PlayerData pPrimaryAttacker,
         List<PlayerData> pSecondaryAttackers,
         GhostData pGhost1, GhostData pGhost2) {
      //Save off references to the views that we need
      mCombatView = pView;

      mPlayerViews.add((PlayerInfoView)pView.findViewById(R.id.info_area_1));
      mPlayerViews.add((PlayerInfoView)pView.findViewById(R.id.info_area_2));
      mPlayerViews.add((PlayerInfoView)pView.findViewById(R.id.info_area_3));
      mPlayerViews.add((PlayerInfoView)pView.findViewById(R.id.info_area_4));

      mPrimaryAttacker = pPrimaryAttacker;
      mSecondaryAttackers = pSecondaryAttackers;

      mDiceViews.add((CombatDiceView)pView.findViewById(R.id.dice_1));
      mDiceViews.add((CombatDiceView)pView.findViewById(R.id.dice_2));
      mDiceViews.add((CombatDiceView)pView.findViewById(R.id.dice_3));
      mDiceViews.add((CombatDiceView)pView.findViewById(R.id.extra_dice));

      mRollView = (CombatRollView)pView.findViewById(R.id.attack);
      mCombatInstructions = (CombatInstructionsView)pView.findViewById(R.id.instructions);

      mGhostView1 = (CombatGhostView)pView.findViewById(R.id.ghost1);
      mGhostData1 = pGhost1;
      mGhostView2 = (CombatGhostView)pView.findViewById(R.id.ghost2);
      mGhostData2 = pGhost2;

      mOKView = (ImageView)pView.findViewById(R.id.ok);
      mOKView.setEnabled(false);
      mCloseView = (ImageView)pView.findViewById(R.id.close);

      mCombatPhase = ECombatPhase.ATTACK;
      updateInstructions();
      addListeners();
   }

   /**
    * Dispose the combat area and deregisters all listeners.
    */
   public void dispose() {
      mRollView.setOnClickListener(null);
      for(CombatDiceView cdv : mDiceViews) {
         cdv.setOnTouchListener(null);
         cdv.setOnDragListener(null);
      }
      for(PlayerInfoView piv : mPlayerViews) {
         if(piv.getVisibility() == View.VISIBLE) {
            for(EColor c : EColor.values()) {
               TaoTokenView taoTokenView =
                     (TaoTokenView)piv.findViewById(c.getTokenId());
               taoTokenView.setOnTouchListener(null);
               taoTokenView.setOnDragListener(null);
            }
         }
      }
      if(mGhostView1 != null) {
         mGhostView1.setOnDragListener(null);
         for(CombatDamageView cdv : mGhostView1.getDamageViews()) {
            cdv.setOnTouchListener(null);
         }
      }
      if(mGhostView2 != null) {
         mGhostView2.setOnDragListener(null);
         for(CombatDamageView cdv : mGhostView1.getDamageViews()) {
            cdv.setOnTouchListener(null);
         }
      }
   }

   /**
    * Adds the dice and tao token drag and drop listeners. This gets called
    * after the dice have been rolled to allow the player to drag the dice
    * and tokens onto the ghosts to apply damage.
    */
   private void addDragDropListeners() {
      for(CombatDiceView cdv : mDiceViews) {
         if(cdv.getVisibility() == View.VISIBLE) {
            cdv.setOnTouchListener(new DragTouchListener());
            cdv.setOnDragListener(new DiceDragListener());
         }
      }

      for(PlayerInfoView piv : mPlayerViews) {
         if(piv.getVisibility() == View.VISIBLE) {
            for(EColor c : EColor.values()) {
               TaoTokenView taoTokenView =
                     (TaoTokenView)piv.findViewById(c.getTokenId());
               taoTokenView.setOnTouchListener(
                     new NumberedImageView.DefaultDragTouchListener());
               taoTokenView.setOnDragListener(new TaoTokenDragListener());
            }
         }
      }

      if(mGhostView1 != null) {
         mGhostView1.setOnDragListener(new GhostDragListener(mGhostData1));
         for(CombatDamageView cdv : mGhostView1.getDamageViews()) {
            cdv.setOnTouchListener(new DragTouchListener());
         }
      }
      if(mGhostView2 != null) {
         mGhostView2.setOnDragListener(new GhostDragListener(mGhostData2));
         for(CombatDamageView cdv : mGhostView2.getDamageViews()) {
            cdv.setOnTouchListener(new DragTouchListener());
         }
      }
   }

   /**
    * Adds all necessary listeners to the views of the combat area.
    */
   private void addListeners() {
      mRollView.setOnClickListener(mAttackViewListener);
      mOKView.setOnClickListener(mOKListener);
      mCloseView.setOnClickListener(mCloseListener);
   }

   /**
    * Called at the end of combat before closing the combat dialog. Resolves
    * any damage that was done to a ghost. If enough damage was done to kill
    * the ghost then kill it, else reset it's health back to full.
    * @param pData The ghost to resolve the damage for
    * @return <code>true</code> if the ghost was killed, <code>false</code>
    *         otherwise
    */
   private boolean resolveGhost(GhostData pData) {
      boolean ghostKilled = false;
      if(pData != null) {
         if(pData.getHealth() == 0) {
            //Update the number of tokens for the player based on combat results
            //TODO Ugh
            GhostStoriesGameManager gm = GhostStoriesGameManager.getInstance();
            for(PlayerInfoView piv : mPlayerViews) {
               if(piv.getVisibility() == View.VISIBLE) {
                  PlayerData pd = piv.getPlayerData();
                  TokenSupplyData supply = gm.getTokenSupply();
                  Map<EColor, Integer> tokenMap = new HashMap<EColor, Integer>();
                  for(EColor c : EColor.values()) {
                     TaoTokenView taoTokenView =
                           (TaoTokenView)piv.findViewById(c.getTokenId());
                     int num = taoTokenView.getNumber();
                     int diff = pd.getNumTaoTokens(c) - num;
                     tokenMap.put(c,  num);
                     if(diff > 0) {
                        supply.addTaoTokens(c, diff);
                     }
                  }
                  pd.setTaoTokens(tokenMap);
               }
            }

            //Kill the ghost
            pData.killGhost();
            gm.getGhostGraveyardData().addGhost(pData);
            ghostKilled = true;
         } else {
            pData.resetResistance();
         }
      }
      return ghostKilled;
   }

   /**
    * Update the instructions that are shown based on the current combat phase
    * @see #mCombatPhase
    */
   private void updateInstructions() {
      mCombatInstructions.setText(mCombatPhase.getCombatStringId());
   }

   /**
    * Click listener for the "Roll" button which starts animating the dice roll
    */
   private final OnClickListener mAttackViewListener = new OnClickListener() {
      public void onClick(View pView) {
         for(CombatDiceView dice : mDiceViews) {
            if(dice.getVisibility() == View.VISIBLE) {
               dice.startDiceAnimation(mDiceRollCompleteRunnable);
               mRollView.setEnabled(false);
               mOKView.setEnabled(false);
               mCloseView.setEnabled(false);
            }
         }
      }
   };

   /**
    * Click listener for the close button which closes the combat view. This
    * can only be used before any combat actions are taken. Once combat actions
    * are taken the close button becomes disabled.
    */
   private final OnClickListener mCloseListener = new OnClickListener() {
      public void onClick(View pView) {
         CombatView combatView = (CombatView)pView.getParent();
         combatView.dispose();
         ViewGroup vg = (ViewGroup)(combatView.getParent());
         vg.removeView(combatView);
      }
   };

   /**
    * Click listener for the OK button. This button will advance the game
    * phase, resolve any combat that occurred and close the combat view.
    */
   private final OnClickListener mOKListener = new OnClickListener() {
      public void onClick(View pView) {
         //Advance to the resolution phase
         GhostStoriesGameManager.getInstance().advanceGamePhase();

         //Destroy the view
         mCombatView.dispose();
         ViewGroup vg = (ViewGroup)(mCombatView.getParent());
         vg.removeView(mCombatView);

         //If the health of the ghosts are 0 then they become dead,
         //else they get reset to full health
         boolean ghost1Killed = resolveGhost(mGhostData1);
         boolean ghost2Killed = resolveGhost(mGhostData2);
         if(!ghost1Killed && !ghost2Killed) {
            //Nothing to resolve so advance game phase
            GhostStoriesGameManager.getInstance().advanceGamePhase();
         }
      }
   };

   /**
    * Runnable that gets called when the dice have finished their rolling
    * animation. If the player has the {@link EPlayerAbility#GODS_FAVORITE}
    * ability, then allows another roll. Otherwise the drag and drop listeners
    * for the dice and tokens are added to allow the player to continue with
    * the combat.
    */
   private final Runnable mDiceRollCompleteRunnable = new Runnable() {
      public void run() {
         PlayerData pd =
               GhostStoriesGameManager.getInstance().getCurrentPlayerData();
         if(mCombatPhase == ECombatPhase.ATTACK &&
               pd.getAbility() ==  EPlayerAbility.GODS_FAVORITE &&
               pd.getAbilityActive()) {
            mRollView.setEnabled(true);
            mCombatPhase = ECombatPhase.ATTACK_2;
            updateInstructions();
         } else {
            mOKView.setEnabled(true);
            mCombatPhase = ECombatPhase.RESOLUTION;
            updateInstructions();
            addDragDropListeners();
         }
      }
   };

   /** The current combat phase **/
   private ECombatPhase mCombatPhase;

   /** The overall combat view **/
   private final CombatView mCombatView;
   /** The combat instructions view **/
   private final CombatInstructionsView mCombatInstructions;
   /** The views for the dice **/
   private final List<CombatDiceView> mDiceViews = new ArrayList<CombatDiceView>();
   /** The views for the player info areas **/
   private final List<PlayerInfoView> mPlayerViews = new ArrayList<PlayerInfoView>();
   /** The ROLL button view **/
   private final CombatRollView mRollView;

   /** Reference to the primary attacker data **/
   private final PlayerData mPrimaryAttacker;
   /** Reference to the secondary attackers data **/
   private final List<PlayerData> mSecondaryAttackers;

   /** The view for the ghost being attacked **/
   private final CombatGhostView mGhostView1;
   /** The data for the ghost being attacked **/
   private final GhostData mGhostData1;

   /** The view for the second ghost being attacked or null if not applicable **/
   private final CombatGhostView mGhostView2;
   /** The data for the second ghost being attacked or null if not applicable **/
   private final GhostData mGhostData2;

   /** The close button **/
   private final ImageView mCloseView;
   /** The ok button **/
   private final ImageView mOKView;
}
