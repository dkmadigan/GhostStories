package com.interfaces;

/**
 * Interface used by components that are able to be dragged. A dragged component
 * can specify the data to be transmitted via the {@link #getDragData()} function.
 */
public interface IDraggable<T> {
   
   /**
    * @return The data associated with this draggable object
    */
   public T getDragData();
}
