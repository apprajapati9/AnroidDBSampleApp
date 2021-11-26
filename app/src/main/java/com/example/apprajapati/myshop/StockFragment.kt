package com.example.apprajapati.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apprajapati.myshop.data.Stock
import com.example.apprajapati.myshop.databinding.StockFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel
import com.example.apprajapati.myshop.viewmodel.StockViewModel

class StockFragment : Fragment() {

    private lateinit var binding: StockFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.stock_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myModel = activity?.run {
            ViewModelProvider(requireActivity())[StockViewModel::class.java]
        }

        myModel?.stockInfo?.observe(viewLifecycleOwner, {
            updateStockInfo(it)
        })

        binding.firm1Btn.setOnClickListener{
            myModel?.getStockData(1)
        }

        binding.firm2Btn.setOnClickListener{
            myModel?.getStockData(2)
        }

        binding.firm3Btn.setOnClickListener{
            myModel?.getStockData(3)
        }
    }

    private fun updateStockInfo(stock: Stock) {
        binding.firmLabelText.text = when(stock.firmId){
            1-> getString(R.string.firm_1_btn_label)
            2-> getString(R.string.firm_2_btn_label)
            3-> getString(R.string.firm_3_btn_label)
            else -> " "
        }
        binding.stockInfoText.text = getString(R.string.stock_info,
                    stock.open, stock.close, stock.change)
    }

}