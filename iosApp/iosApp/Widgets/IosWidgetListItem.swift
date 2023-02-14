//
//  IosWidgetListItem.swift
//  iosApp
//
//  Created by alobynya on 14.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetListItem: ListItem {
    
    private var image: UIImage? = nil
    private var title: String? = nil
    private var subtitle: String? = nil
    private var tintColor: GraphicsColor? = nil

    private let root: FillUITableViewCell = {
        let view = FillUITableViewCell()
        return view

    }()
    
    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.accessoryView = view
    }
    
    func height(height: KotlinInt?) {
        // todo
    }
    
    func updateContent(){
        var content =  root.defaultContentConfiguration()
        // Configure content.
        content.image = image
        if(tintColor == nil){
            content.text = title
            content.secondaryText = subtitle
        } else{
            let quote = title ?? ""
            let attributes = [NSAttributedString.Key.foregroundColor: errorColor]
            let attributedQuote = NSAttributedString(string: quote, attributes: attributes)
            
            content.attributedText = attributedQuote
        }
         
        // Customize appearance.
        root.contentConfiguration = content
    }
    
    func icon(icon: ImageResource?) {
        image = icon?.toUIImage()?.resizeImageTo(size: CGSize(width: 24, height: 24))
        updateContent()
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        if(onClick != nil){
            root.setOnClickListener {
                onClick!()
            }
        }
    }
    
    func subtitle(subtitle: StringDesc?) {
        self.subtitle = subtitle?.localized()
        updateContent()
    }
    
    func tintColor(tintColor: GraphicsColor?) {
        self.tintColor = tintColor
        updateContent()
    }
    
    func title(title: StringDesc) {
        self.title = title.localized()
        updateContent()
   }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
    
    class FillUITableViewCell : UITableViewCell{
        
        var contentSize : CGSize = CGSize(width: 48, height: 48)
        
        override var intrinsicContentSize: CGSize {
           return contentSize
        }
        
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let originalSize = super.sizeThatFits(
                CGSize(width: size.width, height: size.height))
            contentSize =  CGSize(width:  size.width, height: originalSize.height)
            return contentSize
      
        }
    }
}
