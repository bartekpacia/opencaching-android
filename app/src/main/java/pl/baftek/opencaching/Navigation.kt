package pl.baftek.opencaching

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.baftek.opencaching.features.geocache.GeocacheScreen
import pl.baftek.opencaching.features.map.MapScreen
import pl.baftek.opencaching.features.sign_in.SignInScreen

object Destinations {
    const val SIGN_IN_ROUTE = "signin"
    const val MAP_ROUTE = "map/"
    const val GEOCACHE_ROUTE = "geocache/{code}"
}

@Composable
fun OpencachingNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.SIGN_IN_ROUTE
    ) {
        composable(Destinations.SIGN_IN_ROUTE) {
            SignInScreen(
                onNavigateToMap = { navController.navigate(Destinations.MAP_ROUTE) }
            )
        }
        composable(Destinations.MAP_ROUTE) {
            MapScreen(
                onNavigateToGeocache = { code -> navController.navigate("geocache/$code") }
            )
        }
        composable(Destinations.GEOCACHE_ROUTE) {
            val code = it.arguments?.getString("code") ?: "empty"
            GeocacheScreen(
                code = code,
                onBackTap = { navController.popBackStack() }
            )
        }
    }
}