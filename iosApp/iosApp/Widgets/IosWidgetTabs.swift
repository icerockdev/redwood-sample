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
  
    private let root: TabContainerView = {
        let control = TabContainerView()
        control.layoutMargins = UIEdgeInsets(top: 2, left: 12, bottom: 2, right: 12)
        control.isLayoutMarginsRelativeArrangement = true
        return control
    }()
 
    func onChange(onChange_ onChange: ((KotlinInt) -> Void)? = nil) {
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
    
    var value: Any {root}
    
    
    
}

class TabsControll: UIViewController{
    
    var onChange: ((KotlinInt) -> Void)? = nil
      
    let root: UISegmentedControl = {
        let control = UISegmentedControl(items: ["one", "two"])
        control.addTarget(self, action: #selector(segmentControl(_:)), for: .valueChanged)
        control.selectedSegmentIndex = 1
        return control
    }()
    
    
    let container: TabContainerView = {
        let control = TabContainerView()
        control.layoutMargins = UIEdgeInsets(top: 12, left: 12, bottom: 12, right: 12)
        control.isLayoutMarginsRelativeArrangement = true
         return control
    }()
    
    override func loadView() {
        self.view = container
        container.setNeedsLayout()
      
    }
 
    override func viewDidLoad() {
        container.insertArrangedSubview(root, at: 0)
        container.setNeedsLayout()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        let size = root.sizeThatFits(view.frame.size)
        root.frame = CGRect(x: 12, y: 12, width: view.frame.width-24, height: size.height)
        container.setNeedsLayout()
    }
    
    
    @objc func segmentControl(_ segmentedControl: UISegmentedControl) {
        onChange!(KotlinInt(integerLiteral: (segmentedControl.selectedSegmentIndex)))
     
    }
    
    func onChange(onChange: ((KotlinInt) -> Void)? = nil) {
        self.onChange = onChange
    }
    
    
}

class TabContainerView: UIStackView {
    var onChange: ((KotlinInt) -> Void)? = nil
    
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        let childSize = subviews.first?.sizeThatFits(CGSize(width: size.width, height: size.height))
        return CGSize(width: size.width, height: childSize?.height ?? 48)
    }
    
    override init(frame: CGRect) {
      super.init(frame: frame)
      setupView()
    }
    
    required init(coder aDecoder: NSCoder) {
      super.init(coder: aDecoder)
      setupView()
    }
    
    let root: UISegmentedControl = {
        let control = UISegmentedControl(items: ["one", "two"])
        control.addTarget(self, action: #selector(segmentControl(_:)), for: .valueChanged)
        control.selectedSegmentIndex = 1
        return control
    }()
    
    @objc func segmentControl(_ segmentedControl: UISegmentedControl) {
        onChange!(KotlinInt(integerLiteral: (segmentedControl.selectedSegmentIndex)))
     
    }
    
    private func setupView() {
        insertArrangedSubview(root, at: 0)
    }
}
