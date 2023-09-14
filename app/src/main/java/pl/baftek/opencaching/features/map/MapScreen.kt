package pl.baftek.opencaching.features.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import pl.baftek.opencaching.data.CachesRepository
import pl.baftek.opencaching.data.Geocache
import pl.baftek.opencaching.data.Location
import pl.baftek.opencaching.debugLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onNavigateToGeocache: (String) -> Unit,
) {
    val centerOfRudy = Location(latitude = 50.196168, longitude = 18.446953)

    val scope = rememberCoroutineScope()
    val geocaches = remember { mutableStateListOf<Geocache>() }

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Map") },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Map(
                Modifier.padding(8.dp),
                center = centerOfRudy,
                caches = geocaches.toList(),
                onGeocacheClick = { code -> onNavigateToGeocache(code) },
                onMapBoundsChange = {
                    debugLog("MapScreen", "onMapBoundsChange: $it")
                    if (it == null) return@Map

                    scope.launch {
                        delay(500)
                        try {
                            geocaches.clear()
                            geocaches.addAll(repository.searchAndRetrieve(it).values)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )

            Box(Modifier.height(50.dp).width(200.dp).background(Color.Red))
        }
    }
}
