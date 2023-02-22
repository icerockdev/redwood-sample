//
//  IosWidgetChip.swift
//  iosApp
//
//  Created by alobynya on 16.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetChip: Chip {
  
    private let root: UILabel = {
        let view = UILabel()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.layer.cornerRadius = 10
        view.numberOfLines = 1
        view.clipsToBounds = true
        return view

    }()
    
    func backgroundColor(backgroundColor: GraphicsColor?) {
       
    }
    
    func icon(icon: ImageResource?) {
    
    }
    
    func text(text: StringDesc) {
        root.text = text.localized()
    }
    
    func textColor(textColor: GraphicsColor?) {
        
    }
    
    func border(border: KotlinInt?) {
    
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
    
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}
