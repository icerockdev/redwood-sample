//
//  IosWidgetFactory.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetFactory: WidgetFactory {
    
    func Button() -> Button {
        IosWidgetButton()
    }
    
    func Text() -> Text {
        IosWidgetText()
    }
}
