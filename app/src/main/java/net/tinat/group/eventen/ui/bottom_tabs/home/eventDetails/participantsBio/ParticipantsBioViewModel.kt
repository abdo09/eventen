package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsBio

import net.tinat.group.eventen.base.BaseViewModel

class ParticipantsBioViewModel: BaseViewModel() {
    var isExtended = false

    fun extend(){
        isExtended = !isExtended
    }
}