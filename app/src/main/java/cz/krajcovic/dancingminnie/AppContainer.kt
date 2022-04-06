package cz.krajcovic.dancingminnie

import android.app.Application
import cz.krajcovic.dancingminnie.repositories.PicturesRepository
import cz.krajcovic.dancingminnie.services.LongRunningService
import cz.krajcovic.dancingminnie.viewmodels.MinnieViewModelImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppContainer @Inject constructor() {
    var isDebug: Boolean = BuildConfig.DEBUG

    val picturesRepository: PicturesRepository by lazy {
        PicturesRepository(LongRunningService())
    }

    val viewModel: MinnieViewModelImpl by lazy {
        MinnieViewModelImpl(picturesRepository)
    }
}