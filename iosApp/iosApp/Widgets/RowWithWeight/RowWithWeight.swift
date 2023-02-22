//
//  RowWithWeight.swift
//  iosApp
//
//  Created by alobynya on 20.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class RowWithWeightViewController: UIViewController {
    
    var container: FillUiView {
        let view = FillUiView()
        view.translatesAutoresizingMaskIntoConstraints = false
        return view
    }
    
    
    var childs:[UIView] = []
    var weights:[Float?] = []
    
    override func loadView() {
        self.view = container
      
    }
    
    func addPage(subView: UIView,index: KotlinInt, weight: Float?){
        childs.append(subView)
        weights.append(weight)
        view.addSubview(subView)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        
        var fullWeight : CGFloat = 0
        for i in 0..<weights.count{
            fullWeight += CGFloat(weights[i] ?? 0)
        }
        var left = CGFloat(0)
        for i in 0..<childs.count{
            let childWidth = view.frame.width * CGFloat(weights[i] ?? 0) / fullWeight
            let childSize = childs[i].sizeThatFits(CGSize(width: childWidth, height: view.frame.height))
            
            childs[i].frame = CGRect(x: left, y: 0, width: childWidth, height: childSize.height)
            left += childWidth
        }
    }
    
    class FillUiView: UIView{
    
        var contentSize : CGSize = CGSize(width: 48, height: 48)
       
        override var intrinsicContentSize: CGSize {
           return contentSize
        }
        
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            var  subviewsHeight = CGFloat(0)
            subviews.forEach{ view in
                let height =  view.sizeThatFits(size).height
                if(subviewsHeight < height) {
                    subviewsHeight = height
                }
            }
            contentSize =  CGSize(width:  size.width, height: subviewsHeight)
            return contentSize
            
        }
    }
    
}

