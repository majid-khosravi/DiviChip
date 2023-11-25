package com.chips.divisive.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.ProfileWithItsChips

@Composable
fun ProfileListScreen(
    viewModel: MainViewModel,
    callback: (profileId: Int) -> Unit
) {
    val stat by viewModel.stat.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (stat.isLoading) {
        Log.d("TAG", "ProfileListScreen: ${stat.isLoading}")
    } else {
        ProfileList(stat.value) {
            callback.invoke(it)
            MyToast(context, "Item: $it clicked!")
        }
    }
}

fun MyToast(context: Context, message: String) {
    makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun ProfileList(items: List<ProfileWithItsChips>, callback: (profileId: Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        items.forEach {
            Row {
                ProfileCardRow(it) {
                    callback.invoke(it)
                }
            }
        }
    }
}


@Composable
fun ProfileCardRow(item: ProfileWithItsChips, callback: (profileId: Int) -> Unit) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f, fill = true),
                    text = item.profile.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp
                    )
                )
                IconButton(onClick = { callback.invoke(item.profile.id) }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )
                }
            }
            ProfileChipRow(items = item.chips, true)
        }
    }
}




