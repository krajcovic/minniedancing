package cz.krajcovic.dancingminnie.viewmodels

import androidx.lifecycle.ViewModel
import cz.krajcovic.dancingminnie.repositories.PicturesRepository
import javax.inject.Inject

class MinnieViewModelImpl @Inject constructor(private val picturesRepository: PicturesRepository) : ViewModel() {
    fun getDrawable(): List<Int> {
        return picturesRepository.getDrawable()
    }
}