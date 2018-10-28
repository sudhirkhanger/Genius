package com.sudhirkhanger.genius.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sudhirkhanger.genius.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val aboutPage: View = AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.web_hi_res_512)
                .setDescription(getString(R.string.about_desc))
                .addItem(Element().setTitle(getString(R.string.about_version)))
                .addGroup(getString(R.string.about_group_title))
                .addEmail(getString(R.string.about_email_value),
                        getString(R.string.about_email_title))
                .addWebsite(getString(R.string.about_website_value),
                        getString(R.string.about_website_title))
                .addTwitter(getString(R.string.about_username_value),
                        getString(R.string.about_twitter_title))
                .addYoutube(getString(R.string.about_youtube_value),
                        getString(R.string.about_youtube_title))
                .addPlayStore(getString(R.string.about_play_value),
                        getString(R.string.about_play_title))
                .addGitHub(getString(R.string.about_username_value),
                        getString(R.string.about_github_title))
                .create()
        setContentView(aboutPage)
    }
}
