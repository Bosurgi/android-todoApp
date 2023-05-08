package com.leedsbeckett.todo_application.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leedsbeckett.todo_application.databinding.FragmentItemDetailsDialogBinding
import com.leedsbeckett.todo_application.model.Task


class TaskDetailDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemDetailsDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = BottomSheetDialog(requireContext())
        binding = FragmentItemDetailsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        return dialog
    }

    companion object {

        fun newInstance(): TaskDetailDialogFragment {
            return TaskDetailDialogFragment()
            }
    }
}