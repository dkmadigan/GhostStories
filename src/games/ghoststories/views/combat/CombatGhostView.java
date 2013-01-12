package games.ghoststories.views.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import games.ghoststories.R;
import games.ghoststories.data.GhostData;
import games.ghoststories.enums.EColor;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CombatGhostView extends RelativeLayout {

   public CombatGhostView(Context pContext) {
      super(pContext);
   }

   public CombatGhostView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public CombatGhostView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
   
   public void setGhostData(GhostData pGhostData) {
      mGhostData = pGhostData;
      ImageView ghostImage = (ImageView)findViewById(R.id.ghost);
      ghostImage.setImageResource(pGhostData.getImageId());
      
      for(Entry<EColor, Integer> entry : pGhostData.getResistance().entrySet()) {
         EColor color = entry.getKey();
         Integer numHealth = entry.getValue();
         for(int i = 0; i < numHealth; ++i) {
            
         }
      }
   }
   
   private GhostHealthView getNextView() {
      GhostHealthView view = null;
      View healthRow = null;
      switch(mActiveHealthViews.size()) {
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
      
   private GhostData mGhostData = null;
   private List<GhostHealthView> mActiveHealthViews = new ArrayList<GhostHealthView>();
}
