package com.chips.divisive.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chips.divisive.model.ChipModel


@Composable
fun ChipList(
    items: List<ChipModel>?,
    callback: ((chip: ChipModel) -> Unit)? = null
) {
    Column(modifier = Modifier.padding(8.dp)) {
        items?.forEach {
            ProfileChipRow(it, callback)
        }
    }
}

@Composable
fun ProfileChipRow(
    item: ChipModel,
    callback: ((chip: ChipModel) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        Arrangement.Center,
        Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f, true), text = item.value)
        callback?.let {
            IconButton(onClick = { callback.invoke(item) }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
            }
        }

//        Text(text = item.value , color = Color(item.color.toULong()))
    }
}
