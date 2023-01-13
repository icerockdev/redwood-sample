//
//  IosStack.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosStack :  WidgetStack {
    private let root: UIView = {
        let view = UIView(frame: .zero)
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()

    var child1: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildren(parent: root)
    }
    
    var child2: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildren(parent: root)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
