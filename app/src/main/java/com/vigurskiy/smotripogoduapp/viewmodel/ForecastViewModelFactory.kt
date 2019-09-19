package com.vigurskiy.smotripogoduapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Provider


class ForecastViewModelFactory
@Inject constructor(
    private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    private val logger = LoggerFactory.getLogger(ForecastViewModelFactory::class.java)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {

            logger.trace("[create] Creating viewmodel instance: clazz=[$modelClass]")

            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}