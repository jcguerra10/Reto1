package com.reto1

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.content.Intent.CATEGORY_APP_GALLERY
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.reto1.databinding.FragmentNameChangeBinding
import com.reto1.databinding.FragmentProfileBinding
import com.reto1.model.PublicationController
import com.reto1.model.UserController
import java.io.File

class ProfileFragment : Fragment() , NameChangeFragment.OnListener {

    private var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var publicationController: PublicationController
    private lateinit var userController: UserController

    private var image: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    private fun onGalleryResult(activityResult: ActivityResult?) {
        if (activityResult?.resultCode == Activity.RESULT_OK) {
            val uriImage = activityResult?.data?.data
            if (uriImage != null) {
                userController.getActualUser().profileImage = uriImage.toString()
            }
            uriImage?.let {
                binding.profileImage.setImageURI(uriImage)
            }
        }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        binding.textView.text = userController.getActualUser().name

        //Gallery
        binding.changeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        binding.editNameBtn.setOnClickListener {
            val dialog = NameChangeFragment()
            dialog.listener = this
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "nameChange") }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    fun setControllers(publicationController: PublicationController, userController: UserController) {
        this.publicationController = publicationController
        this.userController = userController
    }

    override fun onListener(name: String) {
        binding.textView.text = name
        userController.getActualUser().name = name
    }

    override fun onResume() {
        super.onResume()
        val uri = Uri.parse(userController.getActualUser().profileImage)
        if (uri != null) {
            binding.profileImage.setImageURI(uri)
        } else {
            Toast.makeText(context, "No Tienes Imagen de Perfil", Toast.LENGTH_SHORT)
        }
    }
}