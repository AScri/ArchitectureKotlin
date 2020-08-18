package com.ascri.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ascri.test.R
import com.ascri.test.ui.base.BaseFragment
import com.ascri.test.ui.custom.ProgressIndicator
import kotlinx.android.synthetic.main.custom_indicator_fragment.*

class CustomIndicatorFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.custom_indicator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        deliveryIndicator.setUpIndicators(listOf(
            ProgressIndicator.IndicatorModel(name = "Started", drawableRes = R.drawable.ic_big_user),
            ProgressIndicator.IndicatorModel(name = "Packed", drawableRes = R.drawable.ic_big_user),
            ProgressIndicator.IndicatorModel(name = "Delivered", drawableRes = R.drawable.ic_big_user),
            ProgressIndicator.IndicatorModel(name = "Ended", drawableRes = R.drawable.ic_big_user)
        ))
        nextBtn.setOnClickListener {
            deliveryIndicator.selectNext()
        }
        beforeBtn.setOnClickListener {
            deliveryIndicator.selectBefore()
        }
    }

    companion object {
        fun newInstance() = CustomIndicatorFragment()
        const val TAG = "CUSTOM_INDICATOR_FRAGMENT"
    }
}
