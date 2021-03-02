package com.example.dogecompose

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogecompose.data.Doge
import com.example.dogecompose.data.dogeList
import com.example.dogecompose.ui.theme.DogeComposeTheme
import com.example.dogecompose.utils.NetworkImage

@Composable
fun DogeListingPage(
    doges: List<Doge>,
    navToDetails: (doge: Doge) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Meet the Doges") }) },
        content = {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(doges) { doge ->
                    DogeListItem(
                        doge = doge,
                        modifier = Modifier
                            .clickable { navToDetails(doge) }
                            .fillMaxWidth()
                    )
                }
            }
        }
    )
}

@SuppressLint("ResourceAsColor")
@Composable
fun DogeListItem(doge: Doge, modifier: Modifier) {
    Row(
        modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    ) {
        NetworkImage(
            url = doge.image,
            contentDescription = "Image of ${doge.name}, a ${doge.age} month old ${doge.breed}",
            modifier = Modifier
                .size(128.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            placeholderImage = R.drawable.ic_paw_print
        )
        Column(
            modifier = Modifier
                .height(128.dp)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = doge.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )

            val cost = String.format("\$%.2f - ", doge.cost)
            val availability = when (doge.reserved) {
                true -> "Reserved"
                false -> "Available"
            }


            val color = if (doge.reserved) colorResource(R.color.orange) else colorResource(R.color.teal_200)

            val annotatedString = buildAnnotatedString {
                append(cost + availability)
                addStyle(
                    style = SpanStyle(
                        color = color
                    ),
                    cost.length,
                    cost.length + availability.length,
                )
            }

            Text(
                text = annotatedString,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "${doge.age}mo - ${doge.breed}",
                style = MaterialTheme.typography.body1
            )
        }
    }
    
    Divider(color = Color.DarkGray, thickness = 1.dp)
}

@Preview
@Composable
fun DogeItemPreview() {
    DogeComposeTheme {
        DogeListItem(
            dogeList.first(),
            Modifier.fillMaxWidth()
        )
    }
}