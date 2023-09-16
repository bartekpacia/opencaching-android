# Opencaching

> ⚠️ work in progress

An Android app for [Geocaching], built with Jetpack Compose.

Supports OpenCaching sites through [OKAPI](https://www.opencaching.pl/okapi/introduction.html).

### Android setup

To display the map, a Google Maps API key is needed. It can be obtained on Google Cloud Console.
Once you have the key, paste it into `androidApp/src/main/res/values/google_maps_api_key.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="google_maps_api_key" translatable="false">API_KEY_HERE</string>
</resources>
```

[Geocaching]: https://en.wikipedia.org/wiki/Geocaching
