//
//  ImageCardWithText.swift
//  iosApp
//
//  Created by alobynya on 22.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetImageCardWithText: ImageCardWithText {

    

    private let root: DividerView = {
        let view = DividerView()
        view.backgroundColor = black60
        return view

    }()
    
    func height(height: EntitySize?) {
  
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        
    }
    
    func placeholder(placeholder: ImageResource?) {
        
    }
    
    func text(text: StringDesc) {
        
    }
    
    func textBackgroundColor(textBackgroundColor: GraphicsColor?) {
        
    }
    
    func textColor(textColor: GraphicsColor?) {
        
    }
    
    func url(url: String?) {
        
    }
    
    func width(width_ width: EntitySize?) {
        
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}
