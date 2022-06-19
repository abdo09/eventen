package net.tinat.group.eventen.di

import net.tinat.group.eventen.data.repository.AuthRepository
import net.tinat.group.eventen.data.repository.CategoryRepository
import net.tinat.group.eventen.ui.bottom_tabs.activity.ActivitiesViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.HomeFragmentViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.EventDetailsViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsBio.ParticipantsBioViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList.ParticipantsListViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList.SponsorsListViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio.SponsorBioViewModel
import net.tinat.group.eventen.ui.bottom_tabs.profile.ProfileViewModel
import net.tinat.group.eventen.ui.bottom_tabs.tickets.TicketsViewModel
import net.tinat.group.eventen.ui.getTicketsFlow.getTickets.GetTicketsViewModel
import net.tinat.group.eventen.ui.getTicketsFlow.paymentFlow.PaymentViewModel
import net.tinat.group.eventen.ui.getTicketsFlow.ticketScreen.TicketScreenViewModel
import net.tinat.group.eventen.ui.user.login.LoginFragmentViewModel
import net.tinat.group.eventen.ui.user.signup.SignUpFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    viewModel { LoginFragmentViewModel(get()) }
    viewModel { SignUpFragmentViewModel(get()) }
    viewModel { HomeFragmentViewModel(get()) }
    viewModel { ActivitiesViewModel() }
    viewModel { TicketsViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { EventDetailsViewModel() }
    viewModel { ParticipantsBioViewModel() }
    viewModel { ParticipantsListViewModel() }
    viewModel { SponsorBioViewModel() }
    viewModel { SponsorsListViewModel() }
    viewModel { GetTicketsViewModel() }
    viewModel { PaymentViewModel() }
    viewModel { TicketScreenViewModel() }

    factory { AuthRepository(get()) }
    factory { CategoryRepository(get(), get()) }

}