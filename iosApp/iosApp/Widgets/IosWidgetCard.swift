//
//  IosWidgetCard.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetCard :  WidgetCard{
    
    
    private let root: MyCardView = {
        let container = MyCardView()
        container.translatesAutoresizingMaskIntoConstraints = true
        container.layer.cornerRadius = 16
       // container.backgroundColor =  UIColor(red:242.0/255.0, green:242.0/255.0, blue:242.0/255.0, alpha:1.0)
        container.backgroundColor = .white
        container.clipsToBounds = true
       return container;
    }()
    
    func onClick(onClick: (() -> Void)? = nil) {
        if(onClick != nil){
            root.setOnClickListener {
                onClick!()
            }
        }
        
    }
    
    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        view.translatesAutoresizingMaskIntoConstraints = true
        root.insertArrangedSubview(view, at: 0)
  
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}

class MyCardView: UIStackView{
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        let childSize = subviews.first?.sizeThatFits(CGSize(width: size.width, height: size.height))
        return CGSize(width: size.width, height: childSize?.height ?? 48)
    }
}

extension UIView {
    
    func setOnClickListener(action :@escaping () -> Void){
        let tapRecogniser = ClickListener(target: self, action: #selector(onViewClicked(sender:)))
        tapRecogniser.onClick = action
        self.addGestureRecognizer(tapRecogniser)
    }
     
    @objc func onViewClicked(sender: ClickListener) {
        if let onClick = sender.onClick {
            onClick()
        }
    }
     
}

class ClickListener: UITapGestureRecognizer {
     var onClick : (() -> Void)? = nil
    }
