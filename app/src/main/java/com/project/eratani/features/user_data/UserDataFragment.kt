package com.project.eratani.features.user_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.project.eratani.R
import com.project.eratani.core.data.source.Resource
import com.project.eratani.core.utils.showLongToast
import com.project.eratani.databinding.FragmentUserDataBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private var _binding: FragmentUserDataBinding? = null
    private val binding get() = _binding!!

    private val userDataViewmodel: UserDataViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUserData()
    }

    private fun fetchUserData() {
        userDataViewmodel.getUsers().observe(viewLifecycleOwner) { user ->
            when (user) {
                is Resource.Error -> {
                    showLoading(false)

                    Timber.tag("UserData").e("Error : ${user.message}")
                    activity?.showLongToast(user.message)
                }

                is Resource.Loading -> {
                    showLoading(true)
                }

                is Resource.Message -> {
                    showLoading(false)

                    Timber.e(user.message)
                    Timber.tag("UserData").e("Message : ${user.message}")
                }
                is Resource.Success -> {
                    showLoading(false)

                    val table = binding.tableLayout
                    user.data?.forEach{result ->
                        val row = LayoutInflater.from(context).inflate(R.layout.table_row, null) as TableRow

                        row.apply {
                            findViewById<TextView>(R.id.tv_row_name).text = result.name
                            findViewById<TextView>(R.id.tv_row_email).text = result.email
                            findViewById<TextView>(R.id.tv_row_gender).text = result.gender
                            findViewById<TextView>(R.id.tv_row_status).text = result.status
                        }

                        table.addView(row)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}