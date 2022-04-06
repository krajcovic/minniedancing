package cz.krajcovic.dancingminnie.domains

import cz.krajcovic.dancingminnie.repositories.PicturesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DefaultUseCase @Inject constructor(private val picturesRepository: PicturesRepository) {
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    suspend operator fun invoke() = withContext(defaultDispatcher) {
        // Long-running blocking operations happen on a background thread.
        Timber.i("DefaultUseCase invoke")
    }
}