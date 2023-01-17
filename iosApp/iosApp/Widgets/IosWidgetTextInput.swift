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
        let view = UITextField()
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

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
