package net.tinat.group.eventen.ui.user.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.LoginFragmentBinding
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import net.tinat.group.eventen.utils.setGrayBoarder
import net.tinat.group.eventen.utils.setRedBoarder
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseSupportFragment(){

    override val viewModel by viewModel<LoginFragmentViewModel>()

    override var navigationVisibility = View.GONE

    private var binding: LoginFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()

        viewModelObserver()

        setGrayBoarderToField()

        requireActivity().navigationBarAndStatusBarColor(R.color.purple_900, R.color.purple_900)

    }

    //Check fields are validated
    private fun isEntryValidated(): Boolean {
        return when {
            binding?.edLoginEmailNumber?.text.toString().isEmpty() -> {
                binding?.ipLoginEmailNumber?.setRedBoarder(R.string.email_number)
                false
            }
            binding?.edLoginPassword?.text.toString().isEmpty() -> {
                binding?.ipLoginPassword?.setRedBoarder(R.string.password)
                false
            }
            else -> true
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        binding?.apply {
            edLoginEmailNumber.setGrayBoarder(R.string.email_number, ipLoginEmailNumber)
            edLoginPassword.setGrayBoarder(R.string.password, ipLoginPassword)
        }
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
        binding?.apply {
            edLoginEmailNumber.text = null
            edLoginPassword.text = null
        }
    }

    //Handle fragment clicks
    private fun onClick() {
        binding?.apply {
            tvSignUp.setOnClickListener {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }

            btnLogin.setOnClickListener {
                if (isEntryValidated()){
                    viewModel.signInUser(edLoginEmailNumber.text.toString(), edLoginPassword.text.toString())
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}