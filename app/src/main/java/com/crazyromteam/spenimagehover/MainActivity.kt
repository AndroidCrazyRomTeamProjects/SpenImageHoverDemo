package com.crazyromteam.spenimagehover

import android.os.Bundle
import android.view.MotionEvent
import android.view.PointerIcon
import android.view.PointerIcon.TYPE_HAND
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TYPE_SPEN_HOVER = 20010
    private var spenHoverPointer: PointerIcon? = null
    private var mouseHoverPointer: PointerIcon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val FullscreenImageView = findViewById<ImageView>(R.id.fullscreenImageView)
        val imagePreview = ImagePreview(this)

        imageView.setOnHoverListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_HOVER_ENTER -> {
                    when (event.getToolType(0)) {
                        MotionEvent.TOOL_TYPE_STYLUS -> {
                            if (spenHoverPointer == null){
                                spenHoverPointer = PointerIcon.getSystemIcon(applicationContext, TYPE_SPEN_HOVER)
                            }
                            FullscreenImageView.pointerIcon = spenHoverPointer
                            imagePreview.showFullScreenImage(R.drawable.berat)

                            FullscreenImageView.setOnHoverListener { _, fullScreenEvent ->
                                when (fullScreenEvent.action) {
                                    MotionEvent.ACTION_HOVER_EXIT -> {
                                        imagePreview.fadeOut()
                                        true
                                    }
                                    else -> false
                                }
                            }
                            true
                        }
                        MotionEvent.TOOL_TYPE_MOUSE -> {
                            if (mouseHoverPointer == null){
                                mouseHoverPointer = PointerIcon.getSystemIcon(applicationContext, TYPE_HAND)
                            }

                            view.pointerIcon = mouseHoverPointer
                            true
                        }
                        else -> false
                    }
                }
                else -> false
            }
        }
    }
}