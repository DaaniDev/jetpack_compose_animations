package com.daanidev.compose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daanidev.compose.ui.theme.ComposeTheme

class CanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {

                drawCanvas()
            }
        }
    }

}


    @Composable
    fun drawCanvas() {


        val rightLegAnim = remember { Animatable(0.5f) }

            LaunchedEffect(rightLegAnim) {
                rightLegAnim.animateTo(
                    targetValue = 0.65f,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
        }

        val leftLegAnim = remember { Animatable(0.5f) }

        LaunchedEffect(leftLegAnim) {
            leftLegAnim.animateTo(
                targetValue = 0.65f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }


        val leftArmAnim = remember { Animatable(0.45f) }

        LaunchedEffect(leftArmAnim) {
            leftArmAnim.animateTo(
                targetValue = 0.55f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val rightArmAnim = remember { Animatable(0.45f) }

        LaunchedEffect(rightArmAnim) {
            rightArmAnim.animateTo(
                targetValue = 0.55f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val rectangleAnim = remember { Animatable(0f) }

        LaunchedEffect(rectangleAnim) {
            rectangleAnim.animateTo(
                targetValue = 0.16f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val headAnim = remember { Animatable(0f) }

        LaunchedEffect(headAnim) {
            headAnim.animateTo(
                targetValue = .25f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val headLeftLineAnim = remember { Animatable(0.28f) }

        LaunchedEffect(headLeftLineAnim) {
            headLeftLineAnim.animateTo(
                targetValue = .33f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val headRightLineAnim = remember { Animatable(0.28f) }

        LaunchedEffect(headRightLineAnim) {
            headRightLineAnim.animateTo(
                targetValue = .32f,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )

        }

        val leftEyeAnim = remember { Animatable(0f) }

        LaunchedEffect(leftEyeAnim) {
            leftEyeAnim.animateTo(
                targetValue = 20f,
                animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
            )
          //  fadeIn(initialAlpha = 0.3f)

        }
       /* AnimatedVisibility(
            visible = true,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            )
        ) {
            // Content that needs to appear/disappear goes here:
        }*/


        Canvas(modifier = Modifier.fillMaxSize()){

            val canvasWidth = size.width
            val canvasHeight = size.height
            val sizeArc = size/2.25F


            drawArc(
                color = Color(0xFF58bd46),
                startAngle = 0f,
                sweepAngle = -180f,
                useCenter = false,
                size = Size(size.width * .50f, size.height * headAnim.value),
                topLeft = Offset((size.width - sizeArc.width)/2f,(size.height - sizeArc.height)/2f),

                )

            drawCircle(
                color = Color.White,
                center = Offset(x = (canvasWidth / 2) * 0.88f , y = (size.height)/3f),
                radius = leftEyeAnim.value
            )
            drawCircle(
                color = Color.White,
                center = Offset(x = (canvasWidth / 2) * 1.22f, y = (size.height)/3f),
                radius = leftEyeAnim.value
            )

            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(.38f), canvasHeight.times(headLeftLineAnim.value)),
                strokeWidth = 15f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(.31f), canvasHeight.times(0.28f))
            )
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(.68f), canvasHeight.times(headRightLineAnim.value)),
                strokeWidth = 15f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(.75f), canvasHeight.times(.28f))
            )

            drawLine(
                color = Color(0xFF58bd46),
                start = Offset((size.width - sizeArc.width)/2.25f, canvasHeight.times(leftArmAnim.value)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset((size.width - sizeArc.width)/2.25f, canvasHeight.times(0.45f))
            )

            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(size.width.times(0.8f), canvasHeight.times(rightArmAnim.value)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(size.width.times(0.8f), canvasHeight.times(.45f))
            )
        /*    drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasHeight.times(0.37f), canvasHeight.times(.55f)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(canvasHeight.times(0.37f), canvasHeight.times(.45f))
            )*/

            drawRoundRect(
                color = Color(0xFF58bd46),
                topLeft = Offset(x = canvasWidth / 3.3F, y = canvasHeight / 2.3F),
                size = Size(canvasWidth/2.25f,canvasHeight.times(rectangleAnim.value)),
                cornerRadius = CornerRadius(x=10f,y=10f)
            )

            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(0.4f), canvasHeight.times(.5f)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(0.4f), canvasHeight.times(leftLegAnim.value))
            )

            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(0.65f), canvasHeight.times(0.5f)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(0.65f), canvasHeight.times(rightLegAnim.value))
            )


        }




    }
@Preview(showBackground = true)
@Composable
fun preview() {
    ComposeTheme {
       drawCanvas()
    }
}

