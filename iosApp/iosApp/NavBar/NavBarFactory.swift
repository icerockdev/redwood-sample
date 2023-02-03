//
//  NavBarFactory.swift
//  iosApp
//
//  Created by alobynya on 02.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class NavBarFavtory : IosFlatNavigationFactory{
    func render(navControler: UINavigationController, data: Any?) {
        navControler.navigationBar.backgroundColor = UIColor.white
        let args : SharedToolabrArgs? = data as? SharedToolabrArgs
        if(args == nil || args is SharedToolabrArgs.NoToolbar){
            navControler.setToolbarHidden(true, animated: false)
            return
        }
        if(args is SharedToolabrArgs.Simple){
            let simpleArgs = args as! SharedToolabrArgs.Simple
            navControler.setToolbarHidden(false, animated: false)
            navControler.navigationBar.topItem?.title = simpleArgs.title.localized()
            var buttons:[UIBarButtonItem] = simpleArgs.actoins.map({action in
                let button = UIBarButtonItem(image: UIImage(named: action.icon.assetImageName))
                button.target = button
                let uiAction = UIAction(handler: { uiAction in
                    action.onCLick()
                })
                let uiImage = UIImage(named: action.icon.assetImageName)
                button.primaryAction = uiAction
                button.image = uiImage
                if(action.badge != nil){
                    let filterBtn = UIButton.init(frame: CGRect.init(x: 0, y: 0, width: 30, height: 30))
                    filterBtn.setImage(uiImage?.withTintColor(.systemBlue), for: .normal)
                    filterBtn.addAction(uiAction, for: .touchUpInside)

                    let lblBadge = UILabel.init(frame: CGRect.init(x: 20, y: 0, width: 15, height: 15))
                    lblBadge.backgroundColor = .red
                    lblBadge.clipsToBounds = true
                    lblBadge.layer.cornerRadius = 7
                    lblBadge.text = action.badge?.localized()
                    lblBadge.textColor = UIColor.white
                    lblBadge.font = .systemFont(ofSize: 10)
                    lblBadge.textAlignment = .center

                    filterBtn.addSubview(lblBadge)
                    button.customView = filterBtn
                }
                return button
            })
            
            navControler.navigationBar.topItem?.setRightBarButtonItems(buttons.reversed(), animated: false)
        }
    }
    
    @objc func logoutUser(action: SharedToolbarAction){
         print("clicked")
    }

}

