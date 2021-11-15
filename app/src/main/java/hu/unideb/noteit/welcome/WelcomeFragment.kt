package hu.unideb.noteit.welcome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import hu.unideb.noteit.Constants
import hu.unideb.noteit.R
import hu.unideb.noteit.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedEdit: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Show the previously hidden actionbar
        (activity as AppCompatActivity).supportActionBar?.hide()

        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        sharedEdit = sharedPreferences.edit()

        binding.buttonBegin.setOnClickListener {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.welcomeFragment, true).build()
            if (sharedPreferences.getBoolean(Constants.SHARED_PREFERENCES_IS_FIRST_START, true))
                sharedEdit.putBoolean(Constants.SHARED_PREFERENCES_IS_FIRST_START, false)
            sharedEdit.apply()
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToNotesFragment(), navOptions)
        }

        return binding.root
    }

}