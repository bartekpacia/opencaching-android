# Opencaching

> ⚠️ work in progress

An Android app for [Geocaching], built with Jetpack Compose.

Supports OpenCaching sites through [OKAPI](https://www.opencaching.pl/okapi/introduction.html).

## Setup

### OKAPI

You need to sign up on one of the national Opencaching websites and obtain API credentials.

- [Introduction](https://opencaching.pl/okapi/introduction.html)
- [Sign up for an API key](https://opencaching.pl/okapi/signup.html)

Once you have the credentials, paste them into `local.properties`:

```properties
okapi.consumerKey=YOUR_CONSUMER_KEY_HERE
okapi.consumerSecret=YOUR_CONSUMER_SECRET_HERE
```

### Android setup

To display the map, a Google Maps API key is needed. It can be obtained on Google Cloud Console.
Once you have the key, paste it into `androidApp/src/main/res/values/google_maps_api_key.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="google_maps_api_key" translatable="false">YOUR_API_KEY_HERE</string>
</resources>
```

[Geocaching]: https://en.wikipedia.org/wiki/Geocaching
