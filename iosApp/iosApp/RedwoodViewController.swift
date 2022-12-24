//
//  RedwoodViewController.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 24.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import UIKit
import shared_ios

class RedwoodViewController: UIViewController {
    private var displayLink: CADisplayLink!
    private var delegate: RedwoodViewControllerDelegate!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let container = UIStackView()
        container.backgroundColor = .white
        container.axis = .vertical
        container.alignment = .leading
        container.distribution = .fill
        container.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(container)
        container.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        container.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        container.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
        container.heightAnchor.constraint(equalTo: view.heightAnchor).isActive = true

        let delegate = RedwoodViewControllerDelegate(
            root: container,
            widgetFactory: self
        )
        self.delegate = delegate
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

    deinit {
        delegate.dispose()
    }
}

extension RedwoodViewController: WidgetFactory {
    func Image() -> WidgetImage {
        IosWidgetImage()
    }
    
    func Text() -> WidgetText {
        IosWidgetText()
    }
    
    func TextInput() -> WidgetTextInput {
        IosWidgetTextInput()
    }
}

class IosWidgetImage: WidgetImage {
    private let root: UIImageView = {
        let view = UIImageView()
        view.backgroundColor = .red
        return view
    }()
    
    func url(url: String) {
        root.image = UIImage(systemName: url)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}

class IosWidgetText: WidgetText {
    private let root: UILabel = {
        let view = UILabel()
        view.backgroundColor = .green
        return view
    }()

    func text(text: String) {
        root.text = text
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}

class IosWidgetTextInput: WidgetTextInput {
    private let root: UITextField = {
        let view = UITextField()
        view.backgroundColor = .blue
        view.borderStyle = .roundedRect
        view.autocapitalizationType = .none
        return view
    }()
    
    func state(state: String) {
        root.text = state
    }

    func hint(hint: String) {
        root.placeholder = hint
    }

    func onChange(onChange: ((String) -> Void)? = nil) {
        let identifier = UIAction.Identifier("TextInputBinding.onTextChanged")

        root.removeAction(identifiedBy: identifier, for: .editingChanged)
        root.addAction(UIAction(identifier: identifier, handler: { [unowned self] _ in
            onChange?(self.root.text ?? "")
        }), for: .editingChanged)
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    var value: Any { root }
}
