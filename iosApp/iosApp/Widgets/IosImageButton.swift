//
//  IosImageButton.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosImageButton: WidgetImageButton {
    private let root: UIView = {
        let view = UIButton(type: .system)
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
     
    func icon(icon: String?) {
        // TODO
    }
    
    func iconPadding(iconPadding: KotlinInt?) {
        // TODO
    }
    
    func text(text: String) {
        // TODO
    }
    
    func textStyle(textStyle: EntityTextStyle?) {
        // TODO
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        // TODO
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
