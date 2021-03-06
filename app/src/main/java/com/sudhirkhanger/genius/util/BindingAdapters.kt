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

package com.sudhirkhanger.genius.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.sudhirkhanger.genius.R

object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Picasso
                .get()
                .load(url)
                .fit()
                .centerCrop()
                .noFade()
                .placeholder(R.drawable.picasso_placeholder_image)
                .error(R.drawable.picasso_error_image)
                .into(view)
    }
}