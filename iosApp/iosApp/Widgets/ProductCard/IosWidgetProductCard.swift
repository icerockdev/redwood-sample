//
//  IosWidgetProductCard.swift
//  iosApp
//
//  Created by alobynya on 08.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetProductCard:  ProductCard{
    
    private let root: ProductCardController = {
        let container = ProductCardController()
       return container;
    }()
    
    func badge(badge: StringDesc?) {
        root.setBadge(value: badge?.localized())
    }
    
    func footer(footer: StringDesc?) {
        root.setFooter(value: footer?.localized())
    }
    
    func image(image: String) {
        root.setImage(url: image)
    }
    
    func isLiked(isLiked: Bool) {
        root.setIsLeked(value: isLiked)
    }
    
    func oldPrice(oldPrice: StringDesc?) {
        root.setOldCost(value: oldPrice?.localized())
    }
    
    func onLikeClick(onLikeClick: (() -> Void)? = nil) {
        root.onLikeClick(onClick: onLikeClick)
    }
    
    func price(price: StringDesc) {
        root.setCost(value: price.localized())
    }
    
    func subtitle(subtitle: StringDesc?) {
        root.setSubtitle(value: subtitle?.localized())
    }
    
    func title(title: StringDesc) {
        root.setTitle(value: title.localized())
    }
    
    var action: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.addAction(view: view)
    }
   
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
}

