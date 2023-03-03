//
//  IosWidgetFooterColumn.swift
//  iosApp
//
//  Created by alobynya on 15.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//
import UIKit
import shared_ios


class IosWidgetFooterColumn: HeaderFooterContent {
    var footer: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsertFooter)
    }

    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }
    
    var header: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsertHeader)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.addContenet(value: view)
    }
    func myInsertFooter(view: UIView,index: KotlinInt){
        root.addFooter(value: view)
    }
    func myInsertHeader(view: UIView,index: KotlinInt){
        root.addHeader(value: view)
    }

    private let root: FooterContainerController = {
        let view = FooterContainerController()
        return view

    }()
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root.view}
}

