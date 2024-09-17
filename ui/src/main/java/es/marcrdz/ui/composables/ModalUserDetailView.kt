package es.marcrdz.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.marcrdz.domain.models.Coordinates
import es.marcrdz.domain.models.Location
import es.marcrdz.domain.models.Login
import es.marcrdz.domain.models.Moment
import es.marcrdz.domain.models.Name
import es.marcrdz.domain.models.Picture
import es.marcrdz.domain.models.Street
import es.marcrdz.domain.models.TimeZone
import es.marcrdz.domain.models.User
import es.marcrdz.ui.R
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalUserDetailView(
    user: User,
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {}
) {

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Text(
            text = "${user.name.last}, ${user.name.first}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )

        SimpleLabelledContentView(
            label = stringResource(id = R.string.email),
            content = user.email,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)

        )

        SimpleLabelledContentView(
            label = stringResource(id = R.string.phone),
            content = user.phone,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)

        )

        SimpleLabelledContentView(
            label = stringResource(id = R.string.address),
            content = "${user.location.street.name}, ${user.location.street.number}",
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)

        )

        SimpleLabelledContentView(
            label = stringResource(id = R.string.postal_code),
            content = user.location.postcode,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)

        )

        SimpleLabelledContentView(
            label = stringResource(id = R.string.city),
            content = "${user.location.city}, ${user.location.state}, ${user.location.country}",
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
        )

        UserActionsCardView(
            email = user.email,
            phone = user.phone,
            location = user.location,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .height(56.dp)
                .align(Alignment.CenterHorizontally)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun UserDetailPreview() {
    ModalUserDetailView(
        user = User(
            gender = "female",
            name = Name(
                title = "Mrs",
                first = "Loane",
                last = "Dumas"
            ),
            location = Location(
                street = Street(
                    number = 16487,
                    name = "Rue Paul Bert"
                ),
                city = "Lyon",
                state = "Moselle",
                country = "France",
                postcode = "165512",
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
                uuid = "2a26160ab-2916-16e22-8cb8-b86c93627f7b",
                username = "tinycat670",
                password = "1818",
                salt = "b1PsfhCh",
                md5 = "531eac357d05d8f0160f9d5fe0df7161653",
                sha1 = "ad93e821e2b2ceb1487e16bc0811606fabd5c75fb16",
                sha256 = "9eb725916716b160c00599c313b680a96762a165daf6a68292d833b9a160d9167979c1"
            ),
            dob = Moment(
                date = Date(),
                age = 57
            ),
            registered = Moment(
                date = Date(),
                age = 16
            ),
            phone = "02-916-58-17-66",
            cell = "06-23-73-26-72",
            id = "INSEE" to "2670030812303 50",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/67.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/67.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/67.jpg"
            ),
            nat = "FR"
        ),
        sheetState = SheetState(
            skipPartiallyExpanded = true,
            density = Density(LocalContext.current)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}