//
//  IosWidgetSearch.swift
//  iosApp
//
//  Created by alobynya on 26.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetSearch: NSObject, SearchRow {
    func onMicClick(onMicClick: (() -> Void)? = nil) {
        //do nothing
    }
    
    func showMic(showMic: Bool) {
        // do nothing
    }
    
    
    private let textView: UITextField = {
        let view = FillUITextField()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.borderStyle = .roundedRect
        return view
    }()
    
    override init() {
        super.init()
        
        textView.delegate = self
    }
    
    func state(state: String) {
        textView.text = state
    }

    func hint(hint: StringDesc) {
        textView.placeholder = hint.localized()
    }

    func onChange(onChange: ((String) -> Void)? = nil) {
        let identifier = UIAction.Identifier("TextInputBinding.onTextChanged")
        let action = UIAction(
            identifier: identifier,
            handler: { [unowned self] _ in onChange?(self.textView.text ?? "") }
        )

        textView.removeAction(identifiedBy: identifier, for: .editingChanged)
        textView.addAction(action, for: .editingChanged)
    }
    
    func inputType(inputType: EntityInputType?) {
        if(inputType == EntityInputType.password){
            textView.isSecureTextEntry = true
        }
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { textView }
}

extension IosWidgetSearch: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.endEditing(true)
        return false
    }
}

