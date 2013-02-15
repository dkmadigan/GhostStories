package games.ghoststories.controllers.combat;

import games.ghoststories.R;
import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.PlayerData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.ECombatPhase;
import games.ghoststories.enums.EDiceSide;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.enums.EPlayerAbility;
import games.ghoststories.views.aux_area.PlayerInfoView;
import games.ghoststories.views.combat.CombatDiceView;
import games.ghoststories.views.combat.CombatGhostView;
import games.ghoststories.views.combat.CombatInstructionsView;
import games.ghoststories.views.combat.CombatRollView;
import games.ghoststories.views.combat.CombatView;
import games.ghoststories.views.common.TaoTokenView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.ImageView;

import com.views.listeners.DragTouchListener;

/**
 * Controller that handles interactions during on the combat view during 
 * the combat phase of the game.
 */
//TODO Clean up and comment this class when done
public class CombatAreaController {

   public CombatAreaController(CombatView pView, PlayerData pPrimaryAttacker,
         List<PlayerData> pSecondaryAttackers,
         GhostData pGhost1, GhostData pGhost2) {
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
      mOKView.setOnClickListener(new OnClickListener() {         
         public void onClick(View v) {
            //Advance to the resolution phase
            GhostStoriesGameManager.getInstance().advanceGamePhase();
            
            //Destroy the view
            mCombatView.destroy();
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
      });
      mCloseView = (ImageView)pView.findViewById(R.id.close);
      
      mCombatPhase = ECombatPhase.ATTACK;
      updateInstructions();
      
      addListeners();
   }    
   
   private boolean resolveGhost(GhostData pData) {
      boolean ghostKilled = false;
      if(pData != null) {
         if(pData.getHealth() == 0) {
            pData.killGhost();
            GhostStoriesGameManager.getInstance().getGhostGraveyardData().addGhost(
                  pData);
            ghostKilled = true;
         } else {
            pData.resetResistance();
         }
      }
      return ghostKilled;
   }
   
   private void addListeners() {
      mRollView.setOnClickListener(mAttackViewListener);
   }
   
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
               TaoTokenView tauTokenView = 
                     (TaoTokenView)piv.findViewById(c.getTokenId());
               tauTokenView.setOnTouchListener(new DragTouchListener());
               tauTokenView.setOnDragListener(new TaoTokenDragListener());
            }     
         }
      }
            
      if(mGhostView1 != null) {
         mGhostView1.setOnDragListener(new GhostDragListener(mGhostData1));
      }
      if(mGhostView2 != null) {
         mGhostView2.setOnDragListener(new GhostDragListener(mGhostData2));
      }
   }
   
   public void destroy() {
      mRollView.setOnClickListener(null);
      for(CombatDiceView cdv : mDiceViews) {
         cdv.setOnTouchListener(null);
         cdv.setOnDragListener(null);
      }
      for(PlayerInfoView piv : mPlayerViews) {
         if(piv.getVisibility() == View.VISIBLE) {
            for(EColor c : EColor.values()) {
               TaoTokenView tauTokenView = 
                     (TaoTokenView)piv.findViewById(c.getTokenId());
               tauTokenView.setOnTouchListener(null);
               tauTokenView.setOnDragListener(null);
            }     
         }
      }
      if(mGhostView1 != null) {
         mGhostView1.setOnDragListener(null);
      }
      if(mGhostView2 != null) {
         mGhostView2.setOnDragListener(null);
      }
   }
   
   private void updateInstructions() {
      mCombatInstructions.setText(mCombatPhase.getCombatStringId());
   }
   
   private final OnClickListener mAttackViewListener = new OnClickListener() {      
      public void onClick(View v) {
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
        
   private Runnable mDiceRollCompleteRunnable = new Runnable() {
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
      
   private ECombatPhase mCombatPhase;
   private final CombatView mCombatView;
   private final List<PlayerInfoView> mPlayerViews = new ArrayList<PlayerInfoView>();
   private final PlayerData mPrimaryAttacker;
   private final List<PlayerData> mSecondaryAttackers;
   private final List<CombatDiceView> mDiceViews = new ArrayList<CombatDiceView>();
   private final CombatRollView mRollView;
   private final CombatInstructionsView mCombatInstructions;
   private final CombatGhostView mGhostView1;
   private final CombatGhostView mGhostView2;
   private final GhostData mGhostData1;
   private final GhostData mGhostData2;
   private final ImageView mOKView;
   private final ImageView mCloseView;
   
   private final List<EColor> mWhiteDiceList = new ArrayList<EColor>();
}
