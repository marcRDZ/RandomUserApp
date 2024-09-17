package es.marcrdz.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleLabelledContentView(
    label: String,
    content: String,
    modifier: Modifier = Modifier,
    separator: String = ":",
    fontSize: TextUnit = 14.sp
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$label$separator",
            fontSize = fontSize,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = content,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}