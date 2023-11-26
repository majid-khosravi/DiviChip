package com.chips.divisive.ui.main.dialog

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chips.divisive.ui.detail.HueBar
import com.chips.divisive.ui.home.CircleShape
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(viewModel: ChipViewModel, chipId: Int?, profileId: Int?, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        ChipMaker(viewModel, chipId, profileId) {
            onDismiss()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerBottomSheet(
    setColor: (Float) -> Unit
) {

//    onColorChanged: (color: Color) -> Unit
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
//        ColorPicker(onColorChanged)
        HueBar(setColor)
    }


}


@Composable
fun ColorPicker(
    onColorChanged: (color: Color) -> Unit
) {
    // on below line we are creating a variable for controller
    val controller = rememberColorPickerController()

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp)
    ) {
        // on below line we are adding a row.
        Row(
            // on below line we are adding a modifier
            modifier = Modifier.fillMaxWidth(),
            // on below line we are adding horizontal
            // and vertical alignment.
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // on below line we are adding a alpha tile.
            AlphaTile(
                // on below line we are
                // adding modifier to it
                modifier = Modifier
                    .fillMaxWidth()
                    // on below line
                    // we are adding a height.
                    .height(60.dp)
                    // on below line we are adding clip.
                    .clip(RoundedCornerShape(6.dp)),
                // on below line we are adding controller.
                controller = controller
            )
        }
        // on below line we are
        // adding horizontal color picker.
        HsvColorPicker(
            // on below line we are
            // adding a modifier to it
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp),
            // on below line we are
            // adding a controller
            controller = controller,
            // on below line we are
            // adding on color changed.
            onColorChanged = {
                onColorChanged.invoke(it.color)
            }
        )
        /*  // on below line we are adding a alpha slider.
          AlphaSlider(
              // on below line we
              // are adding a modifier to it.
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp)
                  .height(35.dp),
              // on below line we are
              // adding a controller.
              controller = controller,
              // on below line we are
              // adding odd and even color.
              tileOddColor = Color.White,
              tileEvenColor = Color.Black
          )
          // on below line we are
          // adding a brightness slider.
          BrightnessSlider(
              // on below line we
              // are adding a modifier to it.
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp)
                  .height(35.dp),
              // on below line we are
              // adding a controller.
              controller = controller,
          )*/
    }
}


@Composable
fun ChipMaker(viewModel: ChipViewModel, chipId: Int?, profileId: Int?, onDismiss: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }

    val selectedColor = remember {
        mutableStateOf(Color.LightGray)
    }

    val chipValue = remember { mutableStateOf("") }

    val chipCount = remember { mutableStateOf(1) }
    chipId?.let { viewModel.findChipById(it) }


    if (isClicked) {
        viewModel.insertChip(
            value = chipValue.value,
            count = chipCount.value,
            color = selectedColor.value.value.toLong()
        )
    }
    val stat by viewModel.stat.collectAsStateWithLifecycle()
    val interState by viewModel.interState.collectAsStateWithLifecycle()


    if (stat.isLoading) {
        Log.d("TAG", "ProfileListScreen: ${stat.isLoading}")
    } else {
        stat.value?.let {
            chipValue.value = it.value
            chipCount.value = it.count
            selectedColor.value = Color(it.color)
        }
    }

    if (interState.isLoading) {
        Log.d("TAG", "ProfileListScreen: ${interState.isLoading}")
    } else {
        if (interState.value > 0)
            onDismiss.invoke()
    }


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
                color = selectedColor.value,
                textColor = Color.White,
                shapeSize = 100.dp,
                fontSize = 25.sp,
                text = chipValue.value
            )
            Input(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.5f, false)
                    .size(100.dp),
                label = "Value"
            ) {
                chipValue.value = it
            }
            Input(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.5f, false)
                    .size(100.dp),
                label = "Count"
            ) {
                try {
                    chipCount.value = it.toInt()
                } catch (e: Exception) {
                    chipCount.value = 0
                }
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            HueBar { hue ->
                selectedColor.value = Color.hsv(hue = hue, saturation = 0.9f, value = 0.7f)
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Text(text = "Font Color:")

            Canvas(modifier = Modifier.padding(16.dp), onDraw = {
                drawRect(Color.White)
            })

            Canvas(modifier = Modifier.padding(16.dp), onDraw = {
                drawRect(Color.White)
            })

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
