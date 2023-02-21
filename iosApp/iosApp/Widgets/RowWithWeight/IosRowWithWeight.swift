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
    
    private let root: RowWithWeightViewController = {
        let container = RowWithWeightViewController()
        return container;
    }()

    var childs: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }

    
    func myInsert(view: UIView,index: KotlinInt){
        root.addPage(subView: view, index: index, weight: 1)
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
}
