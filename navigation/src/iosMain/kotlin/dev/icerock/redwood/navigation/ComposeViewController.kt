/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

//internal class ComposeViewController(
//    private val provider: Widget.Provider<UIView>,
//    private val compose: @Composable () -> Unit
//) : UIViewController(null, null) {
//    private lateinit var displayLink: CADisplayLink
//    private lateinit var delegate: RedwoodViewControllerDelegate
//
//    override fun viewDidLoad() {
//        super.viewDidLoad()
//        val container = UIStackView()
//        container.setAxis(UILayoutConstraintAxisVertical)
//        container.setAlignment(UIStackViewAlignmentFill)
//        container.setDistribution(UIStackViewDistributionFill)
//        container.setTranslatesAutoresizingMaskIntoConstraints(false)
//        container.backgroundColor = UIColor.whiteColor
//
//        tabBarController?.navigationItem?.hidesBackButton = true
//
//        view.addSubview(container)
//        container.leadingAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.leadingAnchor).active =
//            true
//        container.topAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.topAnchor).active =
//            true
//        container.widthAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.widthAnchor).active =
//            true
//        container.bottomAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.bottomAnchor).active =
//            true
//
//        val delegate = RedwoodViewControllerDelegate(container, compose)
//        this.delegate = delegate
//    }
//
//    override fun viewDidAppear(animated: Boolean) {
//        super.viewDidAppear(animated)
//
//        val displayLink = CADisplayLink.displayLinkWithTarget(
//            target = this,
//            selector = NSSelectorFromString("tickClock")
//        )
//        displayLink.addToRunLoop(NSRunLoop.currentRunLoop, NSDefaultRunLoopMode)
//        this.displayLink = displayLink
//    }
//
//    @ObjCAction
//    fun tickClock() {
//        delegate.tickClock()
//    }
//
//    override fun viewDidDisappear(animated: Boolean) {
//        super.viewDidDisappear(animated)
//
//        displayLink.invalidate()
//    }
//}
