package com.chips.divisive.model

data class ProfileListState(val value: List<ProfileWithItsChips> = emptyList(), val isLoading: Boolean)