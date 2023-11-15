package com.chips.divisive

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chips.divisive.ui.theme.ChipsDivisiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChipsDivisiveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    MainScreen()
                }
            }
        }
    }
}


private val RED_CHIPS_COUNT = 38
private val BLUE_CHIPS_COUNT = 68
private val GREEN_CHIPS_COUNT = 102
private val YELLOW_CHIPS_COUNT = 33

@Composable
fun MainScreen() {
    Box(
        modifier = with(Modifier.fillMaxWidth()) {
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_home),
                    contentScale = ContentScale.FillBounds
                )
        }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            Arrangement.SpaceBetween
        ) {
            val playersCount = remember {
                mutableStateOf(1)
            }

            ButtonsCard {
                playersCount.value = it
            }

            ChipsTable(playersCount.value)
//            ChipsRow(playersCount.value)


            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(weight =1f, fill = true),
                Arrangement.Bottom,
                Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Developed with ❤️ in Jazireh \uD83C\uDFDD️", modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Black,
                    fontStyle = FontStyle.Normal, style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

    }
}

@Composable
fun ChipsTable(playersCount: Int) {

    Card(
        border = BorderStroke(4.dp, Color.Black),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .weight(weight =1f, fill = false)) {

//            .verticalScroll(rememberScrollState())
            ChipListItems(playersCount)
//            ChipRows(playersCount)

//
        }
    }

}

@Composable
fun ChipRows(playersCount: Int) {

    val red = RED_CHIPS_COUNT.div(playersCount)
    val blue = BLUE_CHIPS_COUNT.div(playersCount)
    val green = GREEN_CHIPS_COUNT.div(playersCount)
    val yellow = YELLOW_CHIPS_COUNT.floorDiv(playersCount)

    val redRemained = RED_CHIPS_COUNT % (playersCount)
    val blueRemained = BLUE_CHIPS_COUNT % (playersCount)
    val greenRemained = GREEN_CHIPS_COUNT % (playersCount)
    val yellowRemained = YELLOW_CHIPS_COUNT % (playersCount)
    ChipRow("100$", Color.Red, Color.White, red, redRemained)
    ChipRow("50 $", Color.Blue, Color.White, blue, blueRemained)
    ChipRow("25 $", Color.Green, Color.Black, green, greenRemained)
    ChipRow("10 $", Color.Yellow, Color.Black, yellow, yellowRemained)

}

@Composable
fun ChipListItems(playersCount: Int) {

    val red = RED_CHIPS_COUNT.div(playersCount)
    val blue = BLUE_CHIPS_COUNT.div(playersCount)
    val green = GREEN_CHIPS_COUNT.div(playersCount)
    val yellow = YELLOW_CHIPS_COUNT.floorDiv(playersCount)

    val redRemained = RED_CHIPS_COUNT % (playersCount)
    val blueRemained = BLUE_CHIPS_COUNT % (playersCount)
    val greenRemained = GREEN_CHIPS_COUNT % (playersCount)
    val yellowRemained = YELLOW_CHIPS_COUNT % (playersCount)
    ChipListItem("100$", Color.Red, Color.White, red, redRemained)
    ChipListItem("50 $", Color.Blue, Color.White, blue, blueRemained)
    ChipListItem("25 $", Color.Green, Color.Black, green, greenRemained)
    ChipListItem("10 $", Color.Yellow, Color.Black, yellow, yellowRemained)
}

@Composable
fun ChipsRow(playersCount: Int) {
    Row(modifier = Modifier.height(200.dp)) {


        Row(modifier = Modifier.fillMaxWidth()) {
            val red = 38.div(playersCount)
            val blue = 67.div(playersCount)
            val green = 100.div(playersCount)
            val yellow = 33.floorDiv(playersCount)

            val redRemained = 38 % (playersCount)
            val blueRemained = 67 % (playersCount)
            val greenRemained = 100 % (playersCount)
            val yellowRemained = 33 % (playersCount)

            Chip("100", Color.Red, Color.White, red, redRemained)
            Chip("50", Color.Blue, Color.White, blue, blueRemained)
            Chip("25", Color.Green, Color.Black, green, greenRemained)
            Chip("10", Color.Yellow, Color.Black, yellow, yellowRemained)
        }

    }


}


@Composable
fun ButtonsCard(callback: (Int) -> Unit) {
    Card(
        border = BorderStroke(4.dp, Color.Black),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "How many guys want to play?",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                ),
            )
            ControllerButtons {
                callback(it)
            }
        }
    }
}

