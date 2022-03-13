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

    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)

        return binding.root
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
            binding.edSignUpFirstName.text.toString().isEmpty() -> {
                binding.ipSignUpFirstName.setRedBoarder(R.string.first_name)
                return false
            }
            binding.edSignUpLastName.text.toString().isEmpty() -> {
                binding.ipSignUpLastName.setRedBoarder(R.string.last_name)
                return false
            }
            binding.edSignUpEmail.text.toString().isEmpty() -> {
                binding.ipSignUpEmail.setRedBoarder(R.string.email)
                return false
            }
            binding.edSignUpNumber.text.toString().isEmpty() -> {
                binding.ipSignUpNumber.setRedBoarder(R.string.number)
                return false
            }
            binding.edSignUpCompanyOrgUn.text.toString().isEmpty() -> {
                binding.ipSignUpCompanyOrgUni.setRedBoarder(R.string.company_organization_university)
                return false
            }
            binding.edSignUpTitle.text.toString().isEmpty() -> {
                binding.ipSignUpTitle.setRedBoarder(R.string.title)
                return false
            }
            binding.edSignUpPassword.text.toString().isEmpty() -> {
                binding.ipSignUpPassword.setRedBoarder(R.string.password)
                return false
            }
            else -> return true
        }
    }

    //Handle fragment clicks
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onClick() {
        binding.btnSignUp.setOnClickListener {
            if (isEntryValidated()){
                viewModel.createUser(binding.edSignUpEmail.text.toString(), binding.edSignUpPassword.text.toString())
            }
        }

        binding.tvLogin.setOnClickListener {
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        binding.btnSignUpMale.setOnClickListener {
            if (!isMale){
                isMale = true
                setGenderChecked(isMale, requireContext())
            }
        }

        binding.btnSignUpFemale.setOnClickListener {
            if (isMale){
                isMale = false
                setGenderChecked(isMale, requireContext())
            }
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        binding.edSignUpFirstName.setGrayBoarder(R.string.first_name, binding.ipSignUpFirstName)
        binding.edSignUpLastName.setGrayBoarder(R.string.last_name, binding.ipSignUpLastName)
        binding.edSignUpEmail.setGrayBoarder(R.string.email, binding.ipSignUpEmail)
        binding.edSignUpNumber.setGrayBoarder(R.string.number, binding.ipSignUpNumber)
        binding.edSignUpCompanyOrgUn.setGrayBoarder(R.string.company_organization_university, binding.ipSignUpCompanyOrgUni)
        binding.edSignUpTitle.setGrayBoarder(R.string.title, binding.ipSignUpTitle)
        binding.edSignUpPassword.setGrayBoarder(R.string.password, binding.ipSignUpPassword)
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
        binding.edSignUpFirstName.text = null
        binding.edSignUpLastName.text = null
        binding.edSignUpEmail.text = null
        binding.edSignUpNumber.text = null
        binding.edSignUpCompanyOrgUn.text = null
        binding.edSignUpTitle.text = null
        binding.edSignUpPassword.text = null
    }

    // Set gender checked
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setGenderChecked(isMale: Boolean, context: Context) {
        if (isMale){
            binding.btnSignUpMale.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
            binding.tvSignUpMale.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
            binding.imageMale.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

            binding.btnSignUpFemale.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
            binding.tvSignUpFemale.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
            binding.imageFemale.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
        } else {
            binding.btnSignUpFemale.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
            binding.tvSignUpFemale.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
            binding.imageFemale.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

            binding.btnSignUpMale.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
            binding.tvSignUpMale.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
            binding.imageMale.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
        }
    }

}