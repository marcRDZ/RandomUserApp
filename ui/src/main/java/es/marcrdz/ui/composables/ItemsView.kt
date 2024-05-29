package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.marcrdz.domain.models.Attributes
import es.marcrdz.domain.models.Item
import es.marcrdz.ui.theme.Grey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsView(
    itemsList: List<Item>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onRefresh() }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(count = itemsList.size) { index ->
                ItemCardView(
                    item = itemsList[index]
                )
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ItemsPreview() {
    ItemsView(
        itemsList = listOf(
            Item(
                id = "1", attributes = Attributes(
                    name = "Item name 1",
                    description = "Description of item 1",
                    imgUrl = "https://picsum.photos/id/101/300/200"
                )
            ),
            Item(
                id = "2", attributes = Attributes(
                    name = "Item name 2",
                    description = "Description of item 2",
                    imgUrl = "https://picsum.photos/id/101/300/200"
                )
            ),
            Item(
                id = "3", attributes = Attributes(
                    name = "Item name 3",
                    description = "Description of item 3",
                    imgUrl = "https://picsum.photos/id/101/300/200"
                )
            )
        ),
        isRefreshing = false,
        onRefresh = {}
    )
}
