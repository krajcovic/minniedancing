package cz.krajcovic.dancingminnie

import android.app.Application
import cz.krajcovic.dancingminnie.repositories.PicturesRepository
import cz.krajcovic.dancingminnie.viewmodels.MinnieViewModelImpl
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// @Component makes Dagger create a graph of dependencies
@Singleton
@Component(
    modules = [
//        PicturesRepository::class,
//        MinnieViewModelImpl::class,
    ]
)
interface AppComponentGraph {
//    // The return type  of functions inside the component interface is
//    // what can be provided from the container
    fun repository() : PicturesRepository
    val picturesRepository: PicturesRepository
    val viewModel: MinnieViewModelImpl

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppGraph
//    }
}