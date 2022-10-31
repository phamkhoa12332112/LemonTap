package com.example.lemonjuicetap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonjuicetap.ui.theme.LemonJuiceTapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonJuiceTapTheme {
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonJuiceTapTheme() {
    var nextStep by remember { mutableStateOf(1) }
    var countClick by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (nextStep) {
            1 -> LemonScreen(textLabelResourceId = R.string.lemon_select,
                drawId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = {
                    nextStep = 2
                    countClick = (2..4).random()
                })
            2 -> LemonScreen(textLabelResourceId = R.string.lemon_squeeze,
                drawId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description,
                onImageClick = {
                    countClick--
                    if (countClick == 0) {
                        nextStep = 3
                    }
                })
            3 -> LemonScreen(
                textLabelResourceId = R.string.lemon_drink,
                drawId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description,
                onImageClick = {
                    nextStep = 4
                })
            4 -> LemonScreen(textLabelResourceId = R.string.lemon_empty_glass,
                drawId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.empty_glass_content_description,
                onImageClick = {
                    nextStep = 1
                })
        }
    }
}



@Composable
fun LemonScreen(
    textLabelResourceId: Int,
    drawId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier
            .height(16.dp)
        )
        Image(
            painter = painterResource(id = drawId),
            contentDescription = stringResource(id = contentDescriptionResourceId),
            modifier = Modifier
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(red = 105, green = 205, blue = 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

