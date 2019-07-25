package dev.blablacar.presentation.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dev.blablacar.presentation.common.BaseViewModel

class SearchActivityViewModel(
    context: Context
) : BaseViewModel(context) {

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
}