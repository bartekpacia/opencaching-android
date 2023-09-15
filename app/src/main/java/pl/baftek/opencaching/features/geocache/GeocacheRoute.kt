package pl.baftek.opencaching.features.geocache


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import pl.baftek.opencaching.data.CachesRepository
import pl.baftek.opencaching.data.FullGeocache

@Composable
fun GeocacheRoute(code: String, onBackTap: () -> Unit) {
    val httpClient = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    val repository = remember { CachesRepository(httpClient) }

    val geocache = remember { mutableStateOf<FullGeocache?>(null) }

    LaunchedEffect(code) {
        delay(1000)
        geocache.value = repository.getGeocache(code)
    }

    if (geocache.value == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
            )
        }
    } else {
        GeocacheScreen(geocache = geocache.value!!, onNavUp = onBackTap)
    }
}