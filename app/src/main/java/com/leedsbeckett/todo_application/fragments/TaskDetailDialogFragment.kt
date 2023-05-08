package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leedsbeckett.todo_application.databinding.FragmentItemDetailsDialogBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler

private const val BUNDLE_ID = "id"
private const val BUNDLE_NAME = "name"
private const val BUNDLE_STATUS = "status"
private const val BUNDLE_DETAIL_KEY = "detail"
class TaskDetailDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemDetailsDialogBinding
    private var details: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        details = requireArguments().getString(BUNDLE_DETAIL_KEY).toString()
        val id: String? = requireArguments().getString(BUNDLE_ID)

        val dialog = BottomSheetDialog(requireContext())
        binding = FragmentItemDetailsDialogBinding.inflate(layoutInflater)
        val addButton: Button = binding.addDescriptionButton
        dialog.setContentView(binding.root)

        val db: DatabaseHandler = DatabaseHandler(requireContext())

        addButton.setOnClickListener {
            val newDetails = binding.todoTask.text.toString()
            db.updateDetails(id!!, newDetails)
            dialog.dismiss()
        }

        if (binding.details.toString() != "") {
            val text = binding.todoTask.editableText
            val task = db.readSingleTask(id!!)
            text.append(task.details)
        }
        return dialog
    }

    companion object {

        fun newInstance(task: Task): TaskDetailDialogFragment {
            val bundle = Bundle(4)
            val detailFragment = TaskDetailDialogFragment()
            bundle.putString(BUNDLE_DETAIL_KEY,task.details)
            bundle.putString(BUNDLE_ID, task.id)
            detailFragment.arguments = bundle
            return detailFragment
            }
    }
}