//
//  IosWidgetTabs.swift
//  iosApp
//
//  Created by alobynya on 16.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetTabs: Tabs {
  
    
    
    private let root: TabsControll = {
        let control = TabsControll()
  
        return control
    }()
 
    func onChange(onChange: ((KotlinInt) -> Void)? = nil) {
        root.onChange = onChange
    }
    
    
    func selectedTab(selectedTab: Int32) {
        root.root.selectedSegmentIndex = Int(selectedTab)
    }
    
    func texts(texts: [StringDesc]) {
        
        if(texts.isEmpty == false){
            root.root.removeAllSegments()
         for i in 0..<texts.count {
                root.root.insertSegment(withTitle: texts[i].localized(), at: i, animated: false)
           }
        }
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root.view}
    
    
    
}

class TabsControll: UIViewController{
    
    let root: UISegmentedControl = {
        let control = UISegmentedControl(items: ["one", "two"])
        control.addTarget(self, action: #selector(segmentControl(_:)), for: .valueChanged)
        control.selectedSegmentIndex = 1
        return control
    }()
    
    
    let container: UIStackView = {
        let control = UIStackView()
        control.layoutMargins = UIEdgeInsets(top: 12, left: 12, bottom: 12, right: 12)
        control.isLayoutMarginsRelativeArrangement = true
         return control
    }()
    
    override func loadView() {
        self.view = container
      
    }
 
    override func viewDidLoad() {
        container.insertArrangedSubview(root, at: 0)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
      //  view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
     //   let size = root.sizeThatFits(view.frame.size)
     //   root.frame = CGRect(x: 12, y: 12, width: view.frame.width-24, height: size.height)
    }
    
    
    @objc func segmentControl(_ segmentedControl: UISegmentedControl) {
       switch (segmentedControl.selectedSegmentIndex) {
          case 0:
             // First segment tapped
          break
          case 1:
             // Second segment tapped
          break
          default:
          break
       }
    }
    
    var onChange: ((KotlinInt) -> Void)? = nil
    
    func onChange(onChange: ((KotlinInt) -> Void)? = nil) {
        self.onChange = onChange
    }
    
    
}
