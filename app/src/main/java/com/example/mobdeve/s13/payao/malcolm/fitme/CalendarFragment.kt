package com.example.mobdeve.s13.payao.malcolm.fitme


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.LinearLayoutManager


class CalendarFragment : Fragment(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var selectedDate: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        initWidgets(view)
        selectedDate = Calendar.getInstance()
        setMonthView()

        return view
    }

    private fun initWidgets(view: View) {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView)
        monthYearText = view.findViewById(R.id.monthYearTV)
        val previousButton = view.findViewById<Button>(R.id.previousbtn)
        val nextButton = view.findViewById<Button>(R.id.nextbtn)

        previousButton.setOnClickListener {
            previousMonthAction()
        }

        nextButton.setOnClickListener {
            nextMonthAction()
        }
    }


    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)
        val currentMonth = selectedDate.get(Calendar.MONTH)
        val currentYear = selectedDate.get(Calendar.YEAR)

        val calendarAdapter = CalendarAdapter(daysInMonth, this, currentMonth, currentYear)
        val layoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }


    private fun daysInMonthArray(date: Calendar): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)
        val maxDaysOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfMonth = Calendar.getInstance()
        firstDayOfMonth.set(year, month, 1)
        val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until firstDayOfWeek) {
            daysInMonthArray.add("")
        }

        for (day in 1..maxDaysOfMonth) {
            daysInMonthArray.add(day.toString())
        }

        while (daysInMonthArray.size % 7 != 0) {
            daysInMonthArray.add("")
        }

        return daysInMonthArray
    }

    private fun monthYearFromDate(date: Calendar): String {
        val month = date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
        val year = date.get(Calendar.YEAR)
        return "$month $year"
    }

    private fun previousMonthAction() {
        selectedDate.add(Calendar.MONTH, -1)
        setMonthView()
    }

    private fun nextMonthAction() {
        selectedDate.add(Calendar.MONTH, 1)
        setMonthView()
    }

    override fun onItemClick(position: Int, dayText: String?) {
        if (!dayText.isNullOrEmpty()) {
            val dialog = MyBottomSheetDialogFragment.newInstance(dayText)
            dialog.show(childFragmentManager, "MyBottomSheetDialogFragment")
        }
    }



    class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.calendar_bottomsheet, container, false)

            // Initialize the RecyclerView
            val recyclerView = view.findViewById<RecyclerView>(R.id.scheduledExercises)

            // Set the layout manager for your RecyclerView
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager

            // Initialize the list of exercises
            val exercises = ScheduledExerciseDataHelper.initializeExercises()

            // Create the adapter with the exercises list
            val adapter = ScheduledExerciseAdapter(exercises)

            // Set the adapter to your RecyclerView
            recyclerView.adapter = adapter

            return view
        }


        companion object {
            fun newInstance(dayText: String): MyBottomSheetDialogFragment {
                val fragment = MyBottomSheetDialogFragment()
                val args = Bundle()
                args.putString("selectedDay", dayText)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
