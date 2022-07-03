package tn.swisaif.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import tn.swisaif.R
import tn.swisaif.adapters.AlbumsAdapter
import tn.swisaif.data.models.AlbumsItem
import tn.swisaif.data.models.UsersItem
import tn.swisaif.databinding.FragmentAlbumBinding
import tn.swisaif.ui.view_models.AlbumsViewModel
import tn.swisaif.ui.view_models.PhotosViewModel
import tn.swisaif.utils.Status

class AlbumFragment : Fragment(), AlbumsAdapter.ItemClickListener {
    private val albumViewModel: AlbumsViewModel by viewModel<AlbumsViewModel>()
    lateinit var binding: FragmentAlbumBinding
    var dataList: ArrayList<AlbumsItem> = ArrayList()
    var auteurs: ArrayList<UsersItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserverAuthors()
        setupObserverAlbums()
        initUi()
    }

    private fun initUi() {
        binding.albumRv.layoutManager = LinearLayoutManager(context)
        binding.albumRv.adapter = AlbumsAdapter(dataList, auteurs, this@AlbumFragment)
    }

    private fun setupObserverAuthors() {
        albumViewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    auteurs.clear()
                    it.data?.let { it1 -> auteurs.addAll(it1) }
                    binding.albumRv.adapter?.notifyDataSetChanged()

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
            }
        })


    }

    private fun setupObserverAlbums() {
        albumViewModel.albums.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    dataList.clear()
                    it.data?.let { it1 -> dataList.addAll(it1) }
                    dataList.sortBy { it.title }
                    binding.albumRv.adapter?.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
            }
        })


    }

    override fun onItemClick(view: View?, position: Int) {
        PhotosViewModel.albumId = dataList[position].id
        findNavController().navigate(R.id.photoFragment)
    }

}