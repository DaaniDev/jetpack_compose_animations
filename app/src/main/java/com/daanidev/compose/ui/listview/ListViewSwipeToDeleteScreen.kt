package com.daanidev.compose.ui.listview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt

class ListViewSwipeToDeleteScreen : AppCompatActivity() {

    var id=0
    @ExperimentalUnitApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setupList()
        }
    }


    @ExperimentalUnitApi
    @ExperimentalMaterialApi
    @Composable
    fun setupList() {

        val notesList = remember {
            mutableStateListOf<NotesItem>()
        }


        val inputvalue = remember { mutableStateOf(TextFieldValue()) }


        Column {

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(Dp(60f))
            ) {

                TextField(
                    value = inputvalue.value,
                    onValueChange = {
                        inputvalue.value = it
                    },
                    modifier = Modifier.weight(0.8f),
                    placeholder = { Text(text = "Enter your note") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = TextUnit.Unspecified,
                        fontFamily = FontFamily.SansSerif
                    ),
                    maxLines = 1,
                    singleLine = true
                )

                Button(
                    onClick = {
                        notesList.add(NotesItem(id++,inputvalue.value.text))
                    },
                    modifier = Modifier
                        .weight(0.2f)
                        .fillMaxHeight()
                ) {
                    Text(text = "ADD")
                }
            }
            Spacer(modifier = Modifier.height(Dp(1f)))


            LazyColumn {

                items(notesList,{notesList:NotesItem->notesList.id}){item->
                    val dismissState = rememberDismissState()

                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {

                        notesList.remove(item)
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = Dp(1f)),
                        directions = setOf(
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                        },
                        background = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.White
                                    else -> Color.Red
                                }
                            )
                            val alignment = Alignment.CenterEnd
                            val icon = Icons.Default.Delete

                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = Dp(20f)),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        },
                        dismissContent = {

                            Card(
                                elevation = animateDpAsState(
                                    if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                ).value,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(Dp(50f))
                                    .align(alignment = Alignment.CenterVertically)
                            ) {
                                setUpRow(item = item)
                            }
                        }
                    )

                    Divider(Modifier.fillMaxWidth(), Color.DarkGray)
                }
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    fun setUpRow(
        item:NotesItem
    ){
        Row(
          modifier= Modifier
              .fillMaxWidth()
              .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = item.title,modifier = Modifier.wrapContentSize(),fontSize = TextUnit(value = 16f,type = TextUnitType.Sp))

        }
    }
}