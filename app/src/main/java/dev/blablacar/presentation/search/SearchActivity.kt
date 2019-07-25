package dev.blablacar.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.nonNullObserveConsume
import com.google.android.material.snackbar.Snackbar
import dev.blablacar.R
import dev.blablacar.presentation.rides.RidesActivity
import dev.mesmoustaches.android.view.linkVisibilityTo
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        loader.linkVisibilityTo(viewModel.loadingLiveData, this)

        nonNullObserveConsume(viewModel.errorLiveData) {
            Timber.e("Error: $it")
            Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setListeners() {
        fab?.setOnClickListener {
            startActivity(
                RidesActivity.createIntent(
                    this,
                    startCity.text.toString(),
                    stopCity.text.toString()
                )
            )
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}