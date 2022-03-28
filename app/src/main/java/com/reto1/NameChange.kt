package com.reto1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.reto1.databinding.FragmentNameChangeBinding
import com.reto1.databinding.FragmentProfileBinding

class NameChangeFragment : DialogFragment() {

    private var _binding: FragmentNameChangeBinding? = null
    private val binding get() = _binding!!

    var listener:OnListener? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNameChangeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.changeNameBtn.setOnClickListener {
            listener?.onListener(binding.newNameTxt.text.toString())
            dismiss()
        }

        return view
    }

    interface OnListener {
        fun onListener(name:String)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = NameChangeFragment()
    }

}