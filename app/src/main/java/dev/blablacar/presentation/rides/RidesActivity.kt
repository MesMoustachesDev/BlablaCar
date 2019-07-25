package dev.blablacar.presentation.rides

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.nonNullObserve
import androidx.lifecycle.nonNullObserveConsume
import com.google.android.material.snackbar.Snackbar
import dev.blablacar.R
import dev.blablacar.presentation.search.SearchActivity
import dev.mesmoustaches.android.view.linkVisibilityTo
import kotlinx.android.synthetic.main.activity_rides.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RidesActivity : AppCompatActivity() {
    private val viewModel: RidesActivityViewModel by viewModel()
    private lateinit var params: Params

    private val ridesAdapter: RidesAdapter by lazy {
        RidesAdapter {
            viewModel.loadMore(
                startCity = params.startCity ?: "",
                stopCity = params.stopCity ?: ""
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rides)

        params = extractFromIntent(intent)

        viewModel.refresh(
            startCity = params.startCity ?: "",
            stopCity = params.stopCity ?: ""
        )

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
            viewModel.refresh(
                forceUpdate = true,
                startCity = params.startCity ?: "",
                stopCity = params.stopCity ?: ""
            )
            refresh?.isRefreshing = false
        }

        fab?.setOnClickListener {
            startActivity(SearchActivity.createIntent(this))
        }
    }

    companion object {
        private const val START_CITY = "START_CITY"
        private const val STOP_CITY = "STOP_CITY"

        fun createIntent(context: Context, startCity: String, stopCity: String): Intent {
            val intent = Intent(context, RidesActivity::class.java)
            intent.putExtra(START_CITY, startCity)
            intent.putExtra(START_CITY, stopCity)
            return intent
        }

        fun extractFromIntent(intent: Intent) = Params(
            intent.getStringExtra(START_CITY),
            intent.getStringExtra(STOP_CITY)
        )

        data class Params(
            val startCity: String?,
            val stopCity: String?
        )
    }
}