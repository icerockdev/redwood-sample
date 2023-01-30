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
    
    private let root: FillButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 12, leading: 20, bottom: 12, trailing: 20)
        let view = FillButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        return view

    }()
    
    var _buttonType: EntityButtonType? = nil
    
    func buttonType(buttonType: EntityButtonType) {
        _buttonType = buttonType
        if(buttonType == EntityButtonType.primary){
            root.backgroundColor = mainColor
            root.layer.cornerRadius = 16
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.setTitleColor(.white, for: UIControl.State.normal)
            root.setTitleColor(.white, for: UIControl.State.disabled)
           }
        if(buttonType == EntityButtonType.secondary){
            root.setTitleColor(mainColor, for: UIControl.State.normal)
            root.setTitleColor(.lightGray, for: UIControl.State.disabled)
            root.backgroundColor = UIColor.clear
            root.layer.borderColor = mainColor.cgColor
            root.layer.borderWidth = 2
            root.layer.cornerRadius = 16
            root.contentHorizontalAlignment = .center
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.updateConfiguration()
        }
        if(buttonType == EntityButtonType.action){
            root.setTitleColor(mainColor, for: UIControl.State.normal)
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.backgroundColor = UIColor.clear
        }
    }
    
    func enabled(enabled: Bool) {
        root.isEnabled = enabled
        if(_buttonType == EntityButtonType.primary){
            if(enabled){
                root.backgroundColor = mainColor
            }else{
                root.backgroundColor = .lightGray
            }
        }
    }
    
    func text(text: StringDesc) {
        root.setTitle(text.localized(), for: .normal)
        root.setTitle(text.localized(), for: .disabled)
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

class FillButton : UIButton{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let originalSize = super.sizeThatFits(size)
            return CGSize(width: size.width, height: originalSize.height)
        }
}
