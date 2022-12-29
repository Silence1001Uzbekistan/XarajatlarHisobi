package com.example.xarajatlarhisobi

import android.os.Binder
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.xarajatlarhisobi.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        setHasOptionsMenu(true)*/


        binding.buttonOne.setOnClickListener {

            findNavController().navigate(R.id.thirdFragment)

        }

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            R.id.calculateId -> {


                Toast.makeText(requireContext(), "MENUUUUUUU", Toast.LENGTH_SHORT).show()

            }


        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_top, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }*/


}