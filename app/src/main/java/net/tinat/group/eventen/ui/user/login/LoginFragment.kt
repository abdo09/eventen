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

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
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
            binding.edLoginEmailNumber.text.toString().isEmpty() -> {
                binding.ipLoginEmailNumber.setRedBoarder(R.string.email_number)
                false
            }
            binding.edLoginPassword.text.toString().isEmpty() -> {
                binding.ipLoginPassword.setRedBoarder(R.string.password)
                false
            }
            else -> true
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        binding.edLoginEmailNumber.setGrayBoarder(R.string.email_number, binding.ipLoginEmailNumber)
        binding.edLoginPassword.setGrayBoarder(R.string.password, binding.ipLoginPassword)
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
        binding.edLoginEmailNumber.text = null
        binding.edLoginPassword.text = null
    }

    //Handle fragment clicks
    private fun onClick() {
        binding.tvSignUp.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.btnLogin.setOnClickListener {
            if (isEntryValidated()){
                viewModel.signInUser(binding.edLoginEmailNumber.text.toString(), binding.edLoginPassword.text.toString())
            }

        }
    }

}