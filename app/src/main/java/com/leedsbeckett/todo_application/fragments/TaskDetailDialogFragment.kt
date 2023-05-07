package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leedsbeckett.todo_application.databinding.FragmentItemDetailsDialogBinding


class TaskDetailDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemDetailsDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // TODO: Implement
    }

    companion object {

        fun newInstance(): TaskDetailDialogFragment {
            // TODO: Implement
            return TaskDetailDialogFragment()
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}