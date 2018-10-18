/*
 * Copyright 2018 Sudhir Khanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sudhirkhanger.genius.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.databinding.FragmentDetailBinding
import com.sudhirkhanger.genius.ui.list.MainActivityFragment
import com.sudhirkhanger.genius.util.BindingAdapters

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_detail, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = detailViewModel
        return binding.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val movieId = activity?.intent?.extras?.getInt(MainActivityFragment.KEY_MOVIE)

        val detailViewModelFactory = DetailViewModelFactory(movieId!!)
        detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(
                DetailViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val backdropImageView = activity?.findViewById(R.id.backdrop_image_view) as ImageView
        val collapsingToolbarLayout =
                activity?.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)

        detailViewModel.movie.observe(this, Observer {
            backdropImageView.viewTreeObserver.addOnGlobalLayoutListener(
                    object : ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            backdropImageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                            BindingAdapters.loadImage(backdropImageView, "https://image.tmdb.org/t/p/w300${it?.backdropPath}")
                        }
                    })
            collapsingToolbarLayout?.title = it?.title
            collapsingToolbarLayout?.setExpandedTitleColor(
                    ContextCompat.getColor(context!!, android.R.color.transparent))
        })
    }
}
