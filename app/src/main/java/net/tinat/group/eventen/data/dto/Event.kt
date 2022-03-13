package net.tinat.group.eventen.data.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.time.Month

@Parcelize
data class Event(
    val id: String? = "",
    val logo: String? = "",
    val status: EventStatus? = EventStatus.COMING_SOON,
    val date: Date? = Date(),
    val eventName: String? = "",
    val description: String? = "",
    val time: Time? = Time(),
    val organizer: String? = "",
    val participates: List<Participates?>? = listOf(),
    val sponsors: List<Sponsors?>? = listOf(),
    val ticketsType: TicketsType? = TicketsType.VISITOR,
    val location: Location? = Location(),
    val popular: Boolean? = false
): Parcelable {
    @Parcelize
    enum class EventStatus: Parcelable {
        ENDED, COMING_SOON, ACTIVE_NOW
    }

    @Keep
    @Parcelize
    data class Date(
        val dayOfTheLong: Long? = 0L,
        val dayOfTheWeek: String? = "Saturday",
        val dayOfTheMonth: Int? = 1,
        val year: Int? = 2022,
        val month: String? = Month.JANUARY.name
    ) : Parcelable

    @Parcelize
    data class Time(
        val startingTime: HoursAndMinutes? = HoursAndMinutes(),
        val endingTime: HoursAndMinutes? = HoursAndMinutes()
    ): Parcelable {
        @Parcelize
        data class HoursAndMinutes(
            var hours: Int? = 0,
            val minutes: Int? = 0,
            var hoursFormat: HoursFormat? = HoursFormat.AM
        ): Parcelable
    }

    @Parcelize
    enum class TicketsType: Parcelable {
        VIP, VISITOR
    }

    @Parcelize
    data class Location(
        val country: String? = "",
        val city: String? = ""
    ): Parcelable

    @Parcelize
    enum class HoursFormat: Parcelable {
        AM, PM
    }
}