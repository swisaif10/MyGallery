package tn.swisaif.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import tn.swisaif.adapters.PhotosAdapter
import tn.swisaif.data.models.PhotosItem
import tn.swisaif.databinding.FragmentPhotoBinding
import tn.swisaif.ui.view_models.PhotosViewModel
import tn.swisaif.utils.Status


class PhotoFragment : Fragment(), PhotosAdapter.ItemClickListener {
    private val photoViewModel: PhotosViewModel by viewModel<PhotosViewModel>()
    lateinit var binding: FragmentPhotoBinding
    var dataList: ArrayList<PhotosItem> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setupObserverPhotos()
    }

    private fun initUi() {
        binding.photoRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.photoRv.adapter = PhotosAdapter(dataList, this@PhotoFragment)
    }

    private fun setupObserverPhotos() {
        photoViewModel.photos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.photoPb.visibility = View.GONE
                    dataList.clear()
                    it.data?.let { it1 -> dataList.addAll(it1) }
                    binding.photoRv.adapter?.notifyDataSetChanged()

                }

                Status.LOADING -> {
                    binding.photoPb.visibility = View.VISIBLE


                }
                Status.ERROR -> {
                    binding.photoPb.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
            }
        })

    }

    override fun onItemClick(view: View?, position: Int) {

    }

}