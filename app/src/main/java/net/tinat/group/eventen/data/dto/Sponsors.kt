package net.tinat.group.eventen.data.dto

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
    data class Sponsors(
        val id: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val image: String = "",
        val jobDescription: String = "",
        val title: String = "",
        val voted: Boolean = false,
        val fcm: String? = ""
    ): Parcelable