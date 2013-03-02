package games.ghoststories.data;

import android.view.View;
import games.ghoststories.enums.EDragItem;

/**
 * Holder for data transferred during a drag and drop operation.
 */
public class DragData {

   /**
    * Constructor
    * @param pDragItem The type of item being dragged
    * @param pData Custom data for the drag and drop operation
    * @param pView Reference to the view being dragged
    */
   public DragData(EDragItem pDragItem, Object pData, View pView) {
      mDragItem = pDragItem;
      mData = pData;
      mView = pView;
   }
   
   /**
    * @return The custom data associated with this drag and drop
    */
   public Object getData() {
      return mData;
   }
   
   /**
    * @return The type of item that was dragged
    */
   public EDragItem getDragItem() {
      return mDragItem;
   }
   
   /**
    * @return The view that was dragged
    */
   public View getView() {
      return mView;
   }

   /** Custom data for the drag and drop operation **/
   private final Object mData;
   /** The type of item that was dragged **/
   private final EDragItem mDragItem;
   /** The view that was dragged **/
   private final View mView;
}
