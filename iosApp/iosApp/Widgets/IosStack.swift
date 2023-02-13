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
        container.axis = .horizontal
        container.alignment = .lastBaseline
        container.distribution = .fillEqually
        container.translatesAutoresizingMaskIntoConstraints = true
        return container;
    }()

    var child1: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }
    
    var child2: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert2)
    }
    
    func myInsert(view: UIView,index: KotlinInt){
        root.insertArrangedSubview(view, at: 0)
        view.bottomAnchor.constraint(equalTo: root.bottomAnchor).isActive = true
        view.widthAnchor.constraint(equalTo: root.widthAnchor).isActive = true
        view.heightAnchor.constraint(equalTo: root.heightAnchor).isActive = true
        view.centerXAnchor.constraint(equalTo: root.centerXAnchor).isActive = true
    }
    
    func myInsert2(view: UIView,index: KotlinInt){
        root.insertArrangedSubview(view, at: 0)
        // todo fix size logic
        let size = view.sizeThatFits(CGSize(width: 200.0,height: 200.0))
        var height = 72
        if(size.height != 0) {
            height = Int(size.height)
            
        }
        view.widthAnchor.constraint(equalTo: root.widthAnchor).isActive = true
        view.heightAnchor.constraint(equalToConstant: CGFloat(height)).isActive = true
        view.bottomAnchor.constraint(equalTo: root.bottomAnchor).isActive = true
       }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class MyStackView : UIStackView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            return CGSize(width: size.width, height: size.height)
        }
    }
}
