//
//  IosWidgetButton.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetButton: WidgetButton {
    
    private let root: UIButton = {
        let view = UIButton(type: .system)
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    func buttonType(buttonType: EntityButtonType) {
        
    }
    
    func borderColor(borderColor: ColorResource?){
        root.backgroundColor = borderColor?.getColor(
            userInterfaceStyle: .uiuserinterfacestylelight
        ).toUIColor()
    }
    
    func background(background: ColorResource?) {
        root.backgroundColor = background?.getColor(
            userInterfaceStyle: .uiuserinterfacestylelight
        ).toUIColor()
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
