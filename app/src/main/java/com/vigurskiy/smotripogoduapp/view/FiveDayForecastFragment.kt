package com.vigurskiy.smotripogoduapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.vigurskiy.smotripogoduapp.R
import com.vigurskiy.smotripogoduapp.di.component.WeatherComponent
import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.view.adapter.FiveDayForecastAdapter
import com.vigurskiy.smotripogoduapp.view.adapter.FiveDayForecastAdapter.*
import com.vigurskiy.smotripogoduapp.viewmodel.FiveDayForecastViewModel
import com.vigurskiy.smotripogoduapp.viewmodel.ForecastViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_five_day_forecast.*
import org.slf4j.LoggerFactory
import javax.inject.Inject

class FiveDayForecastFragment : BaseFragment(), OnForecastClickListener {

    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory

    private val logger = LoggerFactory.getLogger(FiveDayForecastFragment::class.java)

    private lateinit var fiveDayForecastViewModel: FiveDayForecastViewModel

    private lateinit var fiveDayForecastAdapter: FiveDayForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WeatherComponent.get().inject(this)

        fiveDayForecastViewModel = ViewModelProviders
            .of(this, forecastViewModelFactory)
            .get(FiveDayForecastViewModel::class.java)
            .also {
                it.init(arguments?.getString(KEY_CITY_ID) ?: "")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_five_day_forecast, container, false)

        val swipe = root.findViewById<SwipeRefreshLayout>(R.id.sw_container)

        swipe.setOnRefreshListener(::refreshForecast)

        val forecastList = root.findViewById<RecyclerView>(R.id.rv_forecast_list)
        fiveDayForecastAdapter = FiveDayForecastAdapter().apply {
            onForecastClickListener = this@FiveDayForecastFragment
        }

        forecastList.apply {
            layoutManager = LinearLayoutManager(this@FiveDayForecastFragment.context)
            adapter = fiveDayForecastAdapter
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshForecast()
    }

    override fun dayForecastClicked(dayForecast: DayForecast) {
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            (activity as CanShowDayForecastDetails).showDayForecastDetails(dayForecast)
        }
    }

    private fun refreshForecast(){
        addDisposable(
            fiveDayForecastViewModel.fiveDayForecastObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    sw_container.isRefreshing = true
                }
                .doFinally {
                    sw_container.isRefreshing = false
                }
                .subscribe { result, error ->
                    if(result != null){
                        fiveDayForecastAdapter.forecast = result
                    }

                    if (error != null){
                        logger.warn("Error occured while data fetch", error)
                    }
                }
        )
    }

    companion object {
        private const val KEY_CITY_ID =
            "com.vigurskiy.smotripogoduapp.ui.FiveDayForecastFragment.KEY_CITY_ID"

        fun newInstance(cityId: String): FiveDayForecastFragment =
            with(FiveDayForecastFragment()) {
                Bundle().also { bundle ->
                    bundle.putString(KEY_CITY_ID, cityId)
                    arguments = bundle
                }

                this
            }
    }
}