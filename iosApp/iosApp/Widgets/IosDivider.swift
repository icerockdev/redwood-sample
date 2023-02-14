//
//  IosDivider.swift
//  iosApp
//
//  Created by alobynya on 14.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//


import UIKit
import shared_ios


class IosWidgetDivider: Divider {
    func isVertical(isVertical: Bool) {
        // todo
    }

    private let root: DividerView = {
        let view = DividerView()
        view.backgroundColor = black60
        return view

    }()
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}

class DividerView : UIView{
    
    var widgetWidth : EntitySize = EntitySize.Wrap() {
        didSet {
            setNeedsLayout()
        }
    }
    
    var contentSize : CGSize = CGSize(width: 48, height: 48)
    
    override var intrinsicContentSize: CGSize {
       return contentSize
    }
    
        override func sizeThatFits(_ size: CGSize) -> CGSize {
      
            contentSize =  CGSize(width:  size.width, height: 1)
            return contentSize
      
        }
}

