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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import cz.krajcovic.dancingminnie.ui.theme.DancingMinnieTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.uprootAll()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        setContent {
            MainContent()
        }
    }

    companion object {
        val gifList = listOf(
            R.drawable.minnie_mouse_kiss,
            R.drawable.minnie_mouse_minnie,
            R.drawable.minnie_mouse_please
        )
    }
}

@Composable
fun MainContent() {
    DancingMinnieTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
//            Greeting("Android")
//            PlayGif((0 until MainActivity.gifList.size).random())
            PlayList()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
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

@Composable
fun PlayList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
//        PlayGif(idGif = R.drawable.minnie_mouse_kiss)
//        item(0, MainActivity.gifList[0])
//        items(MainActivity.gifList.subList(0,1)) { image ->
//            PlayGif(idGif = image)
//        }
        MainActivity.gifList.forEach {
            PlayGif(idGif = it)
        }
    }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}