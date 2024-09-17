package es.marcrdz.ui.composables

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.marcrdz.domain.models.Coordinates
import es.marcrdz.domain.models.Location
import es.marcrdz.domain.models.Street
import es.marcrdz.domain.models.TimeZone
import es.marcrdz.ui.R

@Composable
fun UserActionsCardView(
    email: String,
    phone: String,
    location: Location,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Rounded.Email,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Google Maps Button",
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(all = 4.dp)
                    .clickable {
                        context.startActivity(
                            Intent.createChooser(
                                Intent(
                                    Intent.ACTION_SENDTO,
                                    Uri.fromParts("mailto", email, null)
                                ),
                                context.getString(R.string.send_email)
                            )

                        )
                    }
            )
            Icon(
                imageVector = Icons.Rounded.Phone,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Google Maps Button",
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(all = 4.dp)
                    .clickable {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_DIAL,
                                Uri.fromParts("tel", phone, null)
                            )
                        )
                    }
            )
            Icon(
                painter = painterResource(R.drawable.google_maps),
                contentDescription = "Google Maps Button",
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(all = 8.dp)
                    .clickable {
                        val mapIntent = location.run {
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    "geo:${coordinates.latitude}," +
                                            "${coordinates.longitude}?q=" +
                                            "${street.number}+${street.name.replace(" ", "+")}+" +
                                            "${location.postcode}+${location.city}"
                                )
                            ).apply { setPackage("com.google.android.apps.maps") }
                        }
                        try {
                            context.startActivity(mapIntent)
                        } catch (e: ActivityNotFoundException) {
                            Toast
                                .makeText(
                                    context,
                                    "No app found to handle this action!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserActionsCardViewPreview() {
    Column {
        UserActionsCardView(
            email = "loane.dumas@example.com",
            phone = "02-916-58-17-66",
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
            modifier = Modifier
                .height(56.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}