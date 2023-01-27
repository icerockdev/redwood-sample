//
//  IosWidgetSpace.swift
//  iosApp
//
//  Created by alobynya on 27.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetSpace: Space {
   
    private let root: UIView = {
        let container = UIView()

        container.translatesAutoresizingMaskIntoConstraints = true
        return container;
    }()
    
    func background(background: GraphicsColor) {
        root.backgroundColor = UIColor.clear
    }
    
    func height(height_ height: Int32) {
        root.heightAnchor.constraint(equalToConstant: CGFloat(height)).isActive = true
    }
    
    func width(width_ width: Int32) {
        root.widthAnchor.constraint(equalToConstant: CGFloat(width)).isActive = true
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
  
    var value: Any { root }
    
    
}
