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
    private let root: MyStackView = {
        let container = MyStackView()
        container.backgroundColor = .blue
        container.translatesAutoresizingMaskIntoConstraints = true
        return container;
    }()

    var child1: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }
    
    var child2: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }
    
    func myInsert(view: UIView,index: KotlinInt){
        root.insertSubview(view, at: 0)
        view.bottomAnchor.constraint(equalTo: root.bottomAnchor).isActive = true
        
        view.centerXAnchor.constraint(equalTo: root.centerXAnchor).isActive = true
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class MyStackView : UIView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            return size
        }
    }
}
