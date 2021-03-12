package com.example.dogecompose.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and loading placeholder.
 */
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderImage: Int
) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {
            Image(
                painter = painterResource(id = placeholderImage),
                contentDescription = contentDescription,
                contentScale =  contentScale,
                modifier = modifier
            )
        }
    )
}
