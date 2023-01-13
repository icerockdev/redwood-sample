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
    
    private let root: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    func background(background: ColorResource?) {
        root.backgroundColor = background?.getColor(
            userInterfaceStyle: .uiuserinterfacestylelight
        ).toUIColor()
    }

    func cornerRadius(cornerRadius: KotlinInt?) {
        root.layer.cornerRadius = CGFloat(cornerRadius?.floatValue ?? 0)
    }

    var child: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildren(parent: root)
    }

    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
