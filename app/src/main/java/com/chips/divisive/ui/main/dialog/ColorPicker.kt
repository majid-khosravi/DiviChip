package com.chips.divisive.ui.main.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chips.divisive.ui.detail.HueBar
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


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