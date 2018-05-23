package com.moovel.gpsrecorderplayer.ui.records

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.moovel.gpsrecorderplayer.R
import com.moovel.gpsrecorderplayer.ui.record.RecordViewModel
import kotlinx.android.synthetic.main.records_fragment.*

class RecordsFragment : Fragment() {

    private lateinit var viewModel: RecordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.records_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_record_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.record_fragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
