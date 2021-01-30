package net.tinat.group.eventen.di

import net.tinat.group.eventen.data.repository.AuthRepository
import net.tinat.group.eventen.data.repository.CategoryRepository
import net.tinat.group.eventen.ui.bottom_tabs.activity.ActivitiesViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.HomeFragmentViewModel
import net.tinat.group.eventen.ui.bottom_tabs.profile.ProfileViewModel
import net.tinat.group.eventen.ui.bottom_tabs.tickets.TicketsViewModel
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

    factory { AuthRepository(get()) }
    factory { CategoryRepository(get(), get()) }

}