package cz.krajcovic.dancingminnie.repositories

import cz.krajcovic.dancingminnie.R
import cz.krajcovic.dancingminnie.services.LongRunningService
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicturesRepository @Inject constructor(private val longRunningService: LongRunningService) {

    fun getDrawable(): List<Int> {
        return dataSource.toMutableList().shuffled()
    }

    fun getNegative(): Int {
        val ds: MutableList<Int> = ArrayList()
//        ds.addAll(dataSource)
        ds.add(R.drawable.daddy)
        return ds.random()
    }

    companion object {
        val dataSource = listOf(
            R.drawable.minnie_mouse_kiss,
            R.drawable.minnie_mouse_minnie,
            R.drawable.minnie_mouse_please,
            R.drawable.elsa_queen_elsa,
            R.drawable.frozen_elsa,
            R.drawable.frozen_elsa_1,
            R.drawable.frozen_ii_frozen2
        )


    }
}