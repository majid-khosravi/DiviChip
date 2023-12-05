package com.chips.divisive.model

data class ChipMakerState(
    val value: ChipModel,
    val isLoading: Boolean,
    val isSuccess: Boolean = false,
)