package com.vigurskiy.smotripogoduapp.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.vigurskiy.smotripogoduapp.R
import com.vigurskiy.smotripogoduapp.di.component.WeatherComponent
import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.util.toCelcius
import com.vigurskiy.smotripogoduapp.viewmodel.ForecastViewModelFactory
import com.vigurskiy.smotripogoduapp.viewmodel.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.slf4j.LoggerFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    CanShowDayForecastDetails {

    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory

    override val selectedDayForecast: DayForecast?
        get() = mainActivityViewModel.selectedDayForecast

    private val logger = LoggerFactory.getLogger(MainActivity::class.java)

    private val compositeDisposable = CompositeDisposable()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherComponent.initAndGet().inject(this)

        mainActivityViewModel = ViewModelProviders
            .of(this, forecastViewModelFactory)
            .get(MainActivityViewModel::class.java)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val cityName = mainActivityViewModel.selectedCityName
            ?: resources.getStringArray(R.array.city_names).first()

        showCurrentWeatherForCity(cityName)

        if(supportFragmentManager.findFragmentByTag(DAY_FORECAST_FRAGMENT_TAG) == null)
            showFiveDayForecastForCity(cityName)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        logger.debug("[onNavigationItemSelected] City selected: city=[{}]", item.toString())

        val cityName = when (item.itemId) {
            R.id.item_spb -> getString(R.string.spb)
            R.id.item_msk -> getString(R.string.msk)
            R.id.item_vyborg -> getString(R.string.vyborg)
            R.id.item_sochi -> getString(R.string.sochi)
            else -> return false
        }

        showCurrentWeatherForCity(cityName)
        showFiveDayForecastForCity(cityName)

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun showDayForecastDetails(dayForecast: DayForecast) {
        mainActivityViewModel.selectedDayForecast = dayForecast

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.forecast_fragment,
                DayForecastFragment.newInstance(),
                DAY_FORECAST_FRAGMENT_TAG
            )
            .commit()
    }

    private fun showCurrentWeatherForCity(cityName: String){
        compositeDisposable.add(
                mainActivityViewModel.queryCurrentWeatherCached(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weather ->
                        txt_temp?.text = weather.temp?.toCelcius()
                        txt_description?.text = weather.description
                    },
                    { logger.debug("[onCreate] Sorry, failed to preload current weather", it)}
                )
        )

    }

    private fun showFiveDayForecastForCity(cityName: String) {
        compositeDisposable.add(
            mainActivityViewModel.selectCityId(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        lt_appbar?.setExpanded(true, true)
                        toolbar?.title = cityName
                        supportFragmentManager.popBackStackImmediate(
                            null,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        supportFragmentManager
                            .beginTransaction()
                            .replace(
                                R.id.forecast_fragment,
                                FiveDayForecastFragment.newInstance(it)
                            )
                            .commit()
                    },
                    {
                        logger.warn("[onNavigationItemSelected] Error while fetching city id: cityName=[${cityName}]")
                    }
                )
        )
    }

    companion object{
        private const val DAY_FORECAST_FRAGMENT_TAG = "day_forecast"
    }
}


