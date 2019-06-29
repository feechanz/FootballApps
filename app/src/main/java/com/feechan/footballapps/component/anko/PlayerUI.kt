package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.feechan.footballapps.R
import org.jetbrains.anko.*

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.profile_picture
                }.lparams{
                    width = dip(50)
                    height = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                    width = dip(0)
                    weight = 1f
                }

                textView {
                    id = R.id.player_position
                    textSize = 16f
                    textColor = Color.GRAY
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }
}