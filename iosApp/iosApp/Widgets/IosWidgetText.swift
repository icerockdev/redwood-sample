//
//  IosWidgetText.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetText: WidgetText {
   
    

    private let root: FillLabel = {
        let view = FillLabel()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.numberOfLines = 2
        return view
    }()
    
    func isSingleLine(isSingleLine: Bool) {
        if(isSingleLine){
            root.numberOfLines = 0
        } else{
            root.numberOfLines = 0
        }
        root.setNeedsLayout()
    }
    
    func width(width: EntitySize) {
        root.widgetWidth = width
    }

    func text(text_ text: String) {
        root.text = text
    }
    
    func textType(textType: EntityTextType?) {
        if(textType == EntityTextType.header){
            root.font = .boldSystemFont(ofSize: 25)
        }
        if(textType == EntityTextType.primary){
            root.font = .systemFont(ofSize: 12)
        }
        if(textType == EntityTextType.bold){
            root.font = .boldSystemFont(ofSize: 22)
        }
    }
    
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class FillLabel : UILabel{
        
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
                if(widgetWidth is EntitySize.Wrap){
                    let originalSize = super.sizeThatFits(size)
                    contentSize = CGSize(width: originalSize.width, height: originalSize.height)
                    return contentSize
                }
                if(widgetWidth is EntitySize.Const){
                    let originalSize = super.sizeThatFits(CGSize(width: CGFloat((widgetWidth as! EntitySize.Const).value), height: size.height))
                    contentSize = CGSize(width: CGFloat((widgetWidth as! EntitySize.Const).value), height: originalSize.height)
                    return contentSize
                }
                let originalSize = super.sizeThatFits(
                    CGSize(width: size.width, height: size.height))
                contentSize =  CGSize(width:  size.width, height: originalSize.height)
                return contentSize
          
            }
    }



}
