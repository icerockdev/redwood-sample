//
//  IosWidgetButton.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetButton: WidgetButton {
  
    

    private let root: FillButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 12, leading: 20, bottom: 12, trailing: 20)
        let view = FillButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.clipsToBounds = true
        view.titleLabel?.numberOfLines = 0
        return view

    }()
    
    var _buttonType: EntityButtonType? = nil
    
    func buttonType(buttonType: EntityButtonType) {
        _buttonType = buttonType
        if(buttonType == EntityButtonType.primary){
            root.backgroundColor = primaryColor
            root.layer.borderColor = primaryColor.cgColor
            root.layer.cornerRadius = 10
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.isHighlighted = false
            root.setTitleColor(.white, for: UIControl.State.normal)
            root.setTitleColor(.white, for: UIControl.State.disabled)
            root.tintColor = .white
           }
        if(buttonType == EntityButtonType.secondary){
            root.setTitleColor(primaryColor, for: UIControl.State.normal)
            root.setTitleColor(black60, for: UIControl.State.disabled)
            root.backgroundColor = UIColor.clear
            root.layer.borderColor = primaryColor.cgColor
            root.layer.borderWidth = 2
            root.layer.cornerRadius = 10
            root.contentHorizontalAlignment = .center
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.updateConfiguration()
            root.tintColor = primaryColor
        }
        if(buttonType == EntityButtonType.text){
            root.setTitleColor(primaryColor, for: UIControl.State.normal)
            root.setTitleColor(primaryColor, for: UIControl.State.highlighted)
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.layer.cornerRadius = 10
            root.backgroundColor = UIColor.clear
            root.tintColor = primaryColor
        }
        if(buttonType == EntityButtonType.tonal){
            root.backgroundColor = primary88
            root.layer.cornerRadius = 10
            root.configuration?.baseBackgroundColor = UIColor.clear
            root.setTitleColor(black, for: UIControl.State.normal)
            root.setTitleColor(black, for: UIControl.State.disabled)
            root.tintColor = black
        }
    }
    
    func width(width: EntitySize) {
        root.widgetWidth = width
    }
    
    func icon(icon: ImageResource?) {
        let image = icon?.toUIImage()?.withTintColor(getTintColor())
        root.setImage(image, for: .normal)
        root.setImage(image?.withTintColor(black70), for: .disabled)
        root.setImage(image, for: .focused)
    }
    
    func enabled(enabled: Bool) {
        root.isEnabled = enabled
        if(_buttonType == EntityButtonType.primary){
            if(enabled){
                root.backgroundColor = primaryColor
            }else{
                root.backgroundColor = black60
            }
        }
        if(_buttonType == EntityButtonType.tonal){
            if(enabled){
                root.backgroundColor = primary12
            }else{
                root.backgroundColor = black60
            }
        }
        if(_buttonType == EntityButtonType.secondary){
            if(enabled){
                root.layer.borderColor = primaryColor.cgColor
            }else{
                root.layer.borderColor = black60.cgColor
            }
        }
    }
    
    func text(text: StringDesc) {
        root.setTitle(text.localized(), for: .normal)
        root.setTitle(text.localized(), for: .disabled)
    }
    
    func getTintColor() -> UIColor{
        if(_buttonType == EntityButtonType.primary){
            return .white
           }
        if(_buttonType == EntityButtonType.secondary){
            return primaryColor
        }
        if(_buttonType == EntityButtonType.text){
            return primaryColor
        }
            return black

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

class FillButton : UIButton{
    
    var widgetWidth : EntitySize = EntitySize.Wrap() {
        didSet {
            setNeedsLayout()
        }
    }
    
    var contentSize : CGSize = CGSize(width: 48, height: 48)
    
    override var intrinsicContentSize: CGSize {
       return contentSize
    }
    
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            if(widgetWidth is EntitySize.Wrap){
                let originalSize = super.sizeThatFits(size)
                contentSize = CGSize(width: originalSize.width, height: originalSize.height)
                return contentSize
            }
            if(widgetWidth is EntitySize.Const){
                let originalSize = super.sizeThatFits(CGSize(width: CGFloat((widgetWidth as! EntitySize.Const).value), height: size.height))
                contentSize = CGSize(width: CGFloat((widgetWidth as! EntitySize.Const).value), height: originalSize.height)
                return contentSize
            }
            let originalSize = super.sizeThatFits(
                CGSize(width: size.width, height: size.height))
            contentSize =  CGSize(width:  size.width, height: originalSize.height)
            return contentSize
      
        }
}

