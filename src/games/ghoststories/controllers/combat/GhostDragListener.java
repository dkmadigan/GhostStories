package games.ghoststories.controllers.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.data.GhostData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDiceSide;
import games.ghoststories.enums.EDragItem;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.views.combat.CombatGhostView;

import java.util.Map;
import java.util.Map.Entry;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

/*package*/ class GhostDragListener implements OnDragListener {

   public GhostDragListener(GhostData pGhostData) {
      mData = pGhostData;
   }

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
                  handle = handleTaoTokenDrag(cgv, (EColor)data);
               default:
               }
               break;
            }
         }
      }
      return handle;
   }
   
   private boolean handleDiceDrag(CombatGhostView pView, EDiceSide pDice) {
      boolean handle = false;
      //TODO Handle the extra dice graphic
      int drawable = pDice.getDiceDrawable();
      EColor color = pDice.getColor();
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
