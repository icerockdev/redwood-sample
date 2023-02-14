//
//  IosWidgetSwitch.swift
//  iosApp
//
//  Created by alobynya on 14.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetSwitch: Switch {

    private let root: UISwitch = {
        let view = UISwitch()
        view.thumbTintColor = primaryColor
        view.onTintColor = primaryColor
        return view

    }()
    
    func isActive(isActive: Bool) {
        root.setOn(isActive, animated: true)
    }
    
    func isEnabled(isEnabled: Bool) {
        root.isEnabled = isEnabled
    }
    
    func onChangeState(onChangeState: ((KotlinBoolean) -> Void)? = nil) {
        autoSaveChanged(root, onChangeState: onChangeState)
    }
    
    @IBAction func autoSaveChanged(_ sender: UISwitch,onChangeState: ((KotlinBoolean) -> Void)? = nil) {
        let bool = KotlinBoolean.init(bool: sender.isOn)
        onChangeState!(bool)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}

