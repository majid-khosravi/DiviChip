package com.chips.divisive.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chips.divisive.ui.detail.HueBar
import com.chips.divisive.ui.home.CircleShape
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import android.graphics.Color as AndroidColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        ChipMaker()
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
fun ChipMaker() {
//    var isClicked by mutableStateOf(false)
    var isClicked by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.LightGray) }


    val hsv = remember {
        val hsv = floatArrayOf(0f, 0f, 0f)
        AndroidColor.colorToHSV(Color.Blue.toArgb(), hsv)

        mutableStateOf(
            Triple(hsv[0], hsv[1], hsv[2])
        )
    }
    val backgroundColo2r = remember(hsv.value) {
        mutableStateOf(Color.hsv(hsv.value.first, hsv.value.second, hsv.value.third))
    }


    val hsv3 = remember {
        val hsv3 = floatArrayOf(0f, 0f, 0f)
        AndroidColor.colorToHSV(Color.Blue.toArgb(), hsv3)

        mutableStateOf(
            Triple(hsv3[0], hsv3[1], hsv3[2])
        )
    }
    val backgroundColo2r3 = remember(hsv3.value) {
        mutableStateOf(Color.hsv(hsv3.value.first, hsv3.value.second, hsv3.value.third))
    }


    val backgroundHSV = remember {
        val backgroundHSV = floatArrayOf(0f, 0f, 0f)
        AndroidColor.colorToHSV(Color.LightGray.toArgb(), backgroundHSV)

        mutableStateOf(
            Triple(backgroundHSV[0], backgroundHSV[1], backgroundHSV[2])
        )
    }

    val foregroundHSV = remember {
        val foregroundHSV = floatArrayOf(0f, 0f, 0f)
        AndroidColor.colorToHSV(Color.Black.toArgb(), foregroundHSV)

        mutableStateOf(
            Triple(foregroundHSV[0], foregroundHSV[1], foregroundHSV[2])
        )
    }

    val backgroundColor = remember(backgroundHSV.value) {
        mutableStateOf(
            Color.hsv(
                backgroundHSV.value.first,
                backgroundHSV.value.second,
                backgroundHSV.value.third
            )
        )
    }

    val foregroundColor = remember(foregroundHSV.value) {
        mutableStateOf(
            Color.hsv(
                foregroundHSV.value.first,
                foregroundHSV.value.second,
                foregroundHSV.value.third
            )
        )
    }

    val chipValue = remember { mutableStateOf("100$") }

    val chipCount = remember { mutableStateOf(1) }



    Column(
        modifier = Modifier.padding(16.dp),
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "You can add or edit chips")
        }
        Row(
            modifier = Modifier.padding(0.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            CircleShape(
                color = backgroundColo2r3.value,
                textColor = backgroundColo2r.value,
                shapeSize = 100.dp,
                text = chipValue.value
            )
            Input(modifier = Modifier.size(100.dp)) {
                chipValue.value = it.toString()
            }
            Input(modifier = Modifier.size(100.dp)) {
                chipCount.value = it
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            HueBar { hue ->
                hsv.value = Triple(hue, hsv.value.second, hsv.value.third)

                backgroundHSV.value =
                    Triple(hue, backgroundHSV.value.second, backgroundHSV.value.third)
            }
        }
        Row(
            modifier = Modifier.padding(8.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            HueBar { hue ->
                hsv3.value =
                    Triple(hue, hsv3.value.second, hsv3.value.third)
                foregroundHSV.value =
                    Triple(hue, foregroundHSV.value.second, foregroundHSV.value.third)
            }
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
fun Input(modifier: Modifier = Modifier, callback: (Int) -> Unit) {

    val text = remember { mutableStateOf("1") }
    Column {
        TextField(
            modifier = modifier,
            value = text.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            onValueChange = { newText ->
                text.value = newText

                if (text.value.isNotEmpty())
                    callback(text.value.toInt())
            },
            label = { Text("Value of this chip") }
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
