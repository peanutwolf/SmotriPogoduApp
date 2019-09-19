package com.vigurskiy.smotripogoduapp.service.openweathermap

import com.google.gson.Gson
import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.service.WeatherNetRepository
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.forecast.FiveDayForecastOpenWeatherMap
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather.CurrentWeatherOpenWeatherMap
import com.vigurskiy.smotripogoduapp.util.toFiveDayForecastData
import com.vigurskiy.smotripogoduapp.util.toThreeHourForecast
import io.reactivex.Single

class OwmMockRepositoryImpl() : WeatherNetRepository {
    override fun queryFiveDayForecast(cityId: String): Single<FiveDayForecastData> =
        Single.just(MOCK_FIVEDAY_FORECAST)
            .map {
                Gson()
                    .fromJson(it, FiveDayForecastOpenWeatherMap::class.java)
                    .toFiveDayForecastData()
            }


    override fun queryCurrentWeather(cityId: String): Single<ThreeHourForecast> =
        Single.just(MOCK_CURRENT_WEATHER_FORECAST)
            .map {
                Gson()
                    .fromJson(it, CurrentWeatherOpenWeatherMap::class.java)
                    .toThreeHourForecast()
            }

    companion object {
        private val MOCK_CURRENT_WEATHER_FORECAST = """
            {"coord": { "lon": 139,"lat": 35},
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "base": "stations",
              "main": {
                "temp": 289.92,
                "pressure": 1009,
                "humidity": 92,
                "temp_min": 288.71,
                "temp_max": 290.93
              },
              "wind": {
                "speed": 0.47,
                "deg": 107.538
              },
              "clouds": {
                "all": 2
              },
              "dt": 1560350192,
              "sys": {
                "type": 3,
                "id": 2019346,
                "message": 0.0065,
                "country": "JP",
                "sunrise": 1560281377,
                "sunset": 1560333478
              },
              "timezone": 32400,
              "id": 1851632,
              "name": "Shuzenji",
              "cod": 200
            }
""".trimIndent()

        private val MOCK_FIVEDAY_FORECAST = """
            {
          "cod": "200",
          "message": 0.014,
          "cnt": 40,
          "list": [
            {
              "dt": 1568376000,
              "main": {
                "temp": 292.02,
                "temp_min": 291.622,
                "temp_max": 292.02,
                "pressure": 1034.96,
                "sea_level": 1034.96,
                "grnd_level": 1029.85,
                "humidity": 47,
                "temp_kf": 0.4
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 76
              },
              "wind": {
                "speed": 3.47,
                "deg": 15.646
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-13 12:00:00"
            },
            {
              "dt": 1568386800,
              "main": {
                "temp": 293.36,
                "temp_min": 293.061,
                "temp_max": 293.36,
                "pressure": 1033.95,
                "sea_level": 1033.95,
                "grnd_level": 1028.59,
                "humidity": 37,
                "temp_kf": 0.3
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 85
              },
              "wind": {
                "speed": 2.81,
                "deg": 19.551
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-13 15:00:00"
            },
            {
              "dt": 1568397600,
              "main": {
                "temp": 290.76,
                "temp_min": 290.561,
                "temp_max": 290.76,
                "pressure": 1034.28,
                "sea_level": 1034.28,
                "grnd_level": 1028.86,
                "humidity": 46,
                "temp_kf": 0.2
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 72
              },
              "wind": {
                "speed": 3.37,
                "deg": 14.28
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-13 18:00:00"
            },
            {
              "dt": 1568408400,
              "main": {
                "temp": 288.68,
                "temp_min": 288.583,
                "temp_max": 288.68,
                "pressure": 1035.22,
                "sea_level": 1035.22,
                "grnd_level": 1030.06,
                "humidity": 65,
                "temp_kf": 0.1
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 10
              },
              "wind": {
                "speed": 2.5,
                "deg": 65.761
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-13 21:00:00"
            },
            {
              "dt": 1568419200,
              "main": {
                "temp": 287.4,
                "temp_min": 287.4,
                "temp_max": 287.4,
                "pressure": 1035.03,
                "sea_level": 1035.03,
                "grnd_level": 1029.84,
                "humidity": 74,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 2.13,
                "deg": 71.838
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-14 00:00:00"
            },
            {
              "dt": 1568430000,
              "main": {
                "temp": 286.2,
                "temp_min": 286.2,
                "temp_max": 286.2,
                "pressure": 1033.87,
                "sea_level": 1033.87,
                "grnd_level": 1028.69,
                "humidity": 74,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 0
              },
              "wind": {
                "speed": 1.61,
                "deg": 42.937
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-14 03:00:00"
            },
            {
              "dt": 1568440800,
              "main": {
                "temp": 285.227,
                "temp_min": 285.227,
                "temp_max": 285.227,
                "pressure": 1033.25,
                "sea_level": 1033.25,
                "grnd_level": 1028.28,
                "humidity": 84,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 1
              },
              "wind": {
                "speed": 1.91,
                "deg": 44.915
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-14 06:00:00"
            },
            {
              "dt": 1568451600,
              "main": {
                "temp": 289.454,
                "temp_min": 289.454,
                "temp_max": 289.454,
                "pressure": 1033.12,
                "sea_level": 1033.12,
                "grnd_level": 1028.04,
                "humidity": 70,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02d"
                }
              ],
              "clouds": {
                "all": 22
              },
              "wind": {
                "speed": 1.74,
                "deg": 64.126
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-14 09:00:00"
            },
            {
              "dt": 1568462400,
              "main": {
                "temp": 294.259,
                "temp_min": 294.259,
                "temp_max": 294.259,
                "pressure": 1031.5,
                "sea_level": 1031.5,
                "grnd_level": 1026.2,
                "humidity": 44,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02d"
                }
              ],
              "clouds": {
                "all": 23
              },
              "wind": {
                "speed": 0.85,
                "deg": 83.428
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-14 12:00:00"
            },
            {
              "dt": 1568473200,
              "main": {
                "temp": 295.166,
                "temp_min": 295.166,
                "temp_max": 295.166,
                "pressure": 1029.4,
                "sea_level": 1029.4,
                "grnd_level": 1024.12,
                "humidity": 37,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 34
              },
              "wind": {
                "speed": 1.19,
                "deg": 296.371
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-14 15:00:00"
            },
            {
              "dt": 1568484000,
              "main": {
                "temp": 292.245,
                "temp_min": 292.245,
                "temp_max": 292.245,
                "pressure": 1028.36,
                "sea_level": 1028.36,
                "grnd_level": 1023.07,
                "humidity": 47,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02d"
                }
              ],
              "clouds": {
                "all": 18
              },
              "wind": {
                "speed": 1.48,
                "deg": 315.984
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-14 18:00:00"
            },
            {
              "dt": 1568494800,
              "main": {
                "temp": 289.661,
                "temp_min": 289.661,
                "temp_max": 289.661,
                "pressure": 1028.36,
                "sea_level": 1028.36,
                "grnd_level": 1023.17,
                "humidity": 56,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 1
              },
              "wind": {
                "speed": 0.83,
                "deg": 250.602
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-14 21:00:00"
            },
            {
              "dt": 1568505600,
              "main": {
                "temp": 287.907,
                "temp_min": 287.907,
                "temp_max": 287.907,
                "pressure": 1027.58,
                "sea_level": 1027.58,
                "grnd_level": 1022.42,
                "humidity": 59,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 0
              },
              "wind": {
                "speed": 1.87,
                "deg": 284.066
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-15 00:00:00"
            },
            {
              "dt": 1568516400,
              "main": {
                "temp": 286.719,
                "temp_min": 286.719,
                "temp_max": 286.719,
                "pressure": 1026.24,
                "sea_level": 1026.24,
                "grnd_level": 1021.08,
                "humidity": 68,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 0
              },
              "wind": {
                "speed": 1.96,
                "deg": 253.79
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-15 03:00:00"
            },
            {
              "dt": 1568527200,
              "main": {
                "temp": 285.713,
                "temp_min": 285.713,
                "temp_max": 285.713,
                "pressure": 1025.85,
                "sea_level": 1025.85,
                "grnd_level": 1020.81,
                "humidity": 64,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02d"
                }
              ],
              "clouds": {
                "all": 18
              },
              "wind": {
                "speed": 1.73,
                "deg": 244.085
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-15 06:00:00"
            },
            {
              "dt": 1568538000,
              "main": {
                "temp": 290.8,
                "temp_min": 290.8,
                "temp_max": 290.8,
                "pressure": 1025.77,
                "sea_level": 1025.77,
                "grnd_level": 1020.46,
                "humidity": 50,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 0
              },
              "wind": {
                "speed": 1.69,
                "deg": 247.015
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-15 09:00:00"
            },
            {
              "dt": 1568548800,
              "main": {
                "temp": 295.8,
                "temp_min": 295.8,
                "temp_max": 295.8,
                "pressure": 1024.55,
                "sea_level": 1024.55,
                "grnd_level": 1019.39,
                "humidity": 44,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 1
              },
              "wind": {
                "speed": 1.78,
                "deg": 269.357
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-15 12:00:00"
            },
            {
              "dt": 1568559600,
              "main": {
                "temp": 297.428,
                "temp_min": 297.428,
                "temp_max": 297.428,
                "pressure": 1023.34,
                "sea_level": 1023.34,
                "grnd_level": 1017.89,
                "humidity": 44,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 29
              },
              "wind": {
                "speed": 2.51,
                "deg": 276.97
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-15 15:00:00"
            },
            {
              "dt": 1568570400,
              "main": {
                "temp": 294.3,
                "temp_min": 294.3,
                "temp_max": 294.3,
                "pressure": 1022.41,
                "sea_level": 1022.41,
                "grnd_level": 1017.04,
                "humidity": 59,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 30
              },
              "wind": {
                "speed": 2.44,
                "deg": 278.24
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-15 18:00:00"
            },
            {
              "dt": 1568581200,
              "main": {
                "temp": 291.078,
                "temp_min": 291.078,
                "temp_max": 291.078,
                "pressure": 1023.27,
                "sea_level": 1023.27,
                "grnd_level": 1018.29,
                "humidity": 77,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 87
              },
              "wind": {
                "speed": 3.06,
                "deg": 291.541
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-15 21:00:00"
            },
            {
              "dt": 1568592000,
              "main": {
                "temp": 289.522,
                "temp_min": 289.522,
                "temp_max": 289.522,
                "pressure": 1022.31,
                "sea_level": 1022.31,
                "grnd_level": 1017.48,
                "humidity": 86,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 70
              },
              "wind": {
                "speed": 2.03,
                "deg": 294.822
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-16 00:00:00"
            },
            {
              "dt": 1568602800,
              "main": {
                "temp": 288.839,
                "temp_min": 288.839,
                "temp_max": 288.839,
                "pressure": 1022.2,
                "sea_level": 1022.2,
                "grnd_level": 1017.46,
                "humidity": 86,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 73
              },
              "wind": {
                "speed": 2.1,
                "deg": 299.065
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-16 03:00:00"
            },
            {
              "dt": 1568613600,
              "main": {
                "temp": 288.162,
                "temp_min": 288.162,
                "temp_max": 288.162,
                "pressure": 1021.67,
                "sea_level": 1021.67,
                "grnd_level": 1017.04,
                "humidity": 88,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 44
              },
              "wind": {
                "speed": 2.07,
                "deg": 286.299
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-16 06:00:00"
            },
            {
              "dt": 1568624400,
              "main": {
                "temp": 292.161,
                "temp_min": 292.161,
                "temp_max": 292.161,
                "pressure": 1023.03,
                "sea_level": 1023.03,
                "grnd_level": 1018.22,
                "humidity": 73,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 5
              },
              "wind": {
                "speed": 2.74,
                "deg": 298.045
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-16 09:00:00"
            },
            {
              "dt": 1568635200,
              "main": {
                "temp": 295.3,
                "temp_min": 295.3,
                "temp_max": 295.3,
                "pressure": 1022.86,
                "sea_level": 1022.86,
                "grnd_level": 1017.76,
                "humidity": 60,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03d"
                }
              ],
              "clouds": {
                "all": 38
              },
              "wind": {
                "speed": 3.39,
                "deg": 311.83
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-16 12:00:00"
            },
            {
              "dt": 1568646000,
              "main": {
                "temp": 294.846,
                "temp_min": 294.846,
                "temp_max": 294.846,
                "pressure": 1022.12,
                "sea_level": 1022.12,
                "grnd_level": 1017.04,
                "humidity": 61,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 99
              },
              "wind": {
                "speed": 2.61,
                "deg": 320.015
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-16 15:00:00"
            },
            {
              "dt": 1568656800,
              "main": {
                "temp": 293.4,
                "temp_min": 293.4,
                "temp_max": 293.4,
                "pressure": 1022.12,
                "sea_level": 1022.12,
                "grnd_level": 1017.08,
                "humidity": 68,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 89
              },
              "wind": {
                "speed": 2.23,
                "deg": 341.995
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-16 18:00:00"
            },
            {
              "dt": 1568667600,
              "main": {
                "temp": 291.5,
                "temp_min": 291.5,
                "temp_max": 291.5,
                "pressure": 1023.46,
                "sea_level": 1023.46,
                "grnd_level": 1018.58,
                "humidity": 72,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02n"
                }
              ],
              "clouds": {
                "all": 22
              },
              "wind": {
                "speed": 1.88,
                "deg": 4.207
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-16 21:00:00"
            },
            {
              "dt": 1568678400,
              "main": {
                "temp": 290.3,
                "temp_min": 290.3,
                "temp_max": 290.3,
                "pressure": 1022.88,
                "sea_level": 1022.88,
                "grnd_level": 1018.13,
                "humidity": 75,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02n"
                }
              ],
              "clouds": {
                "all": 12
              },
              "wind": {
                "speed": 1.36,
                "deg": 14.424
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-17 00:00:00"
            },
            {
              "dt": 1568689200,
              "main": {
                "temp": 289.4,
                "temp_min": 289.4,
                "temp_max": 289.4,
                "pressure": 1022.72,
                "sea_level": 1022.72,
                "grnd_level": 1017.9,
                "humidity": 79,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01n"
                }
              ],
              "clouds": {
                "all": 1
              },
              "wind": {
                "speed": 1.01,
                "deg": 330.22
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-17 03:00:00"
            },
            {
              "dt": 1568700000,
              "main": {
                "temp": 288.688,
                "temp_min": 288.688,
                "temp_max": 288.688,
                "pressure": 1022.59,
                "sea_level": 1022.59,
                "grnd_level": 1017.8,
                "humidity": 82,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 1
              },
              "wind": {
                "speed": 0.89,
                "deg": 312.278
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-17 06:00:00"
            },
            {
              "dt": 1568710800,
              "main": {
                "temp": 291.6,
                "temp_min": 291.6,
                "temp_max": 291.6,
                "pressure": 1023.92,
                "sea_level": 1023.92,
                "grnd_level": 1018.96,
                "humidity": 75,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 61
              },
              "wind": {
                "speed": 0.99,
                "deg": 333.357
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-17 09:00:00"
            },
            {
              "dt": 1568721600,
              "main": {
                "temp": 292.9,
                "temp_min": 292.9,
                "temp_max": 292.9,
                "pressure": 1023.21,
                "sea_level": 1023.21,
                "grnd_level": 1018.06,
                "humidity": 72,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 79
              },
              "wind": {
                "speed": 1.27,
                "deg": 21.927
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-17 12:00:00"
            },
            {
              "dt": 1568732400,
              "main": {
                "temp": 292.612,
                "temp_min": 292.612,
                "temp_max": 292.612,
                "pressure": 1022.5,
                "sea_level": 1022.5,
                "grnd_level": 1017.21,
                "humidity": 75,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10d"
                }
              ],
              "clouds": {
                "all": 90
              },
              "wind": {
                "speed": 1.92,
                "deg": 0.448
              },
              "rain": {
                "3h": 0.312
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-17 15:00:00"
            },
            {
              "dt": 1568743200,
              "main": {
                "temp": 291.969,
                "temp_min": 291.969,
                "temp_max": 291.969,
                "pressure": 1022.1,
                "sea_level": 1022.1,
                "grnd_level": 1016.64,
                "humidity": 69,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 500,
                  "main": "Rain",
                  "description": "light rain",
                  "icon": "10d"
                }
              ],
              "clouds": {
                "all": 71
              },
              "wind": {
                "speed": 3,
                "deg": 22.86
              },
              "rain": {
                "3h": 0.063
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-17 18:00:00"
            },
            {
              "dt": 1568754000,
              "main": {
                "temp": 289.546,
                "temp_min": 289.546,
                "temp_max": 289.546,
                "pressure": 1023.71,
                "sea_level": 1023.71,
                "grnd_level": 1018.39,
                "humidity": 84,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 801,
                  "main": "Clouds",
                  "description": "few clouds",
                  "icon": "02n"
                }
              ],
              "clouds": {
                "all": 22
              },
              "wind": {
                "speed": 3.56,
                "deg": 69.241
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-17 21:00:00"
            },
            {
              "dt": 1568764800,
              "main": {
                "temp": 288.9,
                "temp_min": 288.9,
                "temp_max": 288.9,
                "pressure": 1023.4,
                "sea_level": 1023.4,
                "grnd_level": 1018.11,
                "humidity": 78,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 802,
                  "main": "Clouds",
                  "description": "scattered clouds",
                  "icon": "03n"
                }
              ],
              "clouds": {
                "all": 46
              },
              "wind": {
                "speed": 3.02,
                "deg": 62.254
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-18 00:00:00"
            },
            {
              "dt": 1568775600,
              "main": {
                "temp": 288.4,
                "temp_min": 288.4,
                "temp_max": 288.4,
                "pressure": 1023.19,
                "sea_level": 1023.19,
                "grnd_level": 1018.05,
                "humidity": 73,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 804,
                  "main": "Clouds",
                  "description": "overcast clouds",
                  "icon": "04n"
                }
              ],
              "clouds": {
                "all": 95
              },
              "wind": {
                "speed": 3.05,
                "deg": 60.743
              },
              "sys": {
                "pod": "n"
              },
              "dt_txt": "2019-09-18 03:00:00"
            },
            {
              "dt": 1568786400,
              "main": {
                "temp": 286.759,
                "temp_min": 286.759,
                "temp_max": 286.759,
                "pressure": 1023.92,
                "sea_level": 1023.92,
                "grnd_level": 1018.68,
                "humidity": 77,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 803,
                  "main": "Clouds",
                  "description": "broken clouds",
                  "icon": "04d"
                }
              ],
              "clouds": {
                "all": 65
              },
              "wind": {
                "speed": 2.86,
                "deg": 55.815
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-18 06:00:00"
            },
            {
              "dt": 1568797200,
              "main": {
                "temp": 289.346,
                "temp_min": 289.346,
                "temp_max": 289.346,
                "pressure": 1024.9,
                "sea_level": 1024.9,
                "grnd_level": 1019.63,
                "humidity": 64,
                "temp_kf": 0
              },
              "weather": [
                {
                  "id": 800,
                  "main": "Clear",
                  "description": "clear sky",
                  "icon": "01d"
                }
              ],
              "clouds": {
                "all": 2
              },
              "wind": {
                "speed": 3.38,
                "deg": 66.153
              },
              "sys": {
                "pod": "d"
              },
              "dt_txt": "2019-09-18 09:00:00"
            }
          ],
          "city": {
            "id": 2643743,
            "name": "London",
            "coord": {
              "lat": 51.5085,
              "lon": -0.1258
            },
            "country": "GB",
            "population": 1000000,
            "timezone": 3600,
            "sunrise": 1568352699,
            "sunset": 1568398908
          }
        }
""".trimIndent()
    }
}