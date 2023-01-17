//
//  IosWidgetText.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetText: WidgetText {
    private let root: UILabel = {
        let view = UILabel()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }()
    
    func isSingleLine(isSingleLine: Bool) {
        // TODO
    }
    
    func textStyle(textStyle: String?) {
        // TODO
    }

    func text(text: String) {
        root.text = text
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
}
