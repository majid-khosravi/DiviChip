package com.chips.divisive.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
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
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel,
    chipIdCallback: (chip: ChipModel) -> Unit
) {
//    viewModel.findProfileById(n)
    val stat by viewModel.stat.collectAsStateWithLifecycle()

    if (stat.isLoading) {
        Log.d("TAG", "ProfileListScreen: ${stat.isLoading}")
    } else {
        stat.value?.let {
            ProfileCardRow(it, chipIdCallback = chipIdCallback)
        }
    }
}


@Composable
fun ProfileCardRow(
    item: ProfileWithItsChips,
    profileIdCallback: ((profileId: Int) -> Unit)? = null,
    chipIdCallback: ((chip: ChipModel) -> Unit)? = null,
    calculateCallback: ((chipId: Int) -> Unit)? = null
) {
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
                profileIdCallback?.let {
                    IconButton(onClick = { profileIdCallback.invoke(item.profile.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null
                        )
                    }
                }
            }
            ChipList(items = item.chips, chipIdCallback)
            calculateCallback?.let {
                Button(
                    onClick = { calculateCallback.invoke(item.profile.id) }
                ) {
                    Text(text = "Calculate this")
                }
            }
        }
    }
}