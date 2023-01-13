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
        container.alignment = .fill
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
    func Button() -> WidgetButton {
        IosWidgetButton()
    }
    
    func Card() -> WidgetCard {
        IosWidgetCard()
    }
    
    func ImageButton() -> WidgetImageButton {
        IosImageButton()
    }
    
    func Stack() -> WidgetStack {
        IosStack()
    }
    
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

class IosButton: ButtonView{
    private let root: UIButton = {
        let view = UIButton()
        view.backgroundColor = .red
        return view
    }()
    
    func cornerRadius(cornerRadius: KotlinInt?) {
        root.layer.cornerRadius = CGFloat(cornerRadius?.floatValue ?? 0)
     }
    
    func text(text: String) {
        root.titleLabel?.text = text
   }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
  
    
    func borderColor(borderColor: ColorResource?){
        root.backgroundColor =  borderColor.color;
        
    }
    
    func background(background: ColorResource?) {
        root.layer.borderColor = borderColor.color;
    
    }
    
    func textStyle(textStyle: EntityTextStyle?) {
        root.titleLabel?.font =   root.titleLabel?.font?.withSize(CGFloat(textStyle?.textSize ?? 0))
        root.titleLabel.textColor = textStyle.textColor.color
    }
}

class IosWidgetButton: WidgetButton {
    private let root: UIButton = {
        let view = UIButton()
        return view
    }()
    
    func borderColor(borderColor: ColorResource?){
        root.backgroundColor = borderColor.color;
    }
    
    func background(background: ColorResource?) {
        
        ResourcesColorResource
        root.backgroundColor = background.color;
   }
    
    func textStyle(textStyle: EntityTextStyle?) {
        root.titleLabel
    }
    
    func cornerRadius(cornerRadius: KotlinInt?) {
        
    }
    
    func text(text: String) {
        root.titleLabel?.text = text
    }
 
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}

    
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
    
    func textStyle(textStyle: EntityTextStyle?) {
        root
    }
    
    func isSingleLine(isSingleLine: Bool) {
        root
    }
    
    func textStyle(textStyle: String?) {
        root
    }

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
    
    func textStyle(textStyle: EntityTextStyle?) {
        root
    }
    
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

class IosWidgetCard :  WidgetCard{
    
    private let root: UIView = {
        let view = UIView()
        view.backgroundColor = .blue
        return view
    }()
    
    func background(background: ColorResource?) {
        root.backgroundColor = background.color
    }

    func cornerRadius(cornerRadius: KotlinInt?) {
        root.layer.cornerRadius = CGFloat(cornerRadius?.floatValue ?? 0)
    }

    var child: Redwood_widgetWidgetChildren { ExposedKt.createChildView(parent: root)
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    var value: Any { root }

}

class IosImageButton: WidgetImageButton{
    private let root: UIView = {
        let view = UITextField()
        view.backgroundColor = .blue
        view.borderStyle = .roundedRect
        view.autocapitalizationType = .none
        return view
    }()
     
    func icon(icon: String?) {
        root
    }
    
    func iconPadding(iconPadding: KotlinInt?) {
        root
    }
    
    func text(text: String) {
        root
    }
    
    func textStyle(textStyle: EntityTextStyle?) {
        root
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    var value: Any { root }

}

class IosStack :  WidgetStack {
    private let root: UIView = {
        let view = UITextField()
        view.backgroundColor = .blue
        view.borderStyle = .roundedRect
        view.autocapitalizationType = .none
        return view
    }()

    var child1: Redwood_widgetWidgetChildren
    
    var child2: Redwood_widgetWidgetChildren
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    var value: Any { root }
    
}
