package com.crazyromteam.spenimagehover

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImagePreview(private val activity: AppCompatActivity) {

    private val overlay: FrameLayout = activity.findViewById(R.id.fullscreenOverlay)
    private val fullscreenImageView: ImageView = activity.findViewById(R.id.fullscreenImageView)

    fun showFullScreenImage(imageRes: Int) {
        fullscreenImageView.setImageResource(imageRes)
        overlay.visibility = View.VISIBLE
        overlay.bringToFront()

        // Fade-in animation
        overlay.animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }

    fun fadeOut() {
        overlay.animate()
            .alpha(0f)
            .setDuration(300)
            .withEndAction {
                overlay.visibility = View.GONE
            }
            .start()
    }
}