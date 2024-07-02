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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.dogiumlabs.coldweather.data.weather.ForecastHour
import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.data.weather.WeatherCurrent
import com.dogiumlabs.coldweather.data.weather.getPreviewWeather
import com.dogiumlabs.coldweather.ui.AppViewModelProvider
import com.dogiumlabs.coldweather.ui.components.ColdWeatherTopBar
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLocationButtonClick: () -> Unit,
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
                timeFormatter = viewModel::timeFormatter,
                onLocationButtonClick = onLocationButtonClick,
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
    timeFormatter: (String) -> String,
    onLocationButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { ColdWeatherTopBar(onLocationButtonClick) }
    ) { innerPadding ->
        /** Screen that displays basic weather info **/
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
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
                    .padding(
                        top = 16.dp,
                        bottom = 12.dp
                    )
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.titleMedium
                )
                ClickableText(
                    onClick = { /** TODO **/ },
                    text = AnnotatedString("Next 7 Days >"),
                    style = MaterialTheme.typography.titleMedium.copy(color = LocalContentColor.current),
                )
            }
            WeatherScrollList(
                forecastDay = weather.forecast.forecastDayList[0].forecastHourList,
                timeFormatter = timeFormatter,
                // Returns the first day of weather forecast
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun WeatherCard(
    currentWeather: WeatherCurrent,
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
                    .height(224.dp)
                    .width(224.dp)
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
    currentWeather: WeatherCurrent,
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
    timeFormatter: (String) -> String,
    modifier: Modifier = Modifier
) {
    /** Scrollable Row with weather forecast for day **/
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(forecastDay) { forecastHour ->
            WeatherScrollListItem(
                forecastHourItem = forecastHour,
                timeFormatter = timeFormatter,
            )
        }
    }
}

@Composable
fun WeatherScrollListItem(
    forecastHourItem: ForecastHour,
    timeFormatter: (String) -> String,
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
                text = timeFormatter(forecastHourItem.time),
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

@Composable
@Preview(showBackground = true)
fun HomeScreenWeatherPreview() {
    ColdWeatherTheme {
        HomeWeatherScreen(
            weather = getPreviewWeather(),
            timeFormatter = { inputString -> inputString },
            onLocationButtonClick = {}
        )
    }
}

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