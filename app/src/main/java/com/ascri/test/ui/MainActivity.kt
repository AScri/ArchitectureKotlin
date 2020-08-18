package com.ascri.test.ui

import android.os.Bundle
import com.ascri.test.R
import com.ascri.test.ui.base.BaseActivity
import com.ascri.test.ui.main.CustomIndicatorFragment
import com.ascri.test.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CustomIndicatorFragment.newInstance())
                .commitNow()
        }
    }
}
