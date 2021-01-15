package com.webmyne.fooddelivery

import com.yarolegovich.slidingrootnav.SlidingRootNavLayout

interface SlidingRootNav {
    abstract fun isMenuClosed(): Boolean

    abstract fun isMenuOpened(): Boolean

    abstract fun isMenuLocked(): Boolean

    abstract fun closeMenu()

    abstract fun closeMenu(animated: Boolean)

    abstract fun openMenu()

    abstract fun openMenu(animated: Boolean)

    abstract fun setMenuLocked(locked: Boolean)

    abstract fun getLayout(): SlidingRootNavLayout
}