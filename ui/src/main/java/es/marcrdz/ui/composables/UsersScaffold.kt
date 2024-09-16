package es.marcrdz.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.marcrdz.domain.models.Fail
import es.marcrdz.presentation.models.BackgroundState
import es.marcrdz.ui.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScaffold(
    backgroundState: BackgroundState,
    failState: Fail?,
    modifier: Modifier = Modifier,
    onFailure: (Fail) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    failState?.let { fail ->
        coroutineScope.launch {
            val state = snackBarHostState.showSnackbar(
                message = "Something went wrong!",
                actionLabel = "Retry"
            )

            state.takeIf {
                it == SnackbarResult.ActionPerformed
            }?.let {
                onFailure(fail)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.TwoTone.Home,
                            contentDescription = "home icon"
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(stringResource(id = R.string.app_name))
                    }

                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    actionIconContentColor = MaterialTheme.colorScheme.secondary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) {
        content(it)
        SimpleLoadingDialog(state = backgroundState)
    }

}
