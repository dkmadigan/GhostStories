package games.ghoststories.views.combat;

import games.ghoststories.data.DragData;
import games.ghoststories.enums.EColor;
import games.ghoststories.enums.EDragItem;

import com.interfaces.IDraggable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * {@link ImageView} representing a single damage token that has been placed
 * on a ghost during the combat phase.
 */
//TODO Maybe can get rid of this
public class CombatDamageView extends ImageView implements IDraggable<DragData> {

   public class CombatDamageDragData {
      /** The color of the token **/
      public EColor mColor;
      /** The ghost view **/
      public CombatGhostView mGhostView;
      /** The id of the token drawable **/
      public int mId;
      /** The owner view **/
      public View mOwner;
   }
   
   /**
    * Constructor
    * @param pContext
    */
   public CombatDamageView(Context pContext) {
      super(pContext);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    */
   public CombatDamageView(Context pContext, AttributeSet pAttrs) {
      super(pContext, pAttrs);
   }

   /**
    * Constructor
    * @param pContext
    * @param pAttrs
    * @param pDefStyle
    */
   public CombatDamageView(Context pContext, AttributeSet pAttrs, int pDefStyle) {
      super(pContext, pAttrs, pDefStyle);
   }   
   
   /**
    * Sets the data of this combat damage view.
    * @param pOwner The owner view
    * @param pGhostView The ghost view this belongs to
    * @param pType The type of view (either {@link EDragItem#DAMAGE_DICE} or 
    * {@link EDragItem#DAMAGE_TAO})
    * @param pColor The color of the token
    * @param pId The id of the token
    */
   public void setData(View pOwnerView, CombatGhostView pGhostView, EDragItem pType, 
         EColor pColor, int pId) {
      mOwner = pOwnerView;
      mGhostView = pGhostView;
      mType = pType;
      mColor = pColor;
      mId = pId;
   }
   
   /*
    * (non-Javadoc)
    * @see com.interfaces.IDraggable#getDragData()
    */
   public DragData getDragData() {
      CombatDamageDragData data = new CombatDamageDragData();
      data.mColor = mColor;
      data.mGhostView = mGhostView;
      data.mId = mId;
      data.mOwner = mOwner;
      return new DragData(mType, data, this);
   }
   
   /** The color of the damage token **/
   private EColor mColor;
   /** The drawable id of this token **/
   private int mId;
   /** The ghost view this belongs to **/
   private CombatGhostView mGhostView;
   /** The owner view **/
   private View mOwner;
   /** The owner type **/
   private EDragItem mType;
}
