package com.christopher_elias.navigation.transactions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.christopher_elias.navigation.R

/*
 * Created by Christopher Elias on 29/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

/**
 * Extension for add or replace the fragment to the container.
 * [TransactionType.ADD_FRAGMENT] - Will put the [newFragment] on top of the current fragment.
 * [TransactionType.REPLACE_FRAGMENT] - Will replace the current fragment for the [newFragment] & the current fragment would not be visible.
 *
 * @param transactionType a [TransactionType] object.
 * @param newFragment the fragment to ADD or REPLACE.
 * @param addToBackStack if true then, the [newFragment] will be added to the back stack.
 * @param containerId the VIEW container id. The ID passed here it's supposed to be the ID of the View that contains the Fragment/s. Usually a FrameLayout or a FragmentContainerView.
 * @param transactionAnimations a [TransactionAnimations] object that will determine the animation to apply to the current transaction.
 */
internal fun FragmentTransaction.fragmentTransactionExt(
    transactionType: TransactionType,
    newFragment: Fragment,
    addToBackStack: Boolean,
    containerId: Int,
    fragmentTag: String,
    transactionAnimations: TransactionAnimations
) {
    Log.i(
        "TransactionExt",
        "transactionType: $transactionType | addToBackStack: $addToBackStack | containerId: $containerId | fragmentTag: $fragmentTag | transactionAnimations: $transactionAnimations"
    )
    when (transactionAnimations) {
        TransactionAnimations.NONE -> {
            /* None transition animation is applied */
        }
        TransactionAnimations.BOTTOM_TO_TOP -> {
            setCustomAnimations(
                R.anim.slide_in_from_bottom, R.anim.slide_out_from_top,
                R.anim.slide_in_from_bottom, R.anim.slide_out_from_top
            )
        }
        TransactionAnimations.RIGHT_TO_LEFT -> {
            setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right
            )
        }
    }

    when (transactionType) {
        TransactionType.ADD_FRAGMENT -> {
            add(
                containerId,
                newFragment,
                fragmentTag
            ).also {
                if (addToBackStack) {
                    it.addToBackStack(fragmentTag)
                }
            }.commit()
        }
        TransactionType.REPLACE_FRAGMENT -> {
            replace(
                containerId,
                newFragment,
                fragmentTag
            ).also {
                if (addToBackStack) {
                    it.addToBackStack(fragmentTag)
                }
            }.commit()
        }
    }
}

enum class TransactionAnimations {
    RIGHT_TO_LEFT,
    BOTTOM_TO_TOP,
    NONE
}

enum class TransactionType {
    /**
     * Add fragment on top of the current fragment that call this method.
     */
    ADD_FRAGMENT,

    /**
     * Replace the current fragment for a new fragment that call this method.
     */
    REPLACE_FRAGMENT
}