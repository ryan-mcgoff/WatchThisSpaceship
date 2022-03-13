package com.rmcgoff.watchthisspaceship.homeui.dialog

import android.app.Dialog
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rmcgoff.watchthisspaceship.commonui.Cell
import com.rmcgoff.watchthisspaceship.commonui.CustomDialog

@Composable
fun FilterDialog(
    title: String = "Sort Order",
    currentSelectedFilter: Filter,
    onFilterSelected: (Filter) -> Unit
) {
    CustomDialog(title = title) {
        LazyColumn {
            items(Filter.values().size) { pos ->
                val filter = Filter.values()[pos]
                Cell(trailingComposable = {
                    RadioButton(
                        selected = currentSelectedFilter.ordinal == filter.ordinal,
                        onClick = { onFilterSelected(filter) }
                    )
                }) {
                    Text(text = filter.name)
                }
            }
        }
    }
}
