package android.fundamental.githubapp.ui

import android.fundamental.githubapp.data.adapter.UserAdapter
import android.fundamental.githubapp.data.response.ItemsItem
import android.fundamental.githubapp.databinding.FragmentFollowBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class FollowFragment : Fragment() {

    private val followViewModel by viewModels<FollowViewModel>()
    private lateinit var binding: FragmentFollowBinding
    private var position: Int = 0
    private var username: String? = null

    companion object {
        const val ARG_POSITION = "arg_position"
        const val ARG_USERNAME = "arg_username"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        followViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
        followViewModel.followers.observe(viewLifecycleOwner) { followers ->
            setFollow(followers)
        }
        if (position == 1) {
            followViewModel.getFollowers(username ?: "")
        } else {
            followViewModel.getFollowing(username ?: "")
        }
        return binding.root
    }

    private fun setFollow(account: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(account)
        binding.rvFollow.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
