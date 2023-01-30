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
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.baseBackgroundColor = UIColor.clear

        let view = UIButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.backgroundColor = UIColor.clear
        view.setTitleColor(.black, for: .normal)
        view.heightAnchor.constraint(equalToConstant: 60).isActive = true
        return view

    }()
  
    
    func icon(icon: ImageResource?) {
        root.setImage(icon?.toUIImage(), for: .normal)
    }
    
    func isClicked(isClicked: Bool) {
        if(isClicked){
            root.setTitleColor(mainColor, for: .normal)
            
        } else {
            root.setTitleColor(.gray, for: .normal)
            
        }
    }
    
    
    func text(text: StringDesc) {
        root.setTitle(text.localized(), for: .normal)
        root.titleLabel?.font = .italicSystemFont(ofSize: 12)
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
