package ru.alex009.redwoodapp

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import app.cash.redwood.compose.RedwoodComposition
import app.cash.redwood.widget.UIViewChildren
import kotlinx.cinterop.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import platform.QuartzCore.CADisplayLink
import platform.UIKit.UIStackView
import platform.UIKit.UIView
import platform.UIKit.safeAreaLayoutGuide
import platform.UIKit.UIViewController
import platform.UIKit.leadingAnchor
import platform.UIKit.topAnchor
import platform.UIKit.bottomAnchor
import platform.UIKit.trailingAnchor
import platform.UIKit.widthAnchor
import platform.UIKit.addSubview
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactories
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory

class ComposeViewController(
    val compose: @Composable (Navigator) -> Unit,
    val widgetFactory: RedwoodAppSchemaWidgetFactory<UIView>
) : UIViewController(null, null) {
    private lateinit var displayLink: CADisplayLink
    private lateinit var delegate: RedwoodViewControllerDelegate

    override fun viewDidLoad() {
        super.viewDidLoad()
        val container = UIStackView()
        // container.axis = .vertical
        //container.alignment = .fill
        //container.distribution = .fill
        // container.translatesAutoresizingMaskIntoConstraints = false


        view.addSubview(container)
        container.leadingAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.leadingAnchor).active = true
        container.topAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.topAnchor).active = true
        container.widthAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.widthAnchor).active = true
        container.bottomAnchor.constraintEqualToAnchor(view.safeAreaLayoutGuide.bottomAnchor).active = true

        val delegate = RedwoodViewControllerDelegate(container)
        this.delegate = delegate
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)

        let displayLink = CADisplayLink.init(target: self, selector: #selector(tickClock))
        displayLink.add(to: .current, forMode: .default)
        self.displayLink = displayLink
    }

    @objc func tickClock() {
        delegate.tickClock()
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)

        displayLink.invalidate()
    }

    inner class RedwoodViewControllerDelegate(
        root: UIStackView
    ) {
        private val clock = BroadcastFrameClock()
        private val scope: CoroutineScope = MainScope() + clock

        init {
            val children = UIViewChildren(
                parent = root,
                insert = { view, index -> root.insertArrangedSubview(view, atIndex = index.convert()) }
            )
            val composition = RedwoodComposition(
                scope = scope,
                container = children,
                provider = RedwoodAppSchemaWidgetFactories(
                    RedwoodAppSchema = widgetFactory,
                    RedwoodLayout = UIViewRedwoodLayoutWidgetFactory()
                )
            )
            composition.setContent {
                PostsList(){}
            }
        }

        fun tickClock() {
            clock.sendFrame(0L) // Compose does not use frame time.
        }

        fun dispose() {
            scope.cancel()
        }
    }
}