package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leedsbeckett.todo_application.databinding.FragmentItemDetailsDialogBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler


private const val BUNDLE_DETAIL_KEY = "detail"
class TaskDetailDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemDetailsDialogBinding
    private var details: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        details = requireArguments().getString(BUNDLE_DETAIL_KEY).toString()
        val dialog = BottomSheetDialog(requireContext())
        binding = FragmentItemDetailsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        if (binding.details.toString() != "") {
            val text = binding.todoTask.editableText
            text.append(details)
        }
        return dialog
    }

    companion object {

        fun newInstance(task: Task): TaskDetailDialogFragment {
            val bundle = Bundle(1)
            val detailFragment = TaskDetailDialogFragment()
            bundle.putString(BUNDLE_DETAIL_KEY,task.details)
            detailFragment.arguments = bundle
            return detailFragment
            }
    }
}