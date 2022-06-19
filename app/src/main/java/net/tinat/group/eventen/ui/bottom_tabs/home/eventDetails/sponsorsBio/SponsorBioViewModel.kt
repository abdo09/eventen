package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio

import net.tinat.group.eventen.base.BaseViewModel

class SponsorBioViewModel: BaseViewModel() {
    var isExtended = false

    fun extend(){
        isExtended = !isExtended
    }
}