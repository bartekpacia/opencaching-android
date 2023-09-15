package pl.baftek.opencaching.features.geocache

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.MonitorHeart
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Text(
                text = geocache.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            ) {
                Text(geocache.code)
                Text("•")
                Text("${geocache.type}")
            }

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Navigation,
                        contentDescription = "Navigate"
                    )

                    Text("Navigate")
                }

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Log cache")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Difficulty: ${geocache.difficulty}")
                Text("Terrain: ${geocache.terrain}")
                Text("Size: ${geocache.size}")
            }

            Text(
                text = "Placed by: ${geocache.owner.username}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "on ${geocache.dateHidden}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Hint")
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(Color.Gray)
                )

                TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Hint")
                }
            }

            Column {
                GeocacheInfoTile(
                    icon = Icons.Rounded.List,
                    title = "Description",
                    subtitle = geocache.description,
                )

                GeocacheInfoTile(
                    icon = Icons.Filled.MonitorHeart,
                    title = "Activity",
                    subtitle = "Found 2 days ago",
                )

                GeocacheInfoTile(
                    icon = Icons.Outlined.Info,
                    title = "Attributes",
                    subtitle = "Recommended for kids & 3 more",
                )
            }
        }
    }
}

@Composable
fun GeocacheInfoTile(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    subtitle: String,
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = title
            )
        },
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
    )
}

@Preview(showBackground = true)
@Composable
fun GeocacheScreemPreview() {
    OpencachingTheme {
        GeocacheScreen(geocache = sampleGeocache) {}
    }
}

@Preview(showBackground = true)
@Composable
fun GeocacheScreemPreviewDark() {
    OpencachingTheme(darkTheme = true) {
        GeocacheScreen(geocache = sampleGeocache) {}
    }
}


private val sampleGeocache = FullGeocache(
    code = "GC1234",
    name = "Drewniany most na Rudzie",
    location = Location(latitude = 50.196168, longitude = 18.446953),
    type = Geocache.Type.Traditional,
    status = Geocache.Status.Available,
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