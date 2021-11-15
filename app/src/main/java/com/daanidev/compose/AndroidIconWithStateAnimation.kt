package com.daanidev.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.daanidev.compose.ui.theme.ComposeTheme

class AnimatedCanvas : AppCompatActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {

                drawAnimatedCanvas()
            }
        }
    }

}

@ExperimentalAnimationApi
@Composable
fun drawAnimatedCanvas() {

    // state check variables
    val headState = remember {
        mutableStateOf(false)
    }
    val leftEarState = remember {
        mutableStateOf(false)
    }
    val rightEarState = remember {
        mutableStateOf(false)
    }

    val rightEyeState = remember {
        mutableStateOf(false)
    }

    val leftEyeState = remember {
        mutableStateOf(false)
    }

    val centerRectState = remember {
        mutableStateOf(false)
    }

    val leftLegState = remember {
        mutableStateOf(false)
    }

    val rightLegState = remember {
        mutableStateOf(false)
    }

    val leftArmState = remember {
        mutableStateOf(false)
    }

    val rightArmState = remember {
        mutableStateOf(false)
    }
    // animations
    val headAnim = animateFloatAsState(
        targetValue = if (headState.value) .50f else 0f,
        finishedListener = {

            leftEarState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val leftEarAnim = animateFloatAsState(
        targetValue = if (leftEarState.value) 0.28f else 0.33f,
        finishedListener = {
            rightEarState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val rightEarAnim = animateFloatAsState(
        targetValue = if (rightEarState.value) 0.28f else 0.32f,
        finishedListener = {
            leftEyeState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val leftEyeAnim = animateFloatAsState(
        targetValue = if (leftEyeState.value) 20f else 0f,
        finishedListener = {
            rightEyeState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val rightEyeAnim = animateFloatAsState(
        targetValue = if (rightEyeState.value) 20f else 0f,
        finishedListener = {
            centerRectState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val centerRectAnim = animateFloatAsState(
        targetValue = if (centerRectState.value) 0.16f else 0f,
        finishedListener = {

            leftLegState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val leftLegAnim = animateFloatAsState(
        targetValue = if (leftLegState.value) 0.65f else 0.5f,
        finishedListener = {

            rightLegState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val rightLegAnim = animateFloatAsState(
        targetValue = if (rightLegState.value) 0.65f else 0.5f,
        finishedListener = {

            leftArmState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val leftArmAnim = animateFloatAsState(
        targetValue = if (leftArmState.value) 0.55f else 0.45f,
        finishedListener = {

            rightArmState.value = true
        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    val rightArmAnim = animateFloatAsState(
        targetValue = if (rightArmState.value) 0.55f else 0.45f,
        finishedListener = {

        },
        animationSpec = tween(1000, easing = LinearEasing)
    )

    // canvas
    Canvas(modifier = Modifier.fillMaxSize()) {

        val canvasWidth = size.width
        val canvasHeight = size.height
        val sizeArc = size / 2.25F

        drawArc(
            color = Color(0xFF58bd46),
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(size.width * headAnim.value, size.height * 0.25f),
            topLeft = Offset(
                (size.width - sizeArc.width) / 2f,
                (size.height - sizeArc.height) / 2f
            ),
        )


        if (leftEarState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(.38f), canvasHeight.times(0.33f)),
                strokeWidth = 20f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(.31f), canvasHeight.times(leftEarAnim.value))
            )
        }

        if (rightEarState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(.68f), canvasHeight.times(0.32f)),
                strokeWidth = 15f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(.75f), canvasHeight.times(rightEarAnim.value))
            )
        }

        if (leftEarState.value) {
            drawCircle(
                color = Color.White,
                center = Offset(x = (canvasWidth / 2) * 0.88f, y = (size.height) / 3f),
                radius = leftEyeAnim.value
            )
        }

        if (rightEyeState.value) {
            drawCircle(
                color = Color.White,
                center = Offset(x = (canvasWidth / 2) * 1.22f, y = (size.height) / 3f),
                radius = rightEyeAnim.value
            )
        }

        if (centerRectState.value) {
            drawRoundRect(
                color = Color(0xFF58bd46),
                topLeft = Offset(x = canvasWidth / 3.3F, y = canvasHeight / 2.3F),
                size = Size(canvasWidth / 2.25f, canvasHeight.times(centerRectAnim.value)),
                cornerRadius = CornerRadius(x = 10f, y = 10f)
            )
        }

        if (leftLegState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(0.4f), canvasHeight.times(.5f)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(0.4f), canvasHeight.times(leftLegAnim.value))
            )
        }

        if (rightLegState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(canvasWidth.times(0.65f), canvasHeight.times(0.5f)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(canvasWidth.times(0.65f), canvasHeight.times(rightLegAnim.value))
            )
        }

        if (leftArmState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(
                    (size.width - sizeArc.width) / 2.25f,
                    canvasHeight.times(leftArmAnim.value)
                ),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset((size.width - sizeArc.width) / 2.25f, canvasHeight.times(0.45f))
            )
        }

        if (rightArmState.value) {
            drawLine(
                color = Color(0xFF58bd46),
                start = Offset(size.width.times(0.8f), canvasHeight.times(rightArmAnim.value)),
                strokeWidth = 70f,
                cap = StrokeCap.Round,
                end = Offset(size.width.times(0.8f), canvasHeight.times(.45f))
            )
        }


    }
    Button(onClick = { headState.value = true }) {

        Text(text = "Play Animation")

    }


}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun animatedPreview() {
    ComposeTheme {
        drawAnimatedCanvas()
    }
}
