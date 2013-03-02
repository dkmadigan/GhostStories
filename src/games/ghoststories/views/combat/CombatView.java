package games.ghoststories.views.combat;

import java.util.ArrayList;
import java.util.List;

import games.ghoststories.R;
import games.ghoststories.controllers.combat.CombatAreaController;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.PlayerData;
import games.ghoststories.views.aux_area.PlayerInfoView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Top level view for the combat area that shows during the combat phase of
 * the game. 
 */
public class CombatView extends RelativeLayout {

   /**
    * Constructor
    * @param pContext
    */
   public CombatView(Context pContext) {
      super(pContext);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
      init();
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
      init();
   }

   /**
    * Destroy this view
    */
   public void destroy() {
      mController.destroy();
   }

   /**
    * Sets the data that is used for the combat.
    * @param pPrimaryAttacker The primary attacker during this combat phase
    * @param pSecondaryAttackers The list of secondary attackers during this combat phase
    * @param pGhost1 The first ghost being attacked
    * @param pGhost2 The second ghost being attacked or <code>null</code> if N/A
    */
   public void setCombatData(PlayerData pPrimaryAttacker, 
         List<PlayerData> pSecondaryAttackers, GhostData pGhost1, 
         GhostData pGhost2) {
      //Info area 1 is for the primary player
      PlayerInfoView pi1 = (PlayerInfoView)findViewById(R.id.info_area_1);
      pi1.setPlayerData(pPrimaryAttacker);
      pi1.setVisibility(VISIBLE);

      //Info area 2-4 are for any supporting players      
      List<PlayerInfoView> playerViews = new ArrayList<PlayerInfoView>();
      playerViews.add((PlayerInfoView)findViewById(R.id.info_area_2));
      playerViews.add((PlayerInfoView)findViewById(R.id.info_area_3));
      playerViews.add((PlayerInfoView)findViewById(R.id.info_area_4));
      for(PlayerInfoView piv : playerViews) {
         piv.setVisibility(INVISIBLE);
      }      
      for(int i = 0; i < pSecondaryAttackers.size(); ++i) {
         PlayerInfoView view = playerViews.get(i);
         PlayerData data = pSecondaryAttackers.get(i);
         view.setVisibility(VISIBLE);
         view.setPlayerData(data);         
      }

      //Set the ghost data
      CombatGhostView ghost1 = (CombatGhostView)findViewById(R.id.ghost1);
      ghost1.setVisibility(GONE);
      CombatGhostView ghost2 = (CombatGhostView)findViewById(R.id.ghost2);
      ghost2.setVisibility(GONE);

      if(pGhost1 != null) {
         ghost1.setGhostData(pGhost1);
         ghost1.setVisibility(VISIBLE);
      }
      if(pGhost2 != null) {
         ghost2.setGhostData(pGhost2);
         ghost2.setVisibility(VISIBLE);
      }

      CombatDiceAreaView diceContainer = 
            (CombatDiceAreaView)findViewById(R.id.combat_dice);
      diceContainer.setPrimaryAttacker(pPrimaryAttacker);      

      //TODO Should this really be creating the controller? 
      //Probably should be passed in.
      mController = new CombatAreaController(this, pPrimaryAttacker, 
            pSecondaryAttackers, pGhost1, pGhost2);
   }

   /**
    * Initialize this view 
    */
   private void init() {
      //Ignore touches so they don't propagate to the background
      setOnTouchListener(new OnTouchListener() {         
         public boolean onTouch(View v, MotionEvent event) {
            return true;
         }
      });
   }

   /** Controller for the combat area **/
   private CombatAreaController mController = null;
}
