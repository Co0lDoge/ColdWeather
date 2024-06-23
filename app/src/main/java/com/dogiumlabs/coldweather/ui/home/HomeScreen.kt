package com.dogiumlabs.coldweather.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dogiumlabs.coldweather.R
import com.dogiumlabs.coldweather.data.weather.Current
import com.dogiumlabs.coldweather.data.weather.ForecastHour
import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.ui.AppViewModelProvider
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    /** Top level composable that displays content based on UiState's status **/
    val homeUiState = viewModel.homeUiState
    AnimatedVisibility(
        visible = homeUiState is HomeUiState.Success,
        enter = slideInHorizontally(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
        exit = slideOutVertically()
    ) {
        if (homeUiState is HomeUiState.Success)
            HomeWeatherScreen(
                weather = homeUiState.weather,
                modifier = modifier
            )
    }
    AnimatedVisibility(
        visible = homeUiState == HomeUiState.Loading,
        enter = fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { it / 2 }) + fadeOut()
    ) {
        HomeLoadingScreen(modifier)
    }
    AnimatedVisibility(
        visible = homeUiState == HomeUiState.Error,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        HomeErrorScreen(modifier)
    }
}

@Composable
fun HomeWeatherScreen(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    /** Screen that displays basic weather info **/
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = weather.location.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = weather.location.localTime,
            style = MaterialTheme.typography.titleSmall
        )
        WeatherCard(weather.current)
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleMedium
            )
            ClickableText(
                onClick = { /** TODO **/ },
                text = AnnotatedString("Next 7 Days >"),
                style = MaterialTheme.typography.titleMedium
            )
        }
        WeatherScrollList(
            forecastDay = weather.forecast.forecastDayList[0].forecastHourList,
            // Returns the first day of weather forecast
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun WeatherCard(
    currentWeather: Current,
    modifier: Modifier = Modifier
) {
    /** Card in the center of the screen **/
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(32.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://" + currentWeather.condition.icon)
                    .build(),
                error = painterResource(id = R.drawable.error),
                //placeholder = painterResource(id = R.drawable.loading),
                contentDescription = currentWeather.condition.text,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(256.dp)
                    .width(256.dp)
            )
            Text(
                text = currentWeather.condition.text,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${currentWeather.tempC}°C",
                style = MaterialTheme.typography.headlineMedium
            )
            WeatherParametersTable(
                currentWeather = currentWeather,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun WeatherParametersTable(
    currentWeather: Current,
    modifier: Modifier = Modifier
) {
    /** Table containing Precipitation, Wind Speed, Humidity **/
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        WeatherParameter(
            name = "Precipitation",
            value = "${currentWeather.precipMm}%",
            painter = painterResource(id = R.drawable.humidity),
            modifier = Modifier.weight(1f)
        )
        WeatherParameter(
            name = "Wind Speed",
            value = "${currentWeather.windKph} km/h",
            painter = painterResource(id = R.drawable.wind_speed),
            modifier = Modifier.weight(1f)

        )
        WeatherParameter(
            name = "Humidity",
            value = "${currentWeather.humidity}%",
            painter = painterResource(id = R.drawable.humidity),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun WeatherParameter(
    name: String,
    value: String,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    /** Template for weather parameters display **/
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun WeatherScrollList(
    forecastDay: List<ForecastHour>,
    modifier: Modifier = Modifier
) {
    /** Scrollable Row with weather forecast for day **/
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(forecastDay) { forecastHour ->
            WeatherScrollListItem(forecastHour)
        }
    }
}

@Composable
fun WeatherScrollListItem(
    forecastHourItem: ForecastHour,
    modifier: Modifier = Modifier
) {
    /** Card with time and temperature info**/
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                start = 24.dp,
                top = 16.dp,
                bottom = 16.dp,
                end = 24.dp
            )
        ) {
            Text(
                text = forecastHourItem.time,
                style = MaterialTheme.typography.bodySmall
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://" + forecastHourItem.condition.icon)
                    .build(),
                error = painterResource(id = R.drawable.error),
                //placeholder = painterResource(id = R.drawable.loading),
                contentDescription = forecastHourItem.condition.text,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
            )
            Text(
                text = "${forecastHourItem.tempC}°C",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun HomeLoadingScreen(modifier: Modifier = Modifier) {
    /** Screen that displays basic weather info **/
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .shadow(elevation = 4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.loading),
                    contentDescription = "Loading..."
                )
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

@Composable
fun HomeErrorScreen(modifier: Modifier = Modifier) {
    /** Screen that displays basic weather info **/
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .shadow(elevation = 4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.error),
                    contentDescription = "Error"
                )
                Text(
                    text = "Error",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun HomeScreenWeatherPreview() {
//    ColdWeatherTheme {
//        val previewLocation = Location(
//            "City Name",
//            "Region Name",
//            "Country Name",
//            20.0,
//            20.0,
//            "Timezone Id",
//            20,
//            "2001-03-21 15.30"
//        )
//        val previewWeather = Current(
//            lastUpdatedEpoch = 20,
//            lastUpdated = "time",
//            tempC = 20.0,
//            tempF = 20.0,
//            isDay = 1,
//            condition = Condition("Sunny", "icon url", 1000),
//            windMph = 0.0,
//            windKph = 0.0,
//            windDegree = 0,
//            windDirection = "N",
//            pressureMb = 1013,
//            pressureIn = 29.92,
//            precipMm = 0.0,
//            precipIn = 0.0,
//            humidity = 50,
//            cloud = 20,
//            feelsLikeCelsius = 19.5,
//            feelsLikeFahrenheit = 67.1,
//            windChillCelsius = 18.0,
//            windChillFahrenheit = 64.4,
//            heatIndexCelsius = 22.0,
//            heatIndexFahrenheit = 71.6,
//            dewPointCelsius = 15.0,
//            dewPointFahrenheit = 59.0,
//            visibilityKm = 10,
//            visibilityMiles = 6,
//            uv = 5,
//            gustMph = 0.0,
//            gustKph = 0.0
//
//        )
//        HomeWeatherScreen(
//            location = previewLocation,
//            currentWeather = previewWeather
//        )
//    }
//}

@Composable
@Preview(showBackground = true)
fun HomeLoadingScreenPreview() {
    ColdWeatherTheme {
        HomeLoadingScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun HomeErrorScreenPreview() {
    ColdWeatherTheme {
        HomeErrorScreen()
    }
}