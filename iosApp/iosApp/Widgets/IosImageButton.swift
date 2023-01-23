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
  
    private let root: UIButton = {
        let view = UIButton(type: .custom)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.backgroundColor = UIColor.clear
        view.setTitleColor(.black, for: .normal)
        view.heightAnchor.constraint(equalToConstant: 60).isActive = true
        return view

    }()
    
    func cornerRadius(cornerRadius: KotlinInt?) {
        // TODO
    }
    
    func icon(icon: ImageResource?) {
        root.setImage(icon?.toUIImage(), for: .normal)
    }
    
    func isClicked(isClicked: Bool) {
        // TODO
    }
    
    
    func text(text: String) {
    //    root.setTitle(text, for: .normal)
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
