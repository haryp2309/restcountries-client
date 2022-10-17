package no.vipps.summerinternship.helper.observable

class ObservableList<T>: Iterable<T> {
    private val observers = ArrayList<ObservableListListener<T>>()
    private val list = ArrayList<T>()

    val size: Int
        get() = list.size

    fun addListener(listener: ObservableListListener<T>) {
        observers += listener
        list.forEachIndexed { index, item ->
            listener.addedListener(item, index)
        }
    }

    fun removeListener(listener: ObservableListListener<T>) {
        observers -= listener
    }

    private fun notifyAdded(obj: T, index: Int) = observers.forEach { it.addedListener(obj, index) }
    private fun notifyRemoved(obj: T, index: Int) = observers.forEach { it.removedListener(obj, index) }
    private fun notifyCleared(count: Int) = observers.forEach { it.clearedListener(count) }

    fun add(obj: T) {
        val index = list.lastIndex + 1
        list.add(index, obj)
        notifyAdded(obj, index)
    }
    fun addAll(iterable: Iterable<T>) {
        iterable.forEach(this::add)
    }

    fun remove(obj: T) {
        val index = list.indexOf(obj)
        list.removeAt(index)
        notifyRemoved(obj, index)
    }

    fun clear() {
        val size = this.size
        list.clear()
        notifyCleared(size)
    }


    operator fun plusAssign(obj: T) = add(obj)
    operator fun plusAssign(iterable: Iterable<T>) = addAll(iterable)

    operator fun minusAssign(obj: T) = remove(obj)

    operator fun get(index: Int) = list[index]


    override fun iterator(): Iterator<T> = list.iterator()

}