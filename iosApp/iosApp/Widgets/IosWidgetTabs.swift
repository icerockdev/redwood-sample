//
//  IosWidgetTabs.swift
//  iosApp
//
//  Created by alobynya on 16.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetTabs: Tabs {
   
    private let root: UISegmentedControl = {
        let view = UISegmentedControl(items: ["one", "two"])
        return view

    }()
    
    func onClick(onClick_ onClick: [@convention(block) () -> KotlinUnit?]) {
      
    }
    
    func selectedTab(selectedTab: Int32) {
        root.selectedSegmentIndex = Int(selectedTab)
    }
    
    func texts(texts: [StringDesc]) {
        
        if(texts.isEmpty == false){
            for i in 0..<texts.count {
                root.setTitle(texts[i].localized(), forSegmentAt: i)
            }
        }
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
    
    
    
}
