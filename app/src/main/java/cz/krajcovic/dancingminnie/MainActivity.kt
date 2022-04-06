package cz.krajcovic.dancingminnie

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import cz.krajcovic.dancingminnie.repositories.PicturesRepository
import cz.krajcovic.dancingminnie.services.LongRunningService
import cz.krajcovic.dancingminnie.ui.theme.DancingMinnieTheme
import cz.krajcovic.dancingminnie.viewmodels.MinnieViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appController: AppContainer
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.uprootAll()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // Gets userRepository from the instance of AppContainer in Application
//        val appController = (application as DancingMinnieApplication).appController
//        DaggerDancingMinnieApplication_HiltComponents_SingletonC.builder()
//        (application as DancingMinnieApplication).appGraph.viewModel.getDrawable()
        

        // Create an instance of the application graph
//        val appGraph: AppGraph = DaggerAppGraph.create()

        setContent {
//            MainContent(appController.viewModel.getDrawable())
//            MainContent(appGraph.viewModel.getDrawable())
            MainContent(drawableList = (application as DancingMinnieApplication).appGraph.viewModel.getDrawable())
        }
    }
}

@Composable
fun MainContent(drawableList: List<Int>) {
    DancingMinnieTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            PlayList(drawableList)
        }
    }
}

fun getImageLoader(context: Context): ImageLoader {
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()

    return imageLoader
}

@ExperimentalCoilApi
@Composable
fun PlayGif(idGif: Int) {
    Card(
        shape = RoundedCornerShape(20.dp)
    ) {
        var isClicked by remember { mutableStateOf(false) }
        Image(
            modifier = Modifier.clickable {
                isClicked != isClicked
            },
            painter = rememberImagePainter(
                imageLoader = getImageLoader(LocalContext.current),
                data = idGif,
                builder = {
                    size(
                        LocalConfiguration.current.screenWidthDp / 3,
                        LocalConfiguration.current.screenHeightDp / 3
                    )
                }
            ),
            contentDescription = null,
        )
    }
}

@Composable
fun PlayList(drawableList: List<Int>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        drawableList.forEach {
            item { PlayGif(idGif = it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent(MinnieViewModelImpl(PicturesRepository(LongRunningService())).getDrawable())
}

