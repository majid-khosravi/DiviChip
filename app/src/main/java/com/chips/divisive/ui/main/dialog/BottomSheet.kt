package com.chips.divisive.ui.main.dialog

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chips.divisive.model.ChipModel
import com.chips.divisive.ui.detail.HueBar
import com.chips.divisive.ui.home.CircleShape
import com.chips.divisive.ui.main.MyToast


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(viewModel: ChipViewModel, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        ChipMaker(viewModel) {
            onDismiss()
        }
    }


}


@Composable
fun ChipMaker(viewModel: ChipViewModel, onDismiss: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }

//    val state = viewModel.state.collectAsState()
    val stat by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    /*
        val selectedColor = remember {
            mutableStateOf(Color.LightGray)
        }

        val selectedFontColor = remember {
            mutableStateOf(Color.White)
        }

        val chipValue = remember { mutableStateOf("") }

        val chipCount = remember { mutableStateOf(1) }
        chipId?.let { viewModel.findChipById(it) }
    */


    if (isClicked) {
        viewModel.insertChip(stat.value)
    }


    if (stat.isLoading) {
        Log.d("TAG", "isLoading: ${stat.isLoading}")
    } else if (stat.isSuccess) {
        MyToast(context, "Your chip is added to profile!")
        onDismiss()
    } else


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "You can add or edit chips", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = Modifier.padding(8.dp),
                Arrangement.Center,
                Alignment.CenterVertically
            ) {

                CircleShape(
                    color = Color(stat.value.color),
                    textColor = Color(stat.value.textColor),
                    shapeSize = 100.dp,
                    fontSize = 25.sp,
                    text = stat.value.value
                )
                Input(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.5f, false)
                        .size(100.dp),
                    label = "Value"
                ) {
                    stat.value.value = it
                }
                Input(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.5f, false)
                        .size(100.dp),
                    label = "Count"
                ) {
                    try {
                        stat.value.count = it.toInt()
                    } catch (e: Exception) {
                        stat.value.count = 0
                    }
                }
            }
            Row(
                modifier = Modifier.padding(8.dp),
                Arrangement.Center,
                Alignment.CenterVertically
            ) {
                HueBar { hue ->
                    stat.value.color =
                        Color.hsv(hue = hue, saturation = 0.9f, value = 0.7f).value.toLong()
                }
            }
            Row(
                modifier = Modifier.padding(8.dp),
                Arrangement.Center,
                Alignment.CenterVertically
            ) {
                Text(text = "Font Color:")

                Canvas(modifier = Modifier
                    .padding(16.dp)
                    .size(48.dp)
                    .border(1.dp, Color.Magenta)
                    .clickable {
                        stat.value.textColor = Color.White.value.toLong()
                    }, onDraw = {
                    drawRect(Color.White)
                })

                Canvas(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .border(1.dp, Color.Magenta)
                        .clickable {
                            stat.value.textColor = Color.Black.value.toLong()
                        },
                    onDraw = {
                        drawRect(Color.Black)
                    })

                /*  Canvas(
                      modifier = Modifier
                          .size(size = 48.dp)
                          .border(width = 2.dp, color = Color.Magenta)
                  ) {
                      drawRect(
                          brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Yellow)),
                          size = Size(width = 48.dp.toPx(), height = 48.dp.toPx()),
                          topLeft = Offset(x = 48.dp.toPx(), y = 48.dp.toPx()),
                          style = Stroke(width = 6.dp.toPx())
                      )
                  }
      */
            }
            Row(
                modifier = Modifier.padding(8.dp),
                Arrangement.Center,
                Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        isClicked = true
                    }
                ) {
                    Text(text = "Save it")
                }
            }
        }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(modifier: Modifier = Modifier, label: String, callback: (String) -> Unit) {

    val text = remember { mutableStateOf("1") }
    Column {
        TextField(
            modifier = modifier,
            value = text.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 21.sp
            ),
            maxLines = 1,
            onValueChange = { newText ->
                text.value = newText

                if (text.value.isNotEmpty())
                    callback(text.value)
            },
            label = { Text(label) }
        )
//        Text("Hello, ${text.value}!")


        /*        var textShopName by remember { mutableStateOf("") }

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = textShopName,
                    onValueChange = { textShopName = it },
                    label = { Text("Shop Name") },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                        .fillMaxWidth(),

                    )*/
    }
}
