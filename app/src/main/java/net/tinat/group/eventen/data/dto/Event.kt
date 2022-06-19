package net.tinat.group.eventen.data.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.time.Month

@Parcelize
data class Event(
    var id: String? = "",
    var logo: String? = "",
    var timestamp: Long? = 0L,
    var status: EventStatus? = EventStatus.COMING_SOON,
    var date: Date? = Date(),
    var eventName: String? = "",
    var description: String? = "",
    var time: Time? = Time(),
    var organizer: String? = "",
    var participates: List<Participates?>? = listOf(),
    var sponsors: List<Sponsors?>? = listOf(),
    var ticketsType: TicketsType? = TicketsType.VISITOR,
    var location: Location? = Location(),
    var popular: Boolean? = false
): Parcelable {
    @Parcelize
    enum class EventStatus: Parcelable {
        ENDED, COMING_SOON, ACTIVE_NOW
    }


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

    /*fun formattedDate(event: Event):Event{
    }*/
}