package pl.baftek.opencaching.features.map

import androidx.compose.runtime.Composable
import pl.baftek.opencaching.data.Geocache

@Composable
fun MapRoute(
    onNavigateToGeocache: (Geocache) -> Unit,
) {
    MapScreen(
        onNavigateToGeocache = onNavigateToGeocache,
    )
}
