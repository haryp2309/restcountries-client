package no.vipps.summerinternship.helper.observable

class ObservableListListener<T>(
    val addedListener: (item: T, index: Int) -> Unit,
    val removedListener: (item: T, index: Int) -> Unit,
    val updatedListener: (item: T, index: Int) -> Unit,
    val clearedListener: (count: Int) -> Unit
)