package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.appcompat.R
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.*
import com.feechan.footballapps.base.BaseActivity
import org.jetbrains.anko.*

class DetailMatchUI : AnkoComponent<BaseActivity> {
    lateinit var scrollView: ScrollView
    lateinit var contentLayout: LinearLayout

    lateinit var progressBar: ProgressBar
    lateinit var dateTextView: TextView
    lateinit var timeTextView: TextView

    lateinit var homeScoreTextView: TextView
    lateinit var awayScoreTextView: TextView

    lateinit var homeImageView: ImageView
    lateinit var awayImageView: ImageView

    lateinit var homeNameTextView: TextView
    lateinit var awayNameTextView: TextView

    lateinit var homeFormationTextView: TextView
    lateinit var awayFormationTextView: TextView

    lateinit var homeGoalsTextView: TextView
    lateinit var awayGoalsTextView: TextView

    lateinit var homeShootsTextView: TextView
    lateinit var awayShootsTextView: TextView

    lateinit var homeGoalKeeperLineTextView: TextView
    lateinit var awayGoalKeeperLineTextView: TextView

    lateinit var homeDefenseLineTextView: TextView
    lateinit var awayDefenseLineTextView: TextView

    lateinit var homeMidfieldLineTextView: TextView
    lateinit var awayMidfieldLineTextView: TextView

    lateinit var homeForwardLineTextView: TextView
    lateinit var awayForwardLineTextView: TextView

    lateinit var homeSubstitutesTextView: TextView
    lateinit var awaySubstitutesTextView: TextView

    override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = Color.WHITE

            scrollView = scrollView {
                lparams(width = matchParent, height = matchParent)
                contentLayout = linearLayout {
                    orientation = LinearLayout.VERTICAL

                    dateTextView = textView {
                        text = ""
                        textColor = R.attr.colorPrimary
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        gravity = Gravity.CENTER
                        bottomMargin = dip(5)
                    }

                    timeTextView = textView {
                        text = ""
                        textColor = R.attr.colorPrimary
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        gravity = Gravity.CENTER
                        bottomMargin = dip(30)
                    }

                    linearLayout{
                        orientation = LinearLayout.HORIZONTAL

                        linearLayout{
                            lparams(width = wrapContent, height = wrapContent)
                            orientation = LinearLayout.VERTICAL
                            homeImageView = imageView{
                                backgroundColor = Color.TRANSPARENT
                            }.lparams{
                                width = dip(100)
                                height = dip(100)
                            }
                            homeNameTextView = textView{
                                text = ""
                                textColor = R.attr.colorPrimary
                                singleLine = true
                                ellipsize = TextUtils.TruncateAt.END
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams{
                                width = dip(100)
                                gravity = Gravity.CENTER
                            }
                            homeFormationTextView = textView{
                                text = ""
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams{
                                gravity = Gravity.CENTER
                            }
                        }

                        linearLayout {
                            gravity = Gravity.CENTER
                            homeScoreTextView = textView {
                                text = ""
                                textSize = 25f
                                setTypeface(null, Typeface.BOLD)
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                            }.lparams {
                                gravity = Gravity.CENTER
                                width = dip(15)
                            }
                            textView {
                                text = "vs"
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                gravity = Gravity.CENTER
                                margin = dip(10)
                            }
                            awayScoreTextView = textView {
                                text = ""
                                textSize = 25f
                                setTypeface(null, Typeface.BOLD)
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            }.lparams {
                                gravity = Gravity.CENTER
                                width = dip(15)
                            }
                        }.lparams{
                            width = dip(0)
                            height = wrapContent
                            gravity = Gravity.CENTER
                            weight = 1f
                        }


                        linearLayout{
                            lparams(width = wrapContent, height = wrapContent)
                            orientation = LinearLayout.VERTICAL
                            awayImageView = imageView{
                                backgroundColor = Color.TRANSPARENT
                            }.lparams{
                                width = dip(100)
                                height = dip(100)
                            }
                            awayNameTextView = textView{
                                text = ""
                                textColor = R.attr.colorPrimary
                                singleLine = true
                                ellipsize = TextUtils.TruncateAt.END
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams{
                                width = dip(100)
                                gravity = Gravity.CENTER
                            }
                            awayFormationTextView = textView{
                                text = ""
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams{
                                gravity = Gravity.CENTER
                            }
                        }
                    }

                    view{
                        backgroundColor = Color.GRAY
                    }.lparams(width = matchParent, height = dip(1)){
                        bottomMargin = dip(2)
                        topMargin = dip(2)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeGoalsTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Goals"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayGoalsTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeShootsTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }
                        textView{
                            text = "Shots"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayShootsTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(10)
                    }

                    view{
                        backgroundColor = Color.GRAY
                    }.lparams(width = matchParent, height = dip(1)){
                        bottomMargin = dip(2)
                        topMargin = dip(2)
                    }

                    textView{
                        text = "Lineups"
                        textSize = 20f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeGoalKeeperLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Goal Keeper"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayGoalKeeperLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeDefenseLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Defense"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayDefenseLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeMidfieldLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Midfield"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayMidfieldLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeForwardLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Forward"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awayForwardLineTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        homeSubstitutesTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        }.lparams{
                            width = dip(100)
                        }
                        textView{
                            text = "Substitutes"
                            textColor = R.attr.colorPrimary
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                            weight = 1f
                        }
                        awaySubstitutesTextView = textView{
                            text = ""
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        }.lparams{
                            width = dip(100)
                        }
                    }.lparams{
                        width = matchParent
                        height = wrapContent
                        bottomMargin = dip(20)
                    }
                }.lparams{
                    width = matchParent
                    height = wrapContent
                    margin = dip(20)
                }
            }
            progressBar = progressBar {
            }.lparams {
                centerHorizontally()
            }
        }

    }
}