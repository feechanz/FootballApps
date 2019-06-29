package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.feechan.footballapps.R
import org.jetbrains.anko.*

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                bottomPadding = dip(10)
                leftPadding = dip(10)
                rightPadding = dip(10)

                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                backgroundColor = Color.TRANSPARENT

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER
                    backgroundColor = Color.WHITE
                    textView {
                        id = R.id.eventDateTextView
                        text = "Tanggal"
                        textSize = 15f
                        gravity = Gravity.CENTER
                        textColor = R.attr.colorPrimary

                    }.lparams {
                        gravity = Gravity.CENTER
                        topMargin = dip(10)
                    }

                    textView {
                        id = R.id.eventTimeTextView
                        text = "Waktu"
                        textSize = 13f
                        gravity = Gravity.CENTER
                        textColor = R.attr.colorPrimary

                    }.lparams {
                        gravity = Gravity.CENTER
                        topMargin = dip(10)
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.homeTeamTextView
                            text = "Man City"
                            singleLine = true
                            ellipsize = TextUtils.TruncateAt.END
                            textSize = 20f
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = dip(100)
                        }
                        linearLayout {
                            textView {
                                id = R.id.homeScoreTextView
                                text = "1"
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                                gravity = Gravity.CENTER
                            }.lparams {
                                weight = 1f
                                margin = dip(10)
                            }
                            textView {
                                text = "vs"
                                textSize = 18f
                                gravity = Gravity.CENTER
                            }.lparams {
                                weight = 1f
                                gravity = Gravity.CENTER
                            }
                            textView {
                                id = R.id.awayScoreTextView
                                text = "0"
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                                gravity = Gravity.CENTER
                            }.lparams {
                                weight = 1f
                                margin = dip(10)
                            }
                        }
                        textView {
                            id = R.id.awayTeamTextView
                            text = "Chelsea"
                            textSize = 20f
                            singleLine = true
                            ellipsize = TextUtils.TruncateAt.END
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = dip(100)
                        }
                    }
                }.lparams{
                    width = matchParent
                    height = matchParent
                }
            }
        }
    }
}