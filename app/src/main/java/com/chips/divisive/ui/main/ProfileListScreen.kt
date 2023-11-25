package com.chips.divisive.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        ProfileList(stat.value, callback)
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
                ProfileCardRow(it, profileIdCallback = callback)
            }
        }
    }
}







