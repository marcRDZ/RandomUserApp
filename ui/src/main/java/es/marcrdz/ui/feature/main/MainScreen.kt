package es.marcrdz.ui.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import es.marcrdz.presentation.handlers.MainEvent
import es.marcrdz.presentation.models.BackgroundState
import es.marcrdz.ui.composables.ItemsView
import es.marcrdz.ui.composables.MondlyScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier
) {

    MondlyScaffold(
        backgroundState = viewModel.backgroundState.value,
        failState = viewModel.failState.value,
        modifier = modifier,
        onFailure = { viewModel.onEvent(MainEvent.RetryOnError) }
    ) { padding ->

        viewModel.screenState.value.takeIf {
            it.items.isNotEmpty()
        }?.let {
            ItemsView(
                itemsList = it.items,
                isRefreshing = viewModel.backgroundState.value is BackgroundState.Loading,
                onRefresh = { viewModel.onEvent(MainEvent.RefreshOnSwipe) },
                modifier = Modifier.padding(padding)
            )
        } ?: run {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Text(
                    text = "No Data",
                    color = Color.LightGray,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }

}