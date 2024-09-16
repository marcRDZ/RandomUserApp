package es.marcrdz.ui.composables

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import es.marcrdz.domain.models.Attributes
import es.marcrdz.domain.models.Coordinates
import es.marcrdz.domain.models.Item
import es.marcrdz.domain.models.Location
import es.marcrdz.domain.models.Login
import es.marcrdz.domain.models.Moment
import es.marcrdz.domain.models.Name
import es.marcrdz.domain.models.Picture
import es.marcrdz.domain.models.Street
import es.marcrdz.domain.models.TimeZone
import es.marcrdz.domain.models.User
import java.util.Date

@Composable
fun UserCardView(
    user: User
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
                        text = user.nat,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Text(
                    text = user.name.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                )
                Text(
                    text = user.login.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )
            }

            AsyncImage(
                modifier = Modifier.height(128.dp),
                model = user.picture.thumbnail,
                contentScale = ContentScale.FillHeight,
                contentDescription = "${user.login.username} photo",
                imageLoader = imgLoader
            )

        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ItemCardPreview() {
    UserCardView(
        user = User(
            gender = "female",
            name = Name(
                title = "Miss",
                first = "Amanda",
                last = "Korhonen"
            ),
            location = Location(
                street = Street(
                    number = 3447,
                    name = "Hatanpään Valtatie"
                ),
                city = "Aura",
                state = "South Karelia",
                country = "Finland",
                postcode = 49676,
                coordinates = Coordinates(
                    latitude = -23.7377,
                    longitude = 163.2147
                ),
                timezone = TimeZone(
                    offset = "+11:00",
                    description = "Magadan, Solomon Islands, New Caledonia"
                )
            ),
            email = "amanda.korhonen@example.com",
            login = Login(
                uuid = "150f520b-7718-4647-9258-31155ec8a6ae",
                username = "orangeduck207",
                password = "dutch",
                salt = "DhGyrCJl",
                md5 = "02a3e50d75621a7a3eb1afdc87b357c6",
                sha1 = "6564ab079a8fc3d6c542aa2ef5a1ed6e2bee32a8",
                sha256 = "83e34adb514617340b6f260c349a24332aed1a888d4749fbfe6e53f9a034f339"
            ),
            dob = Moment(
                date = Date(),
                age = 0
            ),
            registered = Moment(
                date = Date(),
                age = 0
            ),
            phone = "09-856-368",
            cell = "041-098-30-88",
            id = "HETU" to "NaNNA086undefined",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/31.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/31.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/31.jpg"
            ),
            nat = "FI"
        )
    )
}