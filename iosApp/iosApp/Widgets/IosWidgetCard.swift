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
    
    private let card: MyCardView = {
        let container = MyCardView()
        container.backgroundColor = .gray
          return container;
    }()
    
    private let root: MyCardView = {
        let container = MyCardView()
        container.translatesAutoresizingMaskIntoConstraints = true
        container.clipsToBounds = true
        container.backgroundColor = .gray
        container.layer.cornerRadius = 16
        return container;
    }()
    
   
    
    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        view.translatesAutoresizingMaskIntoConstraints = true
        root.insertArrangedSubview(view, at: 0)
        view.leadingAnchor.constraint(equalTo:
                root.leadingAnchor
              ).isActive = true
        view.topAnchor.constraint(equalTo:
                root.topAnchor).isActive = true
        view.leftAnchor.constraint(equalTo:
                root.leftAnchor).isActive = true
        view.widthAnchor.constraint(equalTo:
                root.widthAnchor).isActive = true
        root.heightAnchor.constraint(equalTo:
                view.heightAnchor).isActive = true
        view.needsUpdateConstraints()
        root.needsUpdateConstraints()
        view.updateConstraintsIfNeeded()
        root.updateConstraintsIfNeeded()
        view.layoutSubviews()
        root.layoutSubviews()
        view.setNeedsLayout()
        root.setNeedsLayout()
        view.sizeThatFits(CGSize(width: 150, height:150))
        root.sizeThatFits(CGSize(width: 150, height:150))
    }
    
    func background(background: ColorResource?) {
    }
    

    func cornerRadius(cornerRadius: KotlinInt?) {
   
    }

    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}

class MyCardView: UIStackView{
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        let childSize = subviews.first?.sizeThatFits(size)
        return CGSize(width: size.width, height: childSize?.height ?? 32)
    }
}
