package games.ghoststories.views.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.data.interfaces.IGhostListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EHaunterLocation;
import games.ghoststories.utils.GameUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * View for a single ghost during the combat phase of the game. This view contains
 * the image of the ghost, a layer for showing damage to the ghost that sits 
 * on top of the ghost image, and the current health of the ghost below the
 * ghost image. 
 */
public class CombatGhostView extends RelativeLayout implements IGhostListener {

   /**
    * Constructor
    * @param pContext
    */
   public CombatGhostView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatGhostView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatGhostView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }      
   
   /**
    * Adds a damage token to the ghost.
    * @param pTokenId The drawable resource id of the token to add
    */
   public void addDamageToken(int pTokenId) {
     for(CombatDamageView cdv : mDamageViews) {
        if(cdv.getVisibility() == INVISIBLE) {
           cdv.setImageResource(pTokenId);
           cdv.setVisibility(VISIBLE);
           break;
        }
     }
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGhostListener#ghostKilled()
    */
   public void ghostKilled() {
   }
   
   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGhostListener#haunterUpdated(games.ghoststories.enums.EHaunterLocation, games.ghoststories.enums.EHaunterLocation, java.lang.Runnable)
    */
   public void haunterUpdated(EHaunterLocation pOldLocation,
         EHaunterLocation pNewLocation, Runnable pRunnable) {
   }

   /*
    * (non-Javadoc)
    * @see games.ghoststories.data.interfaces.IGhostListener#resistanceUpdated()
    */
   public void resistanceUpdated() {
      //Iterate over the resistance map and update the health views.
      Map<EColor, Integer> resistanceMap = mGhostData.getResistance();
      
      for(Entry<EColor, Integer> entry : resistanceMap.entrySet()) {         
         int ghostHealth = entry.getValue();
         List<GhostHealthView> activeHealths = mActiveHealthViews.get(entry.getKey());
         int activeHealthCount = activeHealths.size();
         int numHealthToDeactivate = activeHealthCount - ghostHealth;
         for(int i = 0; i < numHealthToDeactivate; ++i) {
            activeHealths.get(i).setActive(false);
         }
         for(int i = numHealthToDeactivate; i < activeHealthCount; ++i) {
            activeHealths.get(i).setActive(true);
         }
      }
   }
   
   /**
    * Sets the data model to use for this ghost view. Populates the ghost image,
    * and ghost health using the model
    * @param pGhostData The ghost data model
    */
   public void setGhostData(GhostData pGhostData) {
      mGhostData = pGhostData;
      mNumActiveHealthViews = 0;
      ImageView ghostImage = (ImageView)findViewById(R.id.ghost);
      ghostImage.setImageResource(pGhostData.getImageId());
      
      for(Entry<EColor, Integer> entry : pGhostData.getResistance().entrySet()) {
         EColor color = entry.getKey();
         Integer numHealth = entry.getValue();
         for(int i = 0; i < numHealth; ++i) {
            GhostHealthView ghv = getNextView();
            ghv.setColor(color);
            ghv.setActive(true);
            ghv.setVisibility(VISIBLE);     
            
            if(!mActiveHealthViews.containsKey(color)) {
               mActiveHealthViews.put(color, new ArrayList<GhostHealthView>());
            }
            mActiveHealthViews.get(color).add(ghv);
            mNumActiveHealthViews++;
         }
      }
      
      View topDamage = findViewById(R.id.top_damage);
      mDamageViews.add((CombatDamageView)topDamage.findViewById(R.id.combat_damage_1));
      mDamageViews.add((CombatDamageView)topDamage.findViewById(R.id.combat_damage_2));
      mDamageViews.add((CombatDamageView)topDamage.findViewById(R.id.combat_damage_3));
      mDamageViews.add((CombatDamageView)topDamage.findViewById(R.id.combat_damage_4));
      View bottomDamage = findViewById(R.id.bottom_damage);
      mDamageViews.add((CombatDamageView)bottomDamage.findViewById(R.id.combat_damage_1));
      mDamageViews.add((CombatDamageView)bottomDamage.findViewById(R.id.combat_damage_2));
      mDamageViews.add((CombatDamageView)bottomDamage.findViewById(R.id.combat_damage_3));
      mDamageViews.add((CombatDamageView)bottomDamage.findViewById(R.id.combat_damage_4));
      
      mGhostData.addGhostListener(this);
      GameUtils.invalidateView(this);
   }
   
   /**
    * Helper method for building the health area under the ghost. Returns the 
    * next unused health view.
    * @return The next unused health view
    */
   private GhostHealthView getNextView() {
      GhostHealthView view = null;
      View healthRow = null;
      switch(mNumActiveHealthViews) {
      case 0:         
         healthRow = findViewById(R.id.top_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_1);         
         break;
      case 1:
         healthRow = findViewById(R.id.top_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_2);         
         break;
      case 2:
         healthRow = findViewById(R.id.top_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_3);         
         break;
      case 3:
         healthRow = findViewById(R.id.top_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_4);         
         break;
      case 4:
         healthRow = findViewById(R.id.bottom_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_1);         
         break;
      case 5:
         healthRow = findViewById(R.id.bottom_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_2);         
         break;
      case 6: 
         healthRow = findViewById(R.id.bottom_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_3);         
         break;
      case 7:
         healthRow = findViewById(R.id.bottom_health);
         view = (GhostHealthView)healthRow.findViewById(R.id.combat_health_4);         
         break;
      }
      
      return view;
   }
   
   /**
    *  The health views that are active for this ghost -- The layout contains
    *  the maximum number of possible health views but we only activate a certain
    *  number based on the ghost health.
    **/
   private Map<EColor, List<GhostHealthView>> mActiveHealthViews =          
         new HashMap<EColor, List<GhostHealthView>>();
   /** Counter for the current total number of active health views **/
   private int mNumActiveHealthViews = 0;
   
   /** 
    * List of damage views that lie on the ghost. These are updated as damage
    * is dealt to the ghost
    **/
   private List<CombatDamageView> mDamageViews = new ArrayList<CombatDamageView>();
   
   /** The ghost data model **/
   private GhostData mGhostData = null;            
}
