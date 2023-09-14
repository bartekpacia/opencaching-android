package pl.baftek.opencaching.features.geocache

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeocacheScreen(code: String, onBackTap: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Geocache $code") },
                navigationIcon = {
                    IconButton(onClick = onBackTap) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Text("Details of geocache $code!")
        }
    }
}
