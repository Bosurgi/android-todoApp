package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.databinding.FragmentItemDetailsDialogBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler

private const val BUNDLE_ID = "id"
private const val BUNDLE_DETAIL_KEY = "detail"
class TaskDetailDialogFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemDetailsDialogBinding
    private var details: String = ""
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        details = requireArguments().getString(BUNDLE_DETAIL_KEY).toString()
        val id: String? = requireArguments().getString(BUNDLE_ID)
        // Initialising the dialog page
        val dialog = BottomSheetDialog(requireContext())
        // Initialising the binding inflating the layout
        binding = FragmentItemDetailsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Add Detail button
        val addButton: Button = binding.addDescriptionButton
        // Interaction behaviour for bottom sheet
        bottomSheetBehavior = BottomSheetBehavior.from(binding.root.parent as View)

        // Initialising the database
        val db: DatabaseHandler = DatabaseHandler(requireContext())

        // Setting up the add detail button listener
        addButton.setOnClickListener {
            val newDetails = binding.todoTask.text.toString()
            db.updateDetails(id!!, newDetails)
            dialog.dismiss()
        }
        val textView = binding.fullDetails
        val task = db.readSingleTask(id!!)

        // Displaying standard text if task details are empty
        if (task.details == "") {
            textView.text = getString(R.string.no_details)
        } else { textView.text = task.details }


        return dialog
    }

    override fun onStart() {
        super.onStart()
        /**
         * This will force the bottom sheet to be always opened as expanded
         * This is used to rotate the screen and having the sheet on the screen.
          */
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
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