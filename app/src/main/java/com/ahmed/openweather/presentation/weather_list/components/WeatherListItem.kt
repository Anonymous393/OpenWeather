package com.ahmed.openweather.presentation.weather_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmed.openweather.common.Constants
import com.ahmed.openweather.domain.model.Daily
import com.ahmed.openweather.domain.model.Temp
import com.ahmed.openweather.domain.model.toDay

@Composable
fun WeatherListItem(
    dayWeather: Daily,
    onItemClick: ((Daily) -> Unit)?
) {
    // TODO: Move this to a component and use it here and in the main screen
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .width(56.dp)
            .clickable {
                if (onItemClick != null) {
                    onItemClick(dayWeather)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = dayWeather.date.toDay(),
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )

        Text(
            text = "${dayWeather.temp.max}" + Constants.DEGREES,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )

        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .width(16.dp)
                .height(1.dp)
                .background(Color.White)
        )

        Text(
            text = "${dayWeather.temp.min}" + Constants.DEGREES,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    WeatherListItem(
        Daily(1640170800, Temp(min = 2, max = 4), null),
        null
    )
}