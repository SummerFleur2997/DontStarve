package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class WorldEvents {
	val locationListChanged = mutableListOf<EventHandler<LocationListChangedEventArgs>>()
	val buildingListChanged = mutableListOf<EventHandler<BuildingListChangedEventArgs>>()
	val debrisListChanged = mutableListOf<EventHandler<DebrisListChangedEventArgs>>()
	val largeTerrainFeatureListChanged = mutableListOf<EventHandler<LargeTerrainFeatureListChangedEventArgs>>()
	val npcListChanged = mutableListOf<EventHandler<NpcListChangedEventArgs>>()
	val objectListChanged = mutableListOf<EventHandler<ObjectListChangedEventArgs>>()
	val chestInventoryChanged = mutableListOf<EventHandler<ChestInventoryChangedEventArgs>>()
	val terrainFeatureListChanged = mutableListOf<EventHandler<TerrainFeatureListChangedEventArgs>>()
	val furnitureListChanged = mutableListOf<EventHandler<FurnitureListChangedEventArgs>>()

	@CName("onWorldEventsLocationListChanged")
	fun onLocationListChanged(ref: COpaquePointer) {
		val e = LocationListChangedEventArgs()
		locationListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsBuildingListChanged")
	fun onBuildingListChanged(ref: COpaquePointer) {
		val e = BuildingListChangedEventArgs()
		buildingListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsDebrisListChanged")
	fun onDebrisListChanged(ref: COpaquePointer) {
		val e = DebrisListChangedEventArgs()
		debrisListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsLargeTerrainFeatureListChanged")
	fun onLargeTerrainFeatureListChanged(ref: COpaquePointer) {
		val e = LargeTerrainFeatureListChangedEventArgs()
		largeTerrainFeatureListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsNpcListChanged")
	fun onNpcListChanged(ref: COpaquePointer) {
		val e = NpcListChangedEventArgs()
		npcListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsObjectListChanged")
	fun onObjectListChanged(ref: COpaquePointer) {
		val e = ObjectListChangedEventArgs()
		objectListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsChestInventoryChanged")
	fun onChestInventoryChanged(ref: COpaquePointer) {
		val e = ChestInventoryChangedEventArgs()
		chestInventoryChanged.forEach { it(e) }
	}

	@CName("onWorldEventsTerrainFeatureListChanged")
	fun onTerrainFeatureListChanged(ref: COpaquePointer) {
		val e = TerrainFeatureListChangedEventArgs()
		terrainFeatureListChanged.forEach { it(e) }
	}

	@CName("onWorldEventsFurnitureListChanged")
	fun onFurnitureListChanged(ref: COpaquePointer) {
		val e = FurnitureListChangedEventArgs()
		furnitureListChanged.forEach { it(e) }
	}
}

class LocationListChangedEventArgs
class BuildingListChangedEventArgs
class DebrisListChangedEventArgs
class LargeTerrainFeatureListChangedEventArgs
class NpcListChangedEventArgs
class ObjectListChangedEventArgs
class ChestInventoryChangedEventArgs
class TerrainFeatureListChangedEventArgs
class FurnitureListChangedEventArgs