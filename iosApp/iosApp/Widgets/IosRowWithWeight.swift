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
    
    private let root: RowWithWeightView = {
        let container = RowWithWeightView()
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
    }

    
    func myInsert(view: UIView,index: KotlinInt, layoutModifier: Redwood_runtimeLayoutModifier){
        if(layoutModifier is Redwood_layout_layoutmodifiersPadding){
            // do something
            root.insertArrangedSubview(view, at: index.intValue)
        }else{
            root.insertArrangedSubview(view, at: index.intValue)
        }
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class RowWithWeightView : UIStackView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let chilsdsHeight = subviews.max { a, b in
                a.frame.maxY < b.frame.maxY
            }?.frame.maxY
            return CGSize(width: size.width, height:  (344) + 12)
        }
    }
}
