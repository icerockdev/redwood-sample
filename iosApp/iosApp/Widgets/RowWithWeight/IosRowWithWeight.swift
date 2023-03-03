//
//  IosRowWithWeight.swift
//  iosApp
//
//  Created by alobynya on 21.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosRowWithWeight :  RowWithWeight {
   
    func weight(weight: [KotlinInt]?) {
        // do thomesing
    }
    
    
    private let root: RowWithWeightViewController = {
        let container = RowWithWeightViewController()
        return container;
    }()

    var childs: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenModifierListener(parent: root.view, insert: myInsert)
    }

    
    func myInsert(
        view: Redwood_widgetWidget,
        index: KotlinInt){
            root.addPage(subView: view, index: index)
        }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
}
