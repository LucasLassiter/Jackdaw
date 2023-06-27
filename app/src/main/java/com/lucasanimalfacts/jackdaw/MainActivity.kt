package com.lucasanimalfacts.jackdaw

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.lucasanimalfacts.jackdaw.presentation.homepage.HomepageEvent
import com.lucasanimalfacts.jackdaw.presentation.homepage.HomepageViewModel
import com.lucasanimalfacts.jackdaw.ui.theme.JackdawTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.Url

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            JackdawTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Greeting(
    viewModel: HomepageViewModel = hiltViewModel()
) {
    Image(painter = rememberImagePainter(
        data = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=3a445a32-2c71-4da7-b606-2167431b0d2f",
        builder = { crossfade(true) }
    ), contentDescription = "sdf")
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.state.value.starred?.`subsonic-response`?.starred?.album?.let { songList ->
            items(songList.size) {cur ->

//                    viewModel.onEvent(HomepageEvent.GetAlbumArt(songList[cur].coverArt))
                    val url: String = "https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"
                Log.d("copythe", songList[cur].coverArt)
                Text(text = songList[cur].title)
            }
        }
    }
    Log.d("MainActivity", viewModel.state.value.randomSongs?.`subsonic-response`.toString())
}
