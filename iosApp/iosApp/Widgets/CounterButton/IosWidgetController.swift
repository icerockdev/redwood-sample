//
//  IosWidgetController.swift
//  iosApp
//
//  Created by alobynya on 10.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetController : UIViewController{
    
    
    private let text : UILabel =  {
        let text = UILabel()
        text.translatesAutoresizingMaskIntoConstraints = false
        return text
    }()
    
    private let incrimentView: UIButton = {
        let incrimentView = UIButton()
        incrimentView.setImage(UIImage(named: "like"), for: .normal)
        incrimentView.setImage(UIImage(named: "like"), for: .focused)
        incrimentView.translatesAutoresizingMaskIntoConstraints = false
        return incrimentView
    }()
    
    private let decrimentView: UIButton = {
        let decrimentView = UIButton()
        decrimentView.setImage(UIImage(named: "like"), for: .normal)
        decrimentView.setImage(UIImage(named: "like"), for: .focused)
      decrimentView.translatesAutoresizingMaskIntoConstraints = false
        return decrimentView
    }()
    
    
    
    private let container : CounterCardView = {
        let container = CounterCardView()
        container.backgroundColor = black70
        container.clipsToBounds = true
        container.layer.cornerRadius = 24
        container.translatesAutoresizingMaskIntoConstraints = true
        return container
    }()
    
    override func viewDidLoad() {
        container.addSubview(text)
        container.addSubview(incrimentView)
        container.addSubview(decrimentView)
            
    }
    
    var flag = true
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        decrimentView.frame = CGRect(x: 0, y: 0, width: view.frame.height , height:view.frame.height)
        
        decrimentView.frame = CGRect(x: view.frame.width-view.frame.height, y: 0, width: view.frame.height , height:view.frame.height)
    }
    
    func width(width: EntitySize) {
        container.widgetWidth = width
    }
    
    func setText(string: String) {
        text.text = string
    }
    
    func setOnInrimentClick(onClick: (() -> Void)? = nil) {
        let identifier = UIAction.Identifier("ButtonBinding.onPressed")
        let action = UIAction(
            identifier: identifier,
            handler: { _ in onClick?() }
        )

        incrimentView.removeAction(identifiedBy: identifier, for: .touchUpInside)
        incrimentView.addAction(action, for: .touchUpInside)
    }
    
    func setOnDecrementClick(onClick: (() -> Void)? = nil) {
        let identifier = UIAction.Identifier("ButtonBinding.onPressed")
        let action = UIAction(
            identifier: identifier,
            handler: { _ in onClick?() }
        )

        decrimentView.removeAction(identifiedBy: identifier, for: .touchUpInside)
        decrimentView.addAction(action, for: .touchUpInside)
    }
    
    class CounterCardView: UIView{
        var widgetWidth : EntitySize = EntitySize.Wrap() {
            didSet {
                setNeedsLayout()
            }
        }
        
        override func sizeThatFits(_ size: CGSize) -> CGSize {
             if(widgetWidth is EntitySize.Wrap){
                 let originalSize = super.sizeThatFits(size)
                 return CGSize(width: originalSize.width, height: 48)
             }
             if(widgetWidth is EntitySize.Const){
                 let originalSize = super.sizeThatFits(CGSize(width:    CGFloat((widgetWidth as! EntitySize.Const).value),  height: size.height))
                 return CGSize(width: CGFloat((widgetWidth as!  EntitySize.Const).value), height: 48)
             }
             let originalSize = super.sizeThatFits(size)
             return CGSize(width: size.width, height: 48)
       
        }
    }
    
}
