package com.example.dogecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogecompose.data.Doge
import com.example.dogecompose.data.dogeList
import com.example.dogecompose.ui.theme.DogeComposeTheme
import com.example.dogecompose.utils.NetworkImage

@Composable
fun DogeDetailPage(
    doge: Doge?,
    navBack: () -> Unit
) {

    val title = doge?.let {
        "Meet ${it.name}"
    } ?: "Something went wrong..."

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = { Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier
                        .clickable { navBack() }
                        .padding(8.dp)
                )}
            )},
        content = {
            doge?.let {
                DogeDetails(it)
            } ?: Oops()
        }
    )
}

@Composable
fun DogeDetails(doge: Doge) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {
        NetworkImage(
            url = doge.image,
            contentDescription = "Image of ${doge.name}, a ${doge.age} month old ${doge.breed}",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            placeholderImage = R.drawable.ic_paw_print
        )

        Text(
            text = doge.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Breed: ${doge.breed}",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "Age: ${doge.age} months",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = String.format("Cost: \$%.2f", doge.cost),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = when (doge.reserved) {
                true -> "Availability: Reserved"
                false -> "Availability: Pay deposit today and ${doge.name} is yours!"
            },
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "**Due to COVID 19 the airlines are not shipping puppies through air cargo at this time. All puppies need to be picked up here at Puppy Place or we can recommend a few ground transportation companies.",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 96.dp)
        )
    }
}

@Composable
fun Oops() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_guilty_dog),
            contentDescription = "Image of guilty looking dog.",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
        )
        Text(
            text = "Please click back an try again.",
            style = MaterialTheme.typography.subtitle1
        )
    }
}


@Preview
@Composable
fun OopsPreview() {
    DogeComposeTheme {
        DogeDetailPage(null, {})
    }
}

@Preview
@Composable
fun DogeDetailsPreview() {
    DogeComposeTheme {
        DogeDetailPage(dogeList.first(), {})
    }
}

