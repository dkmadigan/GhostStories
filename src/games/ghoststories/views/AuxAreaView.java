package games.ghoststories.views;

import games.ghoststories.R;
import games.ghoststories.data.GhostStoriesBitmaps;
import games.ghoststories.data.GhostStoriesGameManager;
import games.ghoststories.data.IGamePhaseListener;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EGamePhase;
import games.ghoststories.utils.BitmapUtils;
import games.ghoststories.utils.GameUtils;
import games.ghoststories.utils.ImageViewUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AuxAreaView extends LinearLayout {

   public AuxAreaView(Context pContext) {
      super(pContext);
   }

   public AuxAreaView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   public AuxAreaView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }
}
