//
//  IosRowWithWeight.swift
//  iosApp
//
//  Created by alobynya on 08.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosRowWithWeight :  RowWithWeight {
    
    private let root: UIStackView = {
        let container = UIStackView()
        container.axis = .horizontal
        container.alignment = .top
        container.distribution = .fillProportionally
        container.translatesAutoresizingMaskIntoConstraints = false
        return container;
    }()

    var childs: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    
    func myInsert(view: UIView,index: KotlinInt){
        root.insertArrangedSubview(view, at:  index.intValue)
        view.heightAnchor.constraint(equalToConstant: 72).isActive = true
    }



    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class RowWithWeightView : UIStackView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let chilsdsHeight = subviews.max { a, b in
                a.frame.maxY < b.frame.maxY
            }?.frame.maxY
            return CGSize(width: size.width, height:  200)
        }
    }
}
