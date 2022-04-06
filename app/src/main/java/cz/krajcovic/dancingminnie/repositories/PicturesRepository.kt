package cz.krajcovic.dancingminnie.repositories

import cz.krajcovic.dancingminnie.R
import cz.krajcovic.dancingminnie.services.LongRunningService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicturesRepository @Inject constructor(private val longRunningService: LongRunningService) {

    fun getDrawable(): List<Int> {
        return dataSource
    }

    companion object {
        val dataSource = listOf(
            R.drawable.minnie_mouse_kiss,
            R.drawable.minnie_mouse_minnie,
            R.drawable.minnie_mouse_please
        )
    }
}