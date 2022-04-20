package com.yvkalume.facebookstory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene

@OptIn(ExperimentalMotionApi::class)
@Composable
fun HeadStoryItem(
    progress: Float = 0f,
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    var cardCornerShape by remember { mutableStateOf(0) }
    var cardHeight by remember { mutableStateOf(200) }
    var cardWidth by remember { mutableStateOf(120) }
    var cardColor by remember { mutableStateOf(Color(0xFFFFFFFF)) }
    var cardElevation by remember { mutableStateOf(1) }

    var imageCornerShape by remember { mutableStateOf(0) }
    var imageBorderWidth by remember { mutableStateOf(0) }
    var imageBorderColor by remember { mutableStateOf(Color.Transparent) }

    Card(
        modifier = Modifier.size(height = (cardHeight).dp, width = (cardWidth).dp),
        shape = RoundedCornerShape((cardCornerShape).dp),
        backgroundColor = cardColor,
        elevation = (cardElevation).dp
    ) {
        MotionLayout(
            progress = progress,
            motionScene = MotionScene(content = motionScene),
            modifier = Modifier.fillMaxSize()
        ) {
            val imageProperties = motionProperties(id = "image")

            LaunchedEffect(imageProperties.value.int("cardCornerShape")) {
                cardCornerShape = imageProperties.value.int("cardCornerShape")
                cardHeight = imageProperties.value.int("cardHeight")
                cardWidth = imageProperties.value.int("cardWidth")
                cardColor = imageProperties.value.color("cardColor")
                cardElevation = imageProperties.value.int("cardElevation")
                imageCornerShape = imageProperties.value.int("imageCornerShape")
                imageBorderColor = imageProperties.value.color("imageBorderColor")
                imageBorderWidth = imageProperties.value.int("imageBorderWidth")
            }

            Surface(
                shape = RoundedCornerShape((imageCornerShape).dp),
                border = BorderStroke(width = (imageBorderWidth).dp, color = imageBorderColor),
                modifier = Modifier
                    .layoutId("image")
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img2),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentDescription = null
                )
            }
            Surface(
                modifier = Modifier
                    .layoutId("button"),
                color = Color(0xFF4267B2),
                border = BorderStroke(width = 1.dp, color = Color.White),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                )
            }
            Text(
                text = "Create a Story",
                modifier = Modifier
                    .layoutId("text")
                    .padding(8.dp),
                style = MaterialTheme.typography.subtitle2.copy(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}