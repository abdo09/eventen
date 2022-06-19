package net.tinat.group.eventen.ui.user.signup

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.SignUpFragmentBinding
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.setGrayBoarder
import net.tinat.group.eventen.utils.setRedBoarder
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment : BaseSupportFragment() {

    private var isMale = true
    override val viewModel by viewModel<SignUpFragmentViewModel>()

    private var binding: SignUpFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationVisibility = View.GONE

        onClick()

        setGrayBoarderToField()

        viewModelObserver()

    }

    //Check fields are validated
    private fun isEntryValidated(): Boolean {
        when {
            binding?.edSignUpFirstName?.text.toString().isEmpty() -> {
                binding?.ipSignUpFirstName?.setRedBoarder(R.string.first_name)
                return false
            }
            binding?.edSignUpLastName?.text.toString().isEmpty() -> {
                binding?.ipSignUpLastName?.setRedBoarder(R.string.last_name)
                return false
            }
            binding?.edSignUpEmail?.text.toString().isEmpty() -> {
                binding?.ipSignUpEmail?.setRedBoarder(R.string.email)
                return false
            }
            binding?.edSignUpNumber?.text.toString().isEmpty() -> {
                binding?.ipSignUpNumber?.setRedBoarder(R.string.number)
                return false
            }
            binding?.edSignUpCompanyOrgUn?.text.toString().isEmpty() -> {
                binding?.ipSignUpCompanyOrgUni?.setRedBoarder(R.string.company_organization_university)
                return false
            }
            binding?.edSignUpTitle?.text.toString().isEmpty() -> {
                binding?.ipSignUpTitle?.setRedBoarder(R.string.title)
                return false
            }
            binding?.edSignUpPassword?.text.toString().isEmpty() -> {
                binding?.ipSignUpPassword?.setRedBoarder(R.string.password)
                return false
            }
            else -> return true
        }
    }

    //Handle fragment clicks
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onClick() {
        binding?.btnSignUp?.setOnClickListener {
            if (isEntryValidated()){
                viewModel.createUser(binding?.edSignUpEmail?.text.toString(), binding?.edSignUpPassword?.text.toString())
            }
        }

        binding?.tvLogin?.setOnClickListener {
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        binding?.btnSignUpMale?.setOnClickListener {
            if (!isMale){
                isMale = true
                setGenderChecked(isMale, requireContext())
            }
        }

        binding?.btnSignUpFemale?.setOnClickListener {
            if (isMale){
                isMale = false
                setGenderChecked(isMale, requireContext())
            }
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        binding?.apply {
            edSignUpFirstName.setGrayBoarder(R.string.first_name, ipSignUpFirstName)
            edSignUpLastName.setGrayBoarder(R.string.last_name, ipSignUpLastName)
            edSignUpEmail.setGrayBoarder(R.string.email, ipSignUpEmail)
            edSignUpNumber.setGrayBoarder(R.string.number, ipSignUpNumber)
            edSignUpCompanyOrgUn.setGrayBoarder(R.string.company_organization_university, ipSignUpCompanyOrgUni)
            edSignUpTitle.setGrayBoarder(R.string.title, ipSignUpTitle)
            edSignUpPassword.setGrayBoarder(R.string.password, ipSignUpPassword)
        }
    }

    //ViewModel observer
    private fun viewModelObserver() {
        viewModel.isUserCreated.observe(viewLifecycleOwner){ userCreated ->

            if (userCreated){

                viewModel.uId.observe(viewLifecycleOwner){ uId -> Constants().setUid(requireContext(), uId?: "") }

                navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())

                clearAllFields()

            }
        }
    }

    //Clear all fields
    private fun clearAllFields() {
        binding?.apply {
            edSignUpFirstName.text = null
            edSignUpLastName.text = null
            edSignUpEmail.text = null
            edSignUpNumber.text = null
            edSignUpCompanyOrgUn.text = null
            edSignUpTitle.text = null
            edSignUpPassword.text = null
        }
    }

    // Set gender checked
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setGenderChecked(isMale: Boolean, context: Context) {
        binding?.apply {
            if (isMale){
                btnSignUpMale.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
                tvSignUpMale.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
                imageMale.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

                btnSignUpFemale.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
                tvSignUpFemale.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
                imageFemale.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
            } else {
                btnSignUpFemale.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
                tvSignUpFemale.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
                imageFemale.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

                btnSignUpMale.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
                tvSignUpMale.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
                imageMale.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}