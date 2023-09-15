package pl.baftek.opencaching.features.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.baftek.opencaching.data.CachesRepository
import pl.baftek.opencaching.data.Geocache
import pl.baftek.opencaching.data.Location
import pl.baftek.opencaching.debugLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onNavigateToGeocache: (Geocache) -> Unit,
) {
    val centerOfRudy = Location(latitude = 50.196168, longitude = 18.446953)

    val scope = rememberCoroutineScope()
    val geocaches: SnapshotStateMap<String, Geocache> = rememberSaveable(
        saver = mapSaver(
            save = { it.entries.associate { entry -> entry.key to Json.encodeToString(entry.value) } },
            restore = {
                val pairs = it.entries.map { entry ->
                    entry.key to Json.decodeFromString<Geocache>(entry.value as String)
                }
                mutableStateMapOf(*pairs.toTypedArray())
            }
        ),
        init = { mutableStateMapOf() },
    )


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
                modifier = Modifier.padding(8.dp),
                center = centerOfRudy,
                caches = geocaches.values.toList(),
                onGeocacheClick = { code -> onNavigateToGeocache(geocaches[code]!!) },
                onMapBoundsChange = {
                    debugLog("MapScreen", "onMapBoundsChange: $it")
                    if (it == null) return@Map

                    scope.launch {
                        delay(500)
                        try {
                            // geocaches.clear()
                            geocaches.putAll(repository.searchAndRetrieve(it))
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )
        }
    }
}
