package com.chips.divisive

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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

@Composable
fun MainScreen() {
    Box(
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_home),
                    contentScale = ContentScale.FillBounds
                )
        }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val playersCount = remember {
                mutableStateOf(1)
            }

            ButtonsCard {
                playersCount.value = it
            }

            ChipsTable(playersCount.value)
//            ChipsRow(playersCount.value)


            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(
                    text = "Developed with <3 in the Island", modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Black,
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }
}

@Composable
fun ChipsTable(playersCount: Int) {

    Card(
        border = BorderStroke(1.dp, Color.White),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {


            val red = 38.div(playersCount)
            val blue = 67.div(playersCount)
            val green = 100.div(playersCount)
            val yellow = 33.floorDiv(playersCount)

            val redRemained = 38 % (playersCount)
            val blueRemained = 67 % (playersCount)
            val greenRemained = 100 % (playersCount)
            val yellowRemained = 33 % (playersCount)

            ChipRow("100", Color.Red, Color.White, red, redRemained)
            Divider(color = Color.White, thickness = 1.dp)

            ChipRow("50", Color.Blue, Color.White, blue, blueRemained)
            Divider(color = Color.White, thickness = 1.dp)

            ChipRow("25", Color.Green, Color.Black, green, greenRemained)
            Divider(color = Color.White, thickness = 1.dp)

            ChipRow("10", Color.Yellow, Color.Black, yellow, yellowRemained)

        }
    }

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
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
//                text = "تعداد مورد نظر را انتخاب کنید",
                text = "How many guys want to play? ",
                modifier = Modifier
                    .padding(16.dp)
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

@Composable
fun ChipRow(
    value: String,
    color: Color,
    textColor: Color,
    count: Int,
    remained: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        CircleShape(color, textColor, value)
        Column(modifier = Modifier.widthIn(min = Dp.Unspecified, max = Dp.Infinity)) {
            Text(
                text = "$count each one", Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Remained: $remained", Modifier.padding(8.dp),
                style = TextStyle(
                    color = Color.LightGray,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center,
            )
        }


//            ListItem()

        /*    ListItem(
                text = { Text("Two line list item with 40x40 icon") },
                secondaryText = { Text("Secondary text") },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            )*/
        /*            Text(
                        modifier = Modifier
                            .background(color, shape = RoundedCornerShape(36.dp))
                            .width(72.dp)
                            .height(72.dp)
                            .padding(8.dp)
                            .padding(8.dp),
                        text = value,
                        style = TextStyle(
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        ),
                        textAlign = TextAlign.Center,
                    )*/

//            Text(text = "$remained")


    }
}


@Composable
fun ConstraintLayoutContent() {

}


@Composable
fun CircleShape(
    color: Color,
    textColor: Color, text: String
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
                fontSize = 15.sp
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
            .padding(8.dp),
        Arrangement.Center,
        Alignment.CenterVertically
    ) {
        Text(
            text = "-",
            Modifier
                .width(70.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    if (text.value > 1) {
                        text.value--
                        callback(text.value)
                    }
                }
                .border(2.dp, Color.Black)
                .size(42.dp)
                .background(Color.Red)
                .height(70.dp),
            color = Color.Black,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = text.value.toString(),
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .size(42.dp)
                .background(Color.White)
                .align(Alignment.CenterVertically)
                .height(70.dp),
            color = Color.Black,
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
        )
        Text(
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
        )
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
