package ru.alex009.redwoodapp

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import app.cash.redwood.compose.RedwoodComposition
import app.cash.redwood.widget.UIViewChildren
import app.cash.redwood.widget.Widget
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import platform.Foundation.NSDefaultRunLoopMode
import platform.Foundation.NSRunLoop
import platform.Foundation.NSSelectorFromString
import platform.QuartzCore.CADisplayLink
import platform.UIKit.UIColor
import platform.UIKit.UILayoutConstraintAxisVertical
import platform.UIKit.UIStackView
import platform.UIKit.UIStackViewAlignmentFill
import platform.UIKit.UIStackViewDistributionFill
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.backgroundColor
import platform.UIKit.bottomAnchor
import platform.UIKit.leadingAnchor
import platform.UIKit.navigationController
import platform.UIKit.navigationItem
import platform.UIKit.safeAreaLayoutGuide
import platform.UIKit.setTranslatesAutoresizingMaskIntoConstraints
import platform.UIKit.tabBarController
import platform.UIKit.topAnchor
import platform.UIKit.widthAnchor

internal class ComposeViewController(
    private val provider: Widget.Provider<UIView>,
    private val isNavigationVisible: Boolean,
    private val viewModelOwner: ViewModelOwner,
    private val compose: @Composable () -> Unit
) : UIViewController(null, null) {
    private lateinit var displayLink: CADisplayLink
    private lateinit var delegate: RedwoodViewControllerDelegate

    override fun viewDidLoad() {
        super.viewDidLoad()
        val container = UIStackView()
        container.setAxis(UILayoutConstraintAxisVertical)
        container.setAlignment(UIStackViewAlignmentFill)
        container.setDistribution(UIStackViewDistributionFill)
        container.setTranslatesAutoresizingMaskIntoConstraints(false)
        container.backgroundColor = UIColor.whiteColor

        tabBarController?.navigationItem?.hidesBackButton = true

        view.addSubview(container)
        container.leadingAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.leadingAnchor).active =
            true
        container.topAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.topAnchor).active =
            true
        container.widthAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.widthAnchor).active =
            true
        container.bottomAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.bottomAnchor).active =
            true

        val delegate = RedwoodViewControllerDelegate(container, compose)
        this.delegate = delegate
    }

    override fun viewDidAppear(animated: Boolean) {
        super.viewDidAppear(animated)

        val displayLink = CADisplayLink.displayLinkWithTarget(
            target = this,
            selector = NSSelectorFromString("tickClock")
        )
        displayLink.addToRunLoop(NSRunLoop.currentRunLoop, NSDefaultRunLoopMode)
        this.displayLink = displayLink
        navigationController?.navigationBarHidden = isNavigationVisible.not()
    }

    @ObjCAction
    fun tickClock() {
        delegate.tickClock()
    }

    override fun viewDidDisappear(animated: Boolean) {
        super.viewDidDisappear(animated)

        displayLink.invalidate()
    }

    inner class RedwoodViewControllerDelegate(
        root: UIStackView,
        compose: @Composable () -> Unit
    ) {
        private val clock = BroadcastFrameClock()
        private val scope: CoroutineScope = MainScope() + clock

        init {
            val children = UIViewChildren(
                parent = root,
                insert = { view, index ->
                    root.insertArrangedSubview(
                        view,
                        atIndex = index.convert()
                    )
                }
            )
            val composition = RedwoodComposition(
                scope = scope,
                container = children,
                provider = provider
            )
            composition.setContent {
                compose.invoke()
            }
        }

        fun tickClock() {
            clock.sendFrame(0L) // Compose does not use frame time.
        }

        fun dispose() {
            scope.cancel()
            viewModelOwner.mutableMap.values.forEach {
                it.onCleared()
            }
            viewModelOwner.mutableMap.clear()
        }
    }
}
