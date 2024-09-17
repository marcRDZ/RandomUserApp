package es.marcrdz.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.marcrdz.domain.models.Coordinates
import es.marcrdz.domain.models.Location
import es.marcrdz.domain.models.Login
import es.marcrdz.domain.models.Moment
import es.marcrdz.domain.models.Name
import es.marcrdz.domain.models.Picture
import es.marcrdz.domain.models.Street
import es.marcrdz.domain.models.TimeZone
import es.marcrdz.domain.models.User
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersView(
    usersList: List<User>,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onUserClick: (User) -> Unit = {}
) {

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onRefresh() }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(count = usersList.size) { index ->
                UserCardView(
                    user = usersList[index],
                    onClick = onUserClick
                )
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun UsersPreview() {
    UsersView(
        usersList = listOf(
            User(
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
                    postcode = "49676",
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
            ),
            User(
                gender = "female",
                name = Name(
                    title = "Mrs",
                    first = "Loane",
                    last = "Dumas"
                ),
                location = Location(
                    street = Street(
                        number = 4487,
                        name = "Rue Paul Bert"
                    ),
                    city = "Lyon",
                    state = "Moselle",
                    country = "France",
                    postcode = "45512",
                    coordinates = Coordinates(
                        latitude = 63.7767,
                        longitude = 99.6751
                    ),
                    timezone = TimeZone(
                        offset = "-8:00",
                        description = "Pacific Time (US & Canada)"
                    )
                ),
                email = "loane.dumas@example.com",
                login = Login(
                    uuid = "2a2640ab-2916-4e22-8cb8-b86c93627f7b",
                    username = "tinycat670",
                    password = "1818",
                    salt = "b1PsfhCh",
                    md5 = "531eac357d05d8f040f9d5fe0df74453",
                    sha1 = "ad93e821e2b2ceb1487e4bc081406fabd5c75fb4",
                    sha256 = "9eb7259474b40c00599c313b680a96762a45daf6a68292d833b9a40d947979c1"
                ),
                dob = Moment(
                    date = Date(),
                    age = 57
                ),
                registered = Moment(
                    date = Date(),
                    age = 16
                ),
                phone = "02-94-58-17-66",
                cell = "06-23-73-26-72",
                id = "INSEE" to "2670030812303 50",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/women/67.jpg",
                    medium = "https://randomuser.me/api/portraits/med/women/67.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/women/67.jpg"
                ),
                nat = "FR"
            ),
            User(
                gender = "male",
                name = Name(
                    title = "Mr",
                    first = "Rasmus",
                    last = "Jørgensen"
                ),
                location = Location(
                    street = Street(
                        number = 9204,
                        name = "Skovbovej"
                    ),
                    city = "Frederiksberg",
                    state = "Hovedstaden",
                    country = "Denmark",
                    postcode = "99944",
                    coordinates = Coordinates(
                        latitude = -64.5175,
                        longitude = -168.4397
                    ),
                    timezone = TimeZone(
                        offset = "+3:30",
                        description = "Tehran"
                    )
                ),
                email = "rasmus.jorgensen@example.com",
                login = Login(
                    uuid = "fd40ed0d-a40c-459e-b34f-358ff3ffa440",
                    username = "crazyfrog103",
                    password = "pearls",
                    salt = "pwI08m98",
                    md5 = "19810ce65678348434fbf0cfc1b4907b",
                    sha1 = "6a802492445a2ed5e26847b279c4a66cd313da6f",
                    sha256 = "51e542fbab99d527c9530d49113152094181e0298cf4c05efacf588acf02065d"
                ),
                dob = Moment(
                    date = Date(),
                    age = 31
                ),
                registered = Moment(
                    date = Date(),
                    age = 6
                ),
                phone = "61420671",
                cell = "51491128",
                id = "CPR" to "190293-6575",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/15.jpg",
                    medium = "https://randomuser.me/api/portraits/med/men/15.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/men/15.jpg"
                ),
                nat = "DK"
            )
        ),
        isRefreshing = false
    )
}