@Composable
fun Chip(
    value: String,
    color: Color,
    textColor: Color,
    count: Int,
    remained: Int
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(color, shape = RoundedCornerShape(16.dp))
                    .width(56.dp)
                    .height(56.dp)
                    .padding(8.dp)
//            .align(CenterVertically)
//                        .align(alignment = Alignment.CenterHorizontally)
                    .padding(8.dp),
                text = value,
                style = TextStyle(
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "$count", Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "$remained", Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Center,
            )
//            Text(text = "$remained")

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipListItem(
    value: String,
    color: Color,
    textColor: Color,
    count: Int,
    remained: Int
) {
    Column {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            headlineText = { Text(text = "$count each person") },
            supportingText = { Text(text = "$remained chips are remained") },
            colors = ListItemDefaults.colors(containerColor = Color(0xFF45464f)),
            leadingContent = {
                CircleShape(
                    text = value,
                    color = color,
                    textColor = textColor
                )
            },
        )
        Divider(color = Color.Black, thickness = 3.dp)
    }
}


@Composable
fun ChipRow(
    value: String,
    color: Color,
    textColor: Color,
    count: Int,
    remained: Int
) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            CircleShape(color, textColor, value)
            Column(modifier = Modifier.widthIn(min = Dp.Unspecified, max = Dp.Infinity)) {
                Text(
                    text = "$count each person", Modifier.padding(8.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 17.sp
                    ),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "$remained chips are remained", Modifier.padding(8.dp),
                    style = TextStyle(
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Divider(color = Color.Black, thickness = 3.dp)
    }
}


@Composable
fun ConstraintLayoutContent() {

}


@Composable
fun CircleShape(
    color: Color,
    textColor: Color, text: String,
    fontSize: TextUnit = 15.sp
) {
    Box(modifier = Modifier.padding(8.dp)) {
        Canvas(modifier = Modifier.size(50.dp), onDraw = {
            val size = 48.dp.toPx()
            drawCircle(
                color = color,
                radius = size / 2f
            )
        })
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            style = TextStyle(
                color = textColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = fontSize
            )
        )
    }
}

@Composable
fun RoundedShape(
    color: Color,
    textColor: Color, text: String,
    fontSize: TextUnit = 15.sp,
    callback: (() -> Unit)? = null
) {
    Box(modifier = Modifier.padding(8.dp)) {
        Canvas(modifier = Modifier.size(50.dp), onDraw = {
            val size = 42.dp.value
            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(size, size)
            )
        })
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    callback?.let { it() }
                },
            style = TextStyle(
                color = textColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = fontSize
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(callback: (Int) -> Unit) {

    val text = remember { mutableStateOf("1") }
    Column {
        TextField(
            modifier = Modifier,
            value = text.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            onValueChange = { newText ->
                text.value = newText

                if (text.value.isNotEmpty())
                    callback(text.value.toInt())
            },
            label = { Text("چند نفر می خوان بازی کنن؟") }
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


@Composable
fun ControllerButtons(callback: (Int) -> Unit) {
    val text = remember { mutableStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        Arrangement.Center,
        Alignment.CenterVertically
    ) {

        ControlsButton(text = "-", color = Color(0xFFFF2011)) {
            if (text.value > 1) {
                text.value--
                callback(text.value)
            }
        }

        RoundedShape(
            color = Color.White,
            textColor = Color.Black, text = text.value.toString(), fontSize = 32.sp
        )

        ControlsButton(text = "+", color = Color(0x9900FF22)) {
            if (text.value < 10) {
                text.value++
                callback(text.value)
            }
        }


        /*Text(
            text = "+",
            Modifier
                .clickable {
                    if (text.value < 10) {
                        text.value++
                        callback(text.value)
                    }
                }
                .border(2.dp, Color.Black)
                .width(70.dp)
                .background(Color(0x9900FF22))
                .size(42.dp)
                .height(70.dp),
            color = Color.Black,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )*/
    }

}


@Composable
fun ControlsButton(text: String, color: Color, callback: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart,
    ) {
        RoundedShape(
            color = color,
            textColor = Color.White,
            text = text,
            fontSize = 32.sp,
            callback = callback
        )

        /*
        Text(
            text = text,
            Modifier
                .clickable {
                    callback()
                }
                .border(2.dp, Color.Black)
                .width(70.dp)
                .background(color)
                .size(42.dp)
                .height(70.dp),
            color = Color.Black,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )*/
    }
}


/*


*/
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*//*


*/
/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChipsDivisiveTheme {
        Greeting("Android")
    }
}*//*


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChipsDivisiveTheme {
//        Greeting("Android")
        MainScreen()

    }
}*/
