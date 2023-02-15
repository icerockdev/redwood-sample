//
//  Onboarding.swift
//  iosApp
//
//  Created by alobynya on 15.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetOnboarding: Onboarding {

    private let root: OnboardingViewController = {
        let view = OnboardingViewController()
        return view

    }()
    
    func onFinishClick(onFinishClick: (() -> Void)? = nil) {
        root.setOnFinishClick(onFinishClick: onFinishClick)
    }
    
    var childs: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root.view, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        root.addPage(view: view,index: index)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root.view}
}

