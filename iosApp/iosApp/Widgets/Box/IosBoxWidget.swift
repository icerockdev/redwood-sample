//
//  IosBoxWidget.swift
//  iosApp
//
//  Created by alobynya on 26.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetBox: Box {
    func background(background: GraphicsColor) {
        root.view.backgroundColor = UIColor(red: CGFloat(background.red)/256, green: CGFloat(background.green)/256, blue: CGFloat(background.blue)/256, alpha: CGFloat(background.alpha)/256)
    }
    
    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.isertFullView(value: view)
    }

    private let root: IosStackController = {
        let view = IosStackController()
        return view

    }()
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root.view }
}


extension GraphicsColor{
    func toColor() -> UIColor {
        return UIColor(red: CGFloat(self.red)/256, green: CGFloat(self.green)/256, blue: CGFloat(self.blue)/256, alpha: CGFloat(self.alpha)/256)
    }
}
