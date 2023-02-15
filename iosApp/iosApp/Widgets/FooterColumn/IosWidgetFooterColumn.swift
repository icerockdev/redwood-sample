//
//  IosWidgetFooterColumn.swift
//  iosApp
//
//  Created by alobynya on 15.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//
import UIKit
import shared_ios


class IosWidgetFooterColumn: FooterColumn {
    var footer: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.addSubview(view)
    }

    private let root: DividerView = {
        let view = DividerView()
        view.backgroundColor = black60
        return view

    }()
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}

