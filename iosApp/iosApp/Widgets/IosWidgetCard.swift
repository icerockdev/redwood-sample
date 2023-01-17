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
    
    private let card: UIStackView = {
        let container = UIStackView()
        container.backgroundColor = .red
        container.axis = .vertical
        container.alignment = .fill
        container.distribution = .fill
        container.layer.cornerRadius = 20
        container.translatesAutoresizingMaskIntoConstraints = false
        container.isLayoutMarginsRelativeArrangement = true
          return container;
    }()
    
    private let root: UIStackView = {
        let container = UIStackView()
        container.axis = .vertical
        container.alignment = .fill
        container.distribution = .fill
        container.translatesAutoresizingMaskIntoConstraints = false
        container.isLayoutMarginsRelativeArrangement = true
           return container;
    }()
    
   
    
    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        card.layoutMargins = UIEdgeInsets(top: 20, left: 20, bottom: 20, right: 20)
        view.layoutMargins = UIEdgeInsets(top: 20, left: 20, bottom: 20, right: 20)
        card.insertArrangedSubview(view, at: 0)
        root.insertArrangedSubview(card, at: 0)
        card.leadingAnchor.constraint(equalTo: root.leadingAnchor, constant: 15).isActive = true
        card.topAnchor.constraint(equalTo: root.topAnchor, constant: 15).isActive = true
        card.bottomAnchor.constraint(equalTo: root.bottomAnchor, constant: 15).isActive = true
    }
    
    func background(background: ColorResource?) {
       
    }
    

    func cornerRadius(cornerRadius: KotlinInt?) {
   
    }

    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
