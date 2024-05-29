package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.marcrdz.presentation.models.BackgroundState

@Composable
fun SimpleLoadingDialog(
    state: BackgroundState,
    modifier: Modifier = Modifier
) {
    when (state) {
        BackgroundState.Idle -> Unit
        BackgroundState.Loading ->
            Dialog(
                onDismissRequest = {},
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(
                    modifier = modifier
                        .size(128.dp)
                        .background(Color.Black, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
    }

}