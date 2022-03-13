package net.tinat.group.eventen.ui.bottom_tabs.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.data.dto.Participates
import net.tinat.group.eventen.data.dto.Sponsors
import net.tinat.group.eventen.data.repository.CategoryRepository

class HomeFragmentViewModel(private val categoryRepository: CategoryRepository) : BaseViewModel() {

    private var categoriesList: MutableLiveData<List<String>>? = MutableLiveData()

    fun getCategories(): MutableLiveData<List<String>>? {

        showLoading.value = false
        val listOfCategories = arrayListOf<String>()
        categoryRepository.getCategoryList()?.get()?.addOnSuccessListener { documents ->
            showLoading.value = false
            for (document in documents) {
                listOfCategories.add(document.id)
                //Log.d("GDSGFGASG", document.getString("nameEn").toString())
            }
            categoriesList?.value = listOfCategories
        }
            ?.addOnFailureListener {
                showLoading.value = false
            }

        return categoriesList
    }

    fun events() = listOf(
        Event(
            id = "0",
            eventName = "EVENTEN",
            location = Event.Location(
                country = "Saudi arabia",
                city = "Riyadh"
            ),
            time = Event.Time(
                startingTime = Event.Time.HoursAndMinutes(
                    hours = 11,
                    minutes = 45
                ),
                endingTime = Event.Time.HoursAndMinutes(
                    hours = 15,
                    minutes = 45
                )
            ),
            date = Event.Date(
                dayOfTheMonth = 22,
                dayOfTheWeek = "MONDAY"
            ),
            participates = listOf(
                Participates(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                ),
                Participates(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Participates(
                    id = "1",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "1",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
            ),
            sponsors = listOf(
                Sponsors(
                    id = "4",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
                Sponsors(
                    id = "3",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Sponsors(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                )
            ),
            popular = true,
            status = Event.EventStatus.ACTIVE_NOW
        ),
        Event(
            id = "1",
            eventName = "BESUDAN",
            location = Event.Location(
                country = "Sudan",
                city = "Khartoum"
            ),
            time = Event.Time(
                startingTime = Event.Time.HoursAndMinutes(
                    hours = 13,
                    minutes = 30
                ),
                endingTime = Event.Time.HoursAndMinutes(
                    hours = 17,
                    minutes = 0
                )
            ),
            date = Event.Date(
                dayOfTheMonth = 14,
                dayOfTheWeek = "SATURDAY"
            ),
            participates = listOf(
                Participates(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                ),
                Participates(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Participates(
                    id = "1",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "1",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
            ),
            sponsors = listOf(
                Sponsors(
                    id = "4",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
                Sponsors(
                    id = "3",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Sponsors(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                )
            ),
            popular = true,
            status = Event.EventStatus.COMING_SOON
        ),
        Event(
            id = "2",
            eventName = "TIRHAL",
            location = Event.Location(
                country = "Sudan",
                city = "Khartoum"
            ),
            time = Event.Time(
                startingTime = Event.Time.HoursAndMinutes(
                    hours = 12,
                    minutes = 0
                ),
                endingTime = Event.Time.HoursAndMinutes(
                    hours = 23,
                    minutes = 45
                )
            ),
            date = Event.Date(
                dayOfTheMonth = 9,
                dayOfTheWeek = "SUNDAY"
            )
        ),
        Event(
            id = "3",
            eventName = "Winter land",
            location = Event.Location(
                country = "Saudi arabia",
                city = "Riyadh"
            ),
            time = Event.Time(
                startingTime = Event.Time.HoursAndMinutes(
                    hours = 19,
                    minutes = 0
                ),
                endingTime = Event.Time.HoursAndMinutes(
                    hours = 2,
                    minutes = 45
                )
            ),
            date = Event.Date(
                dayOfTheMonth = 4,
                dayOfTheWeek = "TUESDAY"
            )
        ),
        Event(
            id = "4",
            eventName = "Revolution day",
            location = Event.Location(
                country = "Sudan",
                city = "Omdurman"
            ),
            time = Event.Time(
                startingTime = Event.Time.HoursAndMinutes(
                    hours = 13,
                    minutes = 0
                ),
                endingTime = Event.Time.HoursAndMinutes(
                    hours = 17,
                    minutes = 50
                )
            ),
            date = Event.Date(
                dayOfTheMonth = 6,
                dayOfTheWeek = "FRIDAY"
            ),
            participates = listOf(
                Participates(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                ),
                Participates(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Participates(
                    id = "1",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Participates(
                    id = "1",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
            ),
            sponsors = listOf(
                Sponsors(
                    id = "4",
                    firstName = "Isam",
                    lastName = "Alsafi",
                    title = "Leader",
                    jobDescription = "Brand specialist"
                ),
                Sponsors(
                    id = "3",
                    firstName = "Moe",
                    lastName = "Alsafi",
                    title = "Listener",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "2",
                    firstName = "Timpo",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Photographer"
                ),
                Sponsors(
                    id = "1",
                    firstName = "Succuc",
                    lastName = "Saif",
                    title = "Visitor",
                    jobDescription = "Programmer"
                ),
                Sponsors(
                    id = "0",
                    firstName = "Abdo",
                    lastName = "Omer",
                    title = "Speaker",
                    jobDescription = "Graphic designer"
                )
            ),
            popular = true,
            status = Event.EventStatus.ACTIVE_NOW
        )
    )
}