package pl.baftek.opencaching.features.signin

import androidx.compose.runtime.Composable

@Composable
fun SignInRoute(
    onNavigateToMap: () -> Unit,
) {
    SignInScreen(onNavigateToMap = onNavigateToMap)
}
