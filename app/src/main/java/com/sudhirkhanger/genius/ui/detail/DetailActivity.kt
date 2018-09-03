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

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.databinding.ActivityDetailBinding
import com.sudhirkhanger.genius.ui.list.MainActivityFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.extras.getInt(MainActivityFragment.KEY_MOVIE)

        val detailViewModelFactory = DetailViewModelFactory(movieId)
        val detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(
                DetailViewModel::class.java)

        val binding: ActivityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.setLifecycleOwner(this)

        binding.viewModel = detailViewModel
    }
}
