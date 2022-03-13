package com.rmcgoff.watchthisspaceship.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launch_table")
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "missionName") val missionName: String,
    @ColumnInfo(name = "launchDateUtc") val launchDateUtc: String,
    @ColumnInfo(name = "launchDateUnix") val launchDateUnix: Long,
    @ColumnInfo(name = "rocketName") val rocketName: String,
    @ColumnInfo(name = "rocketType") val rocketType: String,
    @ColumnInfo(name = "wasSuccessful") val wasSuccessful: Boolean,
    @ColumnInfo(name = "patchImage") val patchImage: String,
    @ColumnInfo(name = "wikipediaLink") val wikipediaLink: String
)