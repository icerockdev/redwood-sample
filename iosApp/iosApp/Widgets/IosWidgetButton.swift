//
//  IosWidgetButton.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

//todo remove to coloes
let mainColor = UIColor(red:55.0/255.0, green:121.0/255.0, blue:193.0/255.0, alpha:1.0)

class IosWidgetButton: WidgetButton {
    
    private let root: UIButton = {
        let view = UIButton(type: .custom)
        view.translatesAutoresizingMaskIntoConstraints = true
        return view

    }()
    
    func buttonType(buttonType: EntityButtonType) {
        if(buttonType == EntityButtonType.primary){
            root.titleLabel?.textColor = .white
            root.backgroundColor = mainColor
            root.layer.cornerRadius = 16
            root.heightAnchor.constraint(equalToConstant: 60).isActive = true
            
        }
        if(buttonType == EntityButtonType.secondary){
            root.titleLabel?.textColor = .white
            root.backgroundColor = UIColor.clear
            root.layer.borderColor = mainColor.cgColor
            root.layer.borderWidth = 2
            root.layer.cornerRadius = 16
        }
        if(buttonType == EntityButtonType.action){
            root.titleLabel?.textColor = mainColor
            root.backgroundColor = UIColor.clear
            root.layer.cornerRadius = 16
        }
    }
    
    func enabled(enabled: Bool) {
        // TODO
    }
    
    func cornerRadius(cornerRadius: KotlinInt?) {
        // TODO
    }
    
    func text(text: String) {
        root.setTitle(text, for: .normal)
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        let identifier = UIAction.Identifier("ButtonBinding.onPressed")
        let action = UIAction(
            identifier: identifier,
            handler: { _ in onClick?() }
        )

        root.removeAction(identifiedBy: identifier, for: .touchUpInside)
        root.addAction(action, for: .touchUpInside)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}
