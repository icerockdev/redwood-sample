//
//  FooterContainer.swift
//  iosApp
//
//  Created by alobynya on 26.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class FooterContainerController: UIViewController {

    
    private var footer: UIView? = nil
    private var header: UIView? = nil
    private var content: UIView? = nil
    
    private let container : UIView = {
        let container = UIView()
        container.translatesAutoresizingMaskIntoConstraints = false
        return container
    }()
    
    override func loadView() {
        self.view = container
       // self.view = MyBannersView()
       // view.layer.cornerRadius = 16
       // view.clipsToBounds = true
    }
    
    override func viewDidLoad() {
        // view.addSubview(pageControll)
        // view.addSubview(scrollView)
        // view.addSubview(closeButton)
        // view.addSubview(nextButton)
    }
    
    func addFooter(value: UIView){
        footer = value
        container.addSubview(value)
    }
    
    func addHeader(value: UIView){
        header = value
        container.addSubview(value)
    }
    
    func addContenet(value: UIView){
        content = value
        container.addSubview(value)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        let headerSize = header?.sizeThatFits(view.frame.size)
        let footerSize = footer?.sizeThatFits(view.frame.size)
        header?.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: headerSize?.height ?? 0)
        footer?.frame = CGRect(x: 0, y: view.frame.height - (footerSize?.height ?? 0), width: view.frame.width, height: (footerSize?.height ?? 0))
        content?.frame = CGRect(x: 0, y: headerSize?.height ?? 0, width: view.frame.width, height: view.frame.height - (footerSize?.height ?? 0))
   }
    
}

