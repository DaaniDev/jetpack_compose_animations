package com.daanidev.compose.ui.exoplayer

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.daanidev.compose.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util


class ExoPlayerActivity : AppCompatActivity() {

    val videoURL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { uiContainer() }

    }


    @Composable
    private fun uiContainer() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("ExoPlayer View", color = Color.White) },
                    backgroundColor = Color(0xff0f9d58)
                )
            },
            content = { exoPlayerUI() }
        )
    }

    @Composable
    private fun exoPlayerUI() {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val mContext = LocalContext.current

            // Initializing ExoPLayer
            val mExoPlayer = remember(mContext) {
                ExoPlayer.Builder(mContext).build().apply {

                    val mediaItem = MediaItem.Builder()
                        .setUri(Uri.parse(videoURL))
                        .build()
                    setMediaItem(mediaItem)

                    playWhenReady = true
                    prepare()
                }
            }


            // Implementing ExoPlayer
            AndroidView(factory = { context ->
                StyledPlayerView(context).apply {
                    player = mExoPlayer

                }
            })
        }
    }


}