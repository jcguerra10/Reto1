package com.reto1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reto1.databinding.FragmentHomeBinding
import com.reto1.model.PublicationAdapter
import com.reto1.model.PublicationController
import com.reto1.model.UserController

class HomeFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var publicationController: PublicationController
    private lateinit var userController: UserController

    private lateinit var publicationAdapter: PublicationAdapter


    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding.publicationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            publicationAdapter = PublicationAdapter()
            adapter = publicationAdapter
            publicationAdapter.setPublications(publicationController, userController)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onResume() {
        super.onResume()
        Log.e(">>>", "Resume")
        publicationAdapter.refresh()
    }

    fun setControllers(publicationController: PublicationController, userController: UserController) {
        this.publicationController = publicationController
        this.userController = userController
    }
}