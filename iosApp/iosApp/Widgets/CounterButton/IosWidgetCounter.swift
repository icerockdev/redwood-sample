//
//  IosWidgetCounter.swift
//  iosApp
//
//  Created by alobynya on 10.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetCounter:  CounterButton{
 
    
    private let root: IosWidgetController = {
        let container = IosWidgetController()
       return container;
    }()
    
    func count(count: StringDesc) {
        root.setText(string: count.localized())
    }
    
    func onAddClick(onAddClick: (() -> Void)? = nil) {
        root.setOnInrimentClick(onClick: onAddClick)
    }
    
    func onRemoveClick(onRemoveClick: (() -> Void)? = nil) {
        root.setOnDecrementClick(onClick: onRemoveClick)
    }
    
    func width(width: EntitySize) {
        root.width(width: width)
    }
   
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
}

