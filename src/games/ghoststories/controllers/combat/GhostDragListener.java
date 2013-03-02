package games.ghoststories.controllers.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDiceSide;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.combat.CombatGhostView;
import games.ghoststories.views.common.TaoTokenView.TokenDragData;

import java.util.Map;
import java.util.Map.Entry;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

/**
 * Drag and drop listener for the combat ghost. Handles tokens and dice that
 * are dropped on the ghost and applies the damage to the ghost when this occurs.
 */
/*package*/ class GhostDragListener implements OnDragListener {

   /**
    * Constructor
    * @param pGhostData The ghost data for this ghost
    */
   public GhostDragListener(GhostData pGhostData) {
      mData = pGhostData;
   }

   /*
    * (non-Javadoc)
    * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
    */
   public boolean onDrag(View pView, DragEvent pEvent) {
      boolean handle = false;
      Object localState = pEvent.getLocalState();
      //Only handle if the data is a DragData type and combat related 
      if(localState instanceof DragData) {
         DragData dragData = (DragData)localState;
         EDragItem dragItem = dragData.getDragItem();
         if(dragItem.isCombatDragItem()) {            
            switch(pEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:            
               handle = true;
               break;
            case DragEvent.ACTION_DROP:
               Object data = dragData.getData();
               CombatGhostView cgv = (CombatGhostView)pView;
               switch(dragItem) {
               case COMBAT_DICE:
                  handle = handleDiceDrag(cgv, (EDiceSide)data);
                  break;
               case COMBAT_TAO:
                  TokenDragData taoData = (TokenDragData)data;
                  handle = handleTaoTokenDrag(cgv, taoData.mColor);
               default:
               }
               break;
            }
         }
      }
      return handle;
   }
   
   /**
    * Handles the case where a dice is dropped on the ghost. Checks to see
    * if the attack is valid and applies the attack if it is.
    * @param pView The view of the ghost in the combat area
    * @param pDice The dice type that was dropped on the ghost
    * @return <code>true</code> if the attack was applied, <code>false</code>
    *         if the attack was invalid and not applied
    */
   private boolean handleDiceDrag(CombatGhostView pView, EDiceSide pDice) {
      boolean handle = false;
      //TODO Handle the extra dice graphic
      int drawable = pDice.getDiceDrawable();      
      Map<EColor, Integer> resistance = mData.getResistance();
      if(pDice == EDiceSide.WHITE) {
         //Find the first health and cancel out that one
         for(Entry<EColor, Integer> entry : resistance.entrySet()) {
            Integer health = entry.getValue();
            if(health != null && health > 0) {
               //Ghost still has health left of this color so update health
               mData.updateResistance(entry.getKey(), -1);                        
               pView.addDamageToken(drawable);
               handle = true;
            }
         }
      } else {
         EColor color = pDice.getColor();
         //Only allow if an attack is valid                                       
         Integer health = resistance.get(color);
         if(health != null && health > 0) {
            //Ghost still has health left of this color so update health
            mData.updateResistance(color, -1);                        
            pView.addDamageToken(drawable);
            handle = true;
         } 
      }
      return handle;
   }
   
   /**
    * Handles the case where a tao token is dropped on the ghost. Checks to see
    * if the attack is valid and applies the attack if it is.
    * @param pView The view of the ghost in the combat area
    * @param pColor The color of token that was dropped on the ghost
    * @return <code>true</code> if the attack was applied, <code>false</code>
    *         if the attack was invalid and not applied
    */
   private boolean handleTaoTokenDrag(CombatGhostView pView, EColor pColor) {
      boolean handle = false;
      int drawable = GameUtils.getTaoTokenId(pColor);
      Map<EColor, Integer> resistance = mData.getResistance();
      //Only allow if an attack is valid                                       
      Integer health = resistance.get(pColor);
      if(health != null && health > 0) {
         //Ghost still has health left of this color so update health
         mData.updateResistance(pColor, -1);                        
         pView.addDamageToken(drawable);
         handle = true;
      } 
      return handle;
   }
   
   private final GhostData mData; 

}
