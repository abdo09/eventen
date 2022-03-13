package net.tinat.group.eventen.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
    data class Participates(
        val id: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val image: String = "",
        val jobDescription: String = "",
        val title: String = "",
        val voted: Boolean = false
    ): Parcelable