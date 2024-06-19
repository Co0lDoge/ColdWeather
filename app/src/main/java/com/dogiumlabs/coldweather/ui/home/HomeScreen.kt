package com.dogiumlabs.coldweather.ui.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dogiumlabs.coldweather.ui.theme.ColdWeatherTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "London",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Monday June 17",
            style = MaterialTheme.typography.titleSmall
        )
        WeatherCard()
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleMedium
            )
            ClickableText(
                onClick = { /** TODO **/},
                text = AnnotatedString("Next 7 Days >"),
                style = MaterialTheme.typography.titleMedium
            )
        }
        WeatherScrollList(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
        )
    }
}

@Composable
fun WeatherCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(32.dp)
        ) {
            Image(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(256.dp)
                    .width(256.dp)
            )
            Text(
                text = "Mostly Sunny",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "25°C",
                style = MaterialTheme.typography.headlineMedium
            )
            WeatherParametersTable(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun WeatherParametersTable(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        WeatherParameter(
            text = "Precipitation",
            imageVector = Icons.Filled.Warning,
            modifier = Modifier.weight(1f)
        )
        WeatherParameter(
            text = "Wind Speed",
            imageVector = Icons.Filled.Warning,
            modifier = Modifier.weight(1f)

        )
        WeatherParameter(
            text = "Humidity",
            imageVector = Icons.Filled.Warning,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun WeatherParameter(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun WeatherScrollList(modifier: Modifier = Modifier) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(5) { index ->
            WeatherScrollListItem(
                time = "$index pm",
                imageVector = Icons.Filled.Warning,
                temperature = 25
            )
        }
    }
}

@Composable
fun WeatherScrollListItem(
    time: String,
    imageVector: ImageVector,
    temperature: Int,
    modifier: Modifier = Modifier
) {
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
                text = time,
                style = MaterialTheme.typography.bodySmall
            )
            Image(
                imageVector = imageVector,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
            )
            Text(
                text = "$temperature°",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    ColdWeatherTheme {
        HomeScreen()
    }
}