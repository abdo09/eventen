package net.tinat.group.eventen.ui.user.signup

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.sign_up_fragment.*
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.setGrayBoarder
import net.tinat.group.eventen.utils.setRedBoarder
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpFragment : BaseSupportFragment(R.layout.sign_up_fragment) {
    private var isMale = true
    override val viewModel by viewModel<SignUpFragmentViewModel>()

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
            ed_signUp_firstName.text.toString().isEmpty() -> {
                ip_signUp_firstName.setRedBoarder(R.string.first_name)
                return false
            }
            ed_signUp_lastName.text.toString().isEmpty() -> {
                ip_signUp_lastName.setRedBoarder(R.string.last_name)
                return false
            }
            ed_signUp_email.text.toString().isEmpty() -> {
                ip_signUp_email.setRedBoarder(R.string.email)
                return false
            }
            ed_signUp_number.text.toString().isEmpty() -> {
                ip_signUp_number.setRedBoarder(R.string.number)
                return false
            }
            ed_signUp_company_org_un.text.toString().isEmpty() -> {
                ip_signUp_company_org_uni.setRedBoarder(R.string.company_organization_university)
                return false
            }
            ed_signUp_title.text.toString().isEmpty() -> {
                ip_signUp_title.setRedBoarder(R.string.title)
                return false
            }
            ed_signUp_password.text.toString().isEmpty() -> {
                ip_signUp_password.setRedBoarder(R.string.password)
                return false
            }
            else -> return true
        }
    }

    //Handle fragment clicks
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onClick() {
        btn_signUp.setOnClickListener {
            if (isEntryValidated()){
                viewModel.createUser(ed_signUp_email.text.toString(), ed_signUp_password.text.toString())
            }
        }

        tv_login.setOnClickListener {
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }

        btn_signUp_male.setOnClickListener {
            if (!isMale){
                isMale = true
                setGenderChecked(isMale, requireContext())
            }
        }

        btn_signUp_female.setOnClickListener {
            if (isMale){
                isMale = false
                setGenderChecked(isMale, requireContext())
            }
        }
    }

    //Set default boarder
    private fun setGrayBoarderToField() {
        ed_signUp_firstName.setGrayBoarder(R.string.first_name, ip_signUp_firstName)
        ed_signUp_lastName.setGrayBoarder(R.string.last_name, ip_signUp_lastName)
        ed_signUp_email.setGrayBoarder(R.string.email, ip_signUp_email)
        ed_signUp_number.setGrayBoarder(R.string.number, ip_signUp_number)
        ed_signUp_company_org_un.setGrayBoarder(R.string.company_organization_university, ip_signUp_company_org_uni)
        ed_signUp_title.setGrayBoarder(R.string.title, ip_signUp_title)
        ed_signUp_password.setGrayBoarder(R.string.password, ip_signUp_password)
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
        ed_signUp_firstName.text = null
        ed_signUp_lastName.text = null
        ed_signUp_email.text = null
        ed_signUp_number.text = null
        ed_signUp_company_org_un.text = null
        ed_signUp_title.text = null
        ed_signUp_password.text = null
    }

    // Set gender checked
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setGenderChecked(isMale: Boolean, context: Context) {
        if (isMale){
            btn_signUp_male.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
            tv_signUp_male.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
            image_male.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

            btn_signUp_female.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
            tv_signUp_female.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
            image_female.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
        } else {
            btn_signUp_female.background = ContextCompat.getDrawable(context, R.drawable.boarder_purple700_color)
            tv_signUp_female.setHintTextColor(ContextCompat.getColor(context, R.color.purple_700))
            image_female.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked_gender))

            btn_signUp_male.background = ContextCompat.getDrawable(context, R.drawable.boarder_gray_color)
            tv_signUp_male.setHintTextColor(ContextCompat.getColor(context, R.color.grayColor))
            image_male.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unchecked_gender))
        }
    }

}