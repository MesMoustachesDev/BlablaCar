package dev.blablacar.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.nonNullObserve
import androidx.lifecycle.nonNullObserveConsume
import com.google.android.material.snackbar.Snackbar
import dev.blablacar.R
import dev.blablacar.presentation.routing.FilterScreen
import dev.mesmoustaches.android.view.linkVisibilityTo
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RidesActivity : AppCompatActivity() {
    private val viewModel: RidesActivityViewModel by viewModel()
    private val filterScreen by inject<FilterScreen>()

    private val ridesAdapter: RidesAdapter by lazy {
        RidesAdapter {
            viewModel.loadMore()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setObservers()
        setListeners()

        homeRecyclerView.adapter = ridesAdapter
    }

    private fun setObservers() {
        loader.linkVisibilityTo(viewModel.loadingLiveData, this)

        nonNullObserveConsume(viewModel.errorLiveData) {
            Timber.e("Error: $it")
            Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
        }

        nonNullObserve(viewModel.ridesLiveData) {
            ridesAdapter.update(it)
        }
    }

    private fun setListeners() {
        refresh?.setOnRefreshListener {
            viewModel.refresh(forceUpdate = true)
            refresh?.isRefreshing = false
        }

        fab?.setOnClickListener {
            startActivity(filterScreen.getIntent())
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, RidesActivity::class.java)
        }
    }
}