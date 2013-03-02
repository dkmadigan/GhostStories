package com.utils;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Animation utility methods
 */
public abstract class AnimationUtils2 {

   /**
    * Animates the view to the given x/y coordinates using the given duration 
    * and calling the given {@link Runnable} once the animation completes.
    * @param pView The view to animate
    * @param pX The x location in pixels
    * @param pY The y location in pixels
    * @param pDuration The duration of the animation
    * @param pEndAction The runnable to execute at the end of the animation
    */
   public static void animateXY(final View pView, 
         final int pX, final int pY, final int pDuration, 
         final Runnable pEndAction) {
      if(AndroidUtils.isUIThread()) {
         pView.animate().setDuration(pDuration).x(
               pX).y(pY).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               animateXY(pView, pX, pY, pDuration, pEndAction);
            }
         });
      }
   } 

   /**
    * Runs the given animation on the given view.
    * @param pAnimationId The animation id
    * @param pView The view to run the animation on
    */
   public static void runAnimation(final int pAnimationId, final View pView) {
      if(AndroidUtils.isUIThread()) {         
         Animation anim = AnimationUtils.loadAnimation(pView.getContext(), 
               pAnimationId);
         pView.startAnimation(anim);
      } else {
         pView.post(new Runnable() {
            public void run() {
               runAnimation(pAnimationId, pView); 
            }                    
         });
      }
   }

   /**
    * Animates the view by translating the view in the x direction to the 
    * specified x value using the given duration. Calls the specified 
    * {@link Runnable} once the animation completes.
    * @param pView The view to animate
    * @param pValue The x value to animate the view to
    * @param pDuration The duration of the animation
    * @param pEndAction The runnable to execute at the end of the animation
    */
   public static void translationX(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(AndroidUtils.isUIThread()) {
         pView.animate().setDuration(pDuration).translationX(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationX(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }

   /**
    * Animates the view by translating the view in the y direction to the 
    * specified y value using the given duration. Calls the specified 
    * {@link Runnable} once the animation completes.
    * @param pView The view to animate
    * @param pValue The y value to animate the view to
    * @param pDuration The duration of the animation
    * @param pEndAction The runnable to execute at the end of the animation
    */
   public static void translationY(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(AndroidUtils.isUIThread()) {
         pView.animate().setDuration(pDuration).translationY(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationY(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   } 

   /**
    * Animates the view by translating the view in the x direction by the 
    * specified x value using the given duration. Calls the specified 
    * {@link Runnable} once the animation completes.
    * @param pView The view to animate
    * @param pValue The x value to animate the view by
    * @param pDuration The duration of the animation
    * @param pEndAction The runnable to execute at the end of the animation
    */
   public static void translationXBy(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(AndroidUtils.isUIThread()) {
         pView.animate().setDuration(pDuration).translationXBy(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationXBy(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   }

   /**
    * Animates the view by translating the view in the y direction by the 
    * specified y value using the given duration. Calls the specified 
    * {@link Runnable} once the animation completes.
    * @param pView The view to animate
    * @param pValue The y value to animate the view by
    * @param pDuration The duration of the animation
    * @param pEndAction The runnable to execute at the end of the animation
    */
   public static void translationYBy(final View pView, 
         final float pValue, final int pDuration, final Runnable pEndAction) {
      if(AndroidUtils.isUIThread()) {
         pView.animate().setDuration(pDuration).translationYBy(
               pValue).withEndAction(pEndAction);
      } else {
         pView.post(new Runnable() {
            public void run() {
               translationYBy(pView, pValue, pDuration, pEndAction);
            }
         });
      }
   } 

}
