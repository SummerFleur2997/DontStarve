interface TimeApi {
	val time: Long
	val onLoad: MutableList<(Long) -> Unit>
	val onUpdate: MutableList<(Long) -> Unit>
	val onSync: MutableList<(Long, Long) -> Unit>
}