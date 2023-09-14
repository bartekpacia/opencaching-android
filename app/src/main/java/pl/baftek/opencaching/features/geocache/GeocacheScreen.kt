package pl.baftek.opencaching.features.geocache

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.baftek.opencaching.data.FullGeocache
import pl.baftek.opencaching.data.Geocache
import pl.baftek.opencaching.data.Location
import pl.baftek.opencaching.data.User
import pl.baftek.opencaching.ui.theme.OpencachingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeocacheScreen(geocache: FullGeocache, onNavUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Geocache ${geocache.code}") },
                navigationIcon = {
                    IconButton(onClick = onNavUp) {
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
            Modifier
                .width(IntrinsicSize.Max)
                .padding(padding)
        ) {
            Text(
                geocache.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Terrain: ${geocache.terrain}")
                Text("Difficulty: ${geocache.difficulty}")
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(8.dp)
            )
            {
                Text("Placed by: ${geocache.owner.username}")
                Text("on ${geocache.dateHidden}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GeocacheScreemPreview() {
    OpencachingTheme {
        val geocache = FullGeocache(
            code = "GC1234",
            name = "Drewniany most na Rudzie",
            location = Location(latitude = 50.196168, longitude = 18.446953),
            type = Geocache.Type.Traditional,
            status = Geocache.Status.ARCHIVED,
            difficulty = 2.5,
            terrain = 2.5,
            size = 2,
            owner = User(
                username = "Bartek_Wojak",
                uuid = "1234",
                profile_url = "https://opencaching.pl/images/avatars/1234.jpg",
            ),
            description = "This is a test geocache",
            url = "https://opencaching.pl/viewcache.php?wp=OP9655",
            hint = "Hint: it's here",
            dateHidden = "12/12/2022",
        )

        GeocacheScreen(geocache = geocache) {}
    }
}
