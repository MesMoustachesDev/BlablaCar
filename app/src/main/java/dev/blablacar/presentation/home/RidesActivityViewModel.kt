package dev.blablacar.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.launchDataLoad
import dev.blablacar.domain.usecase.GetRidesUseCase
import dev.blablacar.presentation.common.BaseViewModel
import kotlinx.coroutines.Job

class RidesActivityViewModel(
    private val rideLiveDataUseCase: GetRidesUseCase,
    context: Context
) : BaseViewModel(context) {

    private var localJob: Job? = null
    private var oldSize = -1

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val ridesLiveData: LiveData<List<RidesAdapter.Cell>> =
        Transformations.map(rideLiveDataUseCase.data) { result ->
            if (result.fullLoaded) {
                result.values?.map { it.toCell() }
            } else {
                val arrayList: ArrayList<RidesAdapter.Cell> = ArrayList(result.values?.map { it.toCell() })
                arrayList.add(RidesAdapter.Cell.NeedMore)
                arrayList
            }
        }

    init {
        oldSize = -1
        refresh()
    }

    fun refresh(forceUpdate: Boolean = false) {
        localJob = launchDataLoad(
            loadingLiveData,
            errorLiveData,
            getError
        ) {
            rideLiveDataUseCase.execute(GetRidesUseCase.Params(forceUpdate = forceUpdate))
        }
    }

    override fun onCleared() {
        super.onCleared()
        localJob?.cancel()
    }

    fun loadMore() {
        localJob = launchDataLoad(
            null,
            errorLiveData,
            getError
        ) {
            rideLiveDataUseCase.execute(GetRidesUseCase.Params(loadMore = true))
        }
    }
}