package hu.unideb.noteit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)

        sharedPreferences = applicationContext.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE,
                                                                    Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(Constants.SHARED_PREFERENCES_IS_FIRST_START, true))
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.welcomeFragment)
    }
}