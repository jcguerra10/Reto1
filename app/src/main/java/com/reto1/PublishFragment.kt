package com.reto1

import android.R
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.reto1.databinding.FragmentPublishBinding
import com.reto1.model.Publication
import com.reto1.model.PublicationController
import com.reto1.model.UserController
import java.io.File

class PublishFragment : Fragment() {

    private var _binding:FragmentPublishBinding? = null
    private val binding get() = _binding!!

    private var file: File? = null

    private lateinit var publicationController: PublicationController
    private lateinit var userController: UserController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    private fun onCameraResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(file?.path)
            Log.e(">>>", file?.path+"")
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/3, bitmap.height/3, true)
            binding.imageView2.setImageBitmap(thumbnail)
        } else if (activityResult.resultCode == RESULT_CANCELED) {
            Toast.makeText(activity, "No Se Tomó la Foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGalleryResult(activityResult: ActivityResult?) {
        if (activityResult?.resultCode == RESULT_OK) {
            val uriImage = activityResult?.data?.data
            Log.e(">>>", uriImage?.path+"")
            uriImage?.let {
                binding.imageView2.setImageURI(uriImage)
            }
        }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onCameraResult)
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        //Camera
        binding.camaraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${activity?.getExternalFilesDir(null)}/photo.png")
            val uri = activity?.let { it1 -> FileProvider.getUriForFile(it1.applicationContext, it1.packageName, file!!) }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

            Log.e("----", file?.path.toString())

            cameraLauncher.launch(intent)
        }

        val lan = arrayOf("--ciudad--", "Cali", "Bogota", "Medellin", "Pereira")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item , lan)

        binding.ciudadSpinner.adapter = adapter

        //Add Publication
        binding.addPubBtn.setOnClickListener {
            Log.e(">>>", "")
            if (binding.descriptionTxt.text.toString().compareTo("") != 0  || binding.ciudadSpinner.selectedItemPosition != 0) {
                publicationController.addPublication(Publication("", binding.descriptionTxt.text.toString(), binding.ciudadSpinner.toString(),"Jc"))
                Toast.makeText(requireContext(), "Se ha creado" , Toast.LENGTH_SHORT).show()
            }
        }

        //Gallery
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PublishFragment()
    }



    fun setControllers(publicationController: PublicationController, userController: UserController) {
        this.publicationController = publicationController
        this.userController = userController
    }
}