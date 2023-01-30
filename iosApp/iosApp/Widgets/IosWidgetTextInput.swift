//
//  IosWidgetTextInput.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetTextInput: WidgetTextInput {

    
    private let root: UITextField = {
        let view = FillUITextField()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.borderStyle = .roundedRect
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
        let action = UIAction(
            identifier: identifier,
            handler: { [unowned self] _ in onChange?(self.root.text ?? "") }
        )

        root.removeAction(identifiedBy: identifier, for: .editingChanged)
        root.addAction(action, for: .editingChanged)
    }
    
    func inputType(inputType: EntityInputType?) {
        if(inputType == EntityInputType.password){
            root.isSecureTextEntry = true
        }
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}

class FillUITextField : UITextField{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let originalSize = super.sizeThatFits(size)
            return CGSize(width: size.width, height: originalSize.height)
        }
}
