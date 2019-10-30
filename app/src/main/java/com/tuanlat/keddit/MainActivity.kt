package com.tuanlat.keddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tuanlat.keddit.features.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(tbMain)

        if (savedInstanceState == null) {
            changeFragment(NewsFragment())
        }
    }

    fun changeFragment(f: Fragment, cleanStack : Boolean = false){
        val ft = supportFragmentManager.beginTransaction()
        if(cleanStack){
            cleanBackStack()
        }
        ft.replace(R.id.frMain,f)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun cleanBackStack() {
        val manager = supportFragmentManager
        if(manager.backStackEntryCount > 0){
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1){
            fragmentManager.popBackStack()
        }
        else{
            finish()
        }
    }
}
