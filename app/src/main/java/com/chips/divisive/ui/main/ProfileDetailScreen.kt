package com.chips.divisive.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chips.divisive.model.ProfileWithItsChips

@Composable
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel
) {
//    viewModel.findProfileById(n)
    val stat by viewModel.stat.collectAsStateWithLifecycle()

    if (stat.isLoading) {
        Log.d("TAG", "ProfileListScreen: ${stat.isLoading}")
    } else {
        stat.value?.let {
            ProfileCard(it)
        }
        /*ProfileList(stat.value) {
            callback.invoke(it)
//            MyToast(context, "Item: $it clicked!")
        }*/
    }

}


@Composable
fun ProfileCard(item: ProfileWithItsChips) {
    Card(modifier = Modifier
        .padding(16.dp)
        .background(Color.Cyan)) {
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
                        fontSize = 26.sp
                    )
                )

            }
            ProfileCardRow(items = item.chips)
        }
    }
}