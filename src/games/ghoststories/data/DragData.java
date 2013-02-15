package games.ghoststories.data;

import android.view.View;
import games.ghoststories.enums.EDragItem;

public class DragData {

   public DragData(EDragItem pDragItem, Object pData, View pView) {
      mDragItem = pDragItem;
      mData = pData;
      mView = pView;
   }
   
   public EDragItem getDragItem() {
      return mDragItem;
   }
   
   public Object getData() {
      return mData;
   }
   
   public View getView() {
      return mView;
   }

   private EDragItem mDragItem;
   private Object mData;
   private View mView;
}
