package es.marcrdz.ui.composables

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import es.marcrdz.domain.models.Attributes
import es.marcrdz.domain.models.Item

@Composable
fun ItemCardView(
    item: Item
) {
    val imgLoader = LocalContext.current.imageLoader
    val randomColor: Int = android.graphics.Color.rgb(
        (30..200).random(),
        (30..200).random(),
        (30..200).random()
    )

    ElevatedCard(
        colors = CardColors(
            contentColor = Color.DarkGray,
            containerColor = Color.White,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 4.dp,
                vertical = 8.dp
            )
    ) {

        Row {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .clip(CircleShape)
                        .height(32.dp)
                        .width(32.dp)
                        .background(Color(randomColor))
                        .align(Alignment.End)
                ) {
                    Text(
                        text = item.id,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Text(
                    text = item.attributes.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                )
                Text(
                    text = item.attributes.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )
            }

            AsyncImage(
                modifier = Modifier.height(128.dp),
                model = item.attributes.imgUrl,
                contentScale = ContentScale.FillHeight,
                contentDescription = item.attributes.description,
                imageLoader = imgLoader
            )

        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ItemCardPreview() {
    ItemCardView(
        item = Item(
            id = "1", attributes = Attributes(
                name = "Item name 1",
                description = "Description of item 1",
                imgUrl = "https://picsum.photos/id/101/300/200"
            )
        )
    )
}