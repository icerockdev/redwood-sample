//
//  IosStack.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosStack :  WidgetStack {
    private let root: IosStackController = {
        let container = IosStackController()
        return container;
    }()

    var child1: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }
    
    var child2: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert2)
    }
    
    func myInsert(view: UIView,index: KotlinInt){
        root.isertFullView(value:view)
     }
    
    func myInsert2(view: UIView,index: KotlinInt){
        root.isertBottomView(value:view)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
    
  
}

class MyStackView : UIStackView{
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        return CGSize(width: size.width, height: size.height)
    }
}
