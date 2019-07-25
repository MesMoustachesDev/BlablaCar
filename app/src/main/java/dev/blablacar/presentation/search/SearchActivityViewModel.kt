package dev.blablacar.presentation.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dev.blablacar.domain.usecase.GetRidesUseCase
import dev.blablacar.presentation.common.BaseViewModel
import kotlinx.coroutines.Job

class SearchActivityViewModel(
    private val rideLiveDataUseCase: GetRidesUseCase,
    context: Context
) : BaseViewModel(context) {

    private var localJob: Job? = null

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val ridesLiveData: LiveData<Boolean> =
        Transformations.map(rideLiveDataUseCase.data) { result ->
            result.values?.let {
                it.size > 0
            } ?: null
        }

//    fun refresh(forceUpdate: Boolean = false,
//                startCity: String,
//                stopCity: String) {
//        localJob = launchDataLoad(
//            loadingLiveData,
//            errorLiveData,
//            getError
//        ) {
//            rideLiveDataUseCase.execute(GetRidesUseCase.Params(forceUpdate = forceUpdate,
//                startCity = startCity,
//                stopCity = stopCity))
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        localJob?.cancel()
    }
}