//
//  IosWidgetChip.swift
//  iosApp
//
//  Created by alobynya on 16.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetChip: Chip {
  
    private let root: FillButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 12, leading: 20, bottom: 12, trailing: 20)
        let view = FillButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.layer.cornerRadius = 10
        view.clipsToBounds = true
        view.titleLabel?.numberOfLines = 0
        view.widgetWidth = EntitySize.Wrap()
        view.setTitleColor(.black, for: .normal)
        return view
    }()
    
    func backgroundColor(backgroundColor: GraphicsColor?) {
        root.backgroundColor = backgroundColor?.toColor()
        root.configuration?.baseBackgroundColor = UIColor.clear
        root.isHighlighted = false
        root.tintColor = .clear
        if(backgroundColor == nil){
            root.titleLabel?.textColor = .black
        }else{
            root.titleLabel?.textColor = .white
        }
        root.titleLabel?.setNeedsLayout()
    }
    
    func icon(icon: ImageResource?) {
        let image = icon?.toUIImage()
        root.setImage(image, for: .normal)
        root.setImage(image?.withTintColor(black70), for: .disabled)
        root.setImage(image, for: .focused)
    }
    
    func text(text: StringDesc) {
        root.setTitle(text.localized(), for: .normal)
        root.setTitle(text.localized(), for: .disabled)
    }
    
    func textColor(textColor: GraphicsColor?) {
     //   root.setTitleColor(textColor?.toColor(), for: .normal)
    }
    
    func border(border: KotlinInt?) {
        root.layer.borderColor = black.cgColor
        root.layer.borderWidth = CGFloat(border?.intValue ?? 0)
  
       
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        let identifier = UIAction.Identifier("ButtonBinding.onPressed")
        let action = UIAction(
            identifier: identifier,
            handler: { _ in onClick?() }
        )

        root.removeAction(identifiedBy: identifier, for: .touchUpInside)
        root.addAction(action, for: .touchUpInside)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}
