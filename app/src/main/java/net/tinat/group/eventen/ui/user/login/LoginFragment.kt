package net.tinat.group.eventen.ui.user.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.login_fragment.*
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import net.tinat.group.eventen.utils.setGrayBoarder
import net.tinat.group.eventen.utils.setRedBoarder
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseSupportFragment(R.layout.login_fragment){
    override val viewModel by viewModel<LoginFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationVisibility = View.GONE

        onClick()

        viewModelObserver()

        setGrayBoarderToField()

        requireActivity().navigationBarAndStatusBarColor(R.color.purple_900, R.color.purple_900)

    }

    //Check fields are validated
    private fun isEntryValidated(): Boolean {
        return when {
            ed_login_emailNumber.text.toString().isEmpty() -> {
                ip_login_emailNumber.setRedBoarder(R.string.email_number)
                false
            }
            ed_login_password.text.toString().isEmpty() -> {
                ip_login_password.setRedBoarder(R.string.password)
                false
            }
            else -> true
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        ed_login_emailNumber.setGrayBoarder(R.string.email_number, ip_login_emailNumber)
        ed_login_password.setGrayBoarder(R.string.password, ip_login_password)
    }

    //ViewModel observer
    private fun viewModelObserver() {
        viewModel.uId.observe(viewLifecycleOwner){ uId -> Constants().setUid(requireContext(), uId?: "") }

        viewModel.isUserSignedIn.observe(viewLifecycleOwner){ userSignedIn ->

            if (userSignedIn){

                navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

                clearAllFields()
            }

        }
    }

    //Clear all fields
    private fun clearAllFields() {
        ed_login_emailNumber.text = null
        ed_login_password.text = null
    }

    //Handle fragment clicks
    private fun onClick() {
        tv_signUp.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        btn_login.setOnClickListener {
            if (isEntryValidated()){
                viewModel.signInUser(ed_login_emailNumber.text.toString(), ed_login_password.text.toString())
            }

        }
    }

}