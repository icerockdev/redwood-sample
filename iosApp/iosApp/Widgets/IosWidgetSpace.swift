//
//  IosWidgetSpace.swift
//  iosApp
//
//  Created by alobynya on 27.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetSpace: Space {
   
    private let root: SpaceView = {
        let container = SpaceView()

        container.translatesAutoresizingMaskIntoConstraints = false
        return container;
    }()
    
    func background(background: GraphicsColor) {
        root.backgroundColor = UIColor.clear
    }
    
    func height(height_ height: Int32) {
        root.widgetHeight = EntitySize.Const(value: height)
    }
    
    func width(width__ width: Int32) {
        root.widgetWidth = EntitySize.Const(value: width)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
  
    var value: Any { root }
    
    class SpaceView : UIView{
        
        var widgetWidth : EntitySize.Const = EntitySize.Const(value: 0) {
            didSet {
                setNeedsLayout()
            }
        }
        var widgetHeight : EntitySize.Const = EntitySize.Const(value: 0)  {
            didSet {
                setNeedsLayout()
            }
        }
        
        var contentSize : CGSize = CGSize(width: 80, height: 80)
        
        override var intrinsicContentSize: CGSize {
           return contentSize
        }
        
            override func sizeThatFits(_ size: CGSize) -> CGSize {
                return contentSize
          
            }
    }
}
