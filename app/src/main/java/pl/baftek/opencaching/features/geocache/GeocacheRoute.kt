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
import kotlinx.coroutines.delay
import pl.baftek.opencaching.data.CachesRepository
import pl.baftek.opencaching.data.FullGeocache

@Composable
fun GeocacheRoute(
    code: String,
    onNavUp: () -> Unit,
    onNavigateToDescription: (String) -> Unit,
) {
    val repository = remember { CachesRepository() }

    val geocache = remember { mutableStateOf<FullGeocache?>(null) }

    LaunchedEffect(code) {
        delay(1000)
        geocache.value = repository.getGeocache(code)
    }

    if (geocache.value == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
            )
        }
    } else {
        GeocacheScreen(
            geocache = geocache.value!!,
            onNavUp = onNavUp,
            onNavigateToDescription = onNavigateToDescription,
            )
    }
}
