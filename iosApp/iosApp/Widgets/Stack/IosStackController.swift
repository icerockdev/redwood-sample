//
//  IosStackController.swift
//  iosApp
//
//  Created by alobynya on 28.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosStackController: UIViewController {

    
    private var footer: UIView? = nil
    private var content: UIView? = nil
    
    private let container : MyStackView = {
        let container = MyStackView()
        container.translatesAutoresizingMaskIntoConstraints = false
        return container
    }()
    
    override func loadView() {
        self.view = container
    }
    
    override func viewDidLoad() {
    }
    
    func isertFullView(value: UIView){
        content = value
        container.addSubview(value)
    }
    
    func isertBottomView(value: UIView){
         footer = value
         container.addSubview(value)
    }
    

    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        let footerSize = footer?.sizeThatFits(view.frame.size)
        footer?.frame = CGRect(x: 0, y: view.frame.height - (footerSize?.height ?? 0), width: view.frame.width, height: (footerSize?.height ?? 0))
        content?.frame = view.frame
   }
    
}

