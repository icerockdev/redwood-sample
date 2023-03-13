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
        return view
    }
    
    
    var childs:[Redwood_widgetWidget] = []
    
    override func loadView() {
        self.view = container
      
    }
    
    func addPage(subView: Redwood_widgetWidget,index: KotlinInt){
        childs.append( subView)
        view.addSubview(subView.value as! UIView)
    }
    
    var widgets: [Redwood_widgetWidget] = []
    func setWidgets(widgets: [Redwood_widgetWidget]){
        self.widgets = widgets
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        var fullWeight : Float = 0
        for i in 0..<childs.count{
            fullWeight += childs[i].layoutModifiers.getWeight() ?? 0
        }
        var fullConstSize : CGFloat = 0
        var left = CGFloat(0)
        for i in 0..<childs.count{
            if(childs[i].layoutModifiers.getWeight() == nil){
                let view = childs[i].value as! UIView
                let padding = childs[i].layoutModifiers.getPadding()
                fullConstSize += CGFloat(view.sizeThatFits(view.frame.size).width)
                fullConstSize += CGFloat(padding?.start ?? 0)
                fullConstSize += CGFloat(padding?.end ?? 0)
            }
        }
        let fullWeightSize = view.frame.size.width - fullConstSize
        left = CGFloat(0)
        for i in 0..<childs.count{
            let weight = childs[i].layoutModifiers.getWeight()
            let view = childs[i].value as! UIView
            let padding = childs[i].layoutModifiers.getPadding()
            if(weight != nil){
                let childWidth = fullWeightSize * CGFloat((weight ?? 0) / fullWeight)
                let childSize = view.sizeThatFits(CGSize(width: childWidth, height: view.frame.height))
                view.frame = CGRect(x: left+CGFloat(padding?.start ?? 0), y: CGFloat(padding?.top ?? 0), width: childWidth - CGFloat(padding?.start ?? 0) - CGFloat(padding?.end ?? 0), height: childSize.height)
                left += childWidth
            }else{
                let childSize = view.sizeThatFits(view.frame.size)
                view.frame = CGRect(x: left+CGFloat(padding?.start ?? 0), y: +CGFloat(padding?.top ?? 0), width: childSize.width, height: childSize.height)
                left += childSize.width + CGFloat(padding?.start ?? 0) + CGFloat(padding?.end ?? 0)
            }
        }
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

class RowItem{
    let child: UIView
    let weight: Float?
    let padding: Redwood_layout_layoutmodifiersPadding?
    let layoutModifier: Redwood_runtimeLayoutModifier

    init(
        child: UIView,
        weight: Float?,
        padding: Redwood_layout_layoutmodifiersPadding?,
        layoutModifier: Redwood_runtimeLayoutModifier
    ) {
        self.child = child
        self.weight = layoutModifier.getWeight()
        self.padding = padding
        self.layoutModifier = layoutModifier
    }
    
}

extension Redwood_runtimeLayoutModifier{
    func getWeight() -> Float?{
        var result: Float? = nil
        self.forEach { element in
            if(element is SharedWeight){
                result = (element as! SharedWeight).weight
            }
        }
        return result
    }
    
    func getPadding() -> Redwood_layout_apiPadding?{
        var result: Redwood_layout_apiPadding? = nil
        self.forEach { element in
            if(element is Redwood_layout_layoutmodifiersPadding){
                result = (element as! Redwood_layout_layoutmodifiersPadding).padding
            }
        }
        return result
    }
}


class RowWithWeightViewContainer: FillUiView {
    
    var childs:[Redwood_widgetWidget] = []

    
    func addPage(subView: Redwood_widgetWidget,index: KotlinInt){
        childs.append( subView)
        self.addSubview(subView.value as! UIView)
    }
    
    var widgets: [Redwood_widgetWidget] = []
    func setWidgets(widgets: [Redwood_widgetWidget]){
        self.widgets = widgets
    }

    /*
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        
        var fullWeight : Float = 0
        for i in 0..<childs.count{
            fullWeight += childs[i].layoutModifiers.getWeight() ?? 0
        }
        var fullConstSize : CGFloat = 0
        var left = CGFloat(0)
        for i in 0..<childs.count{
            if(childs[i].layoutModifiers.getWeight() == nil){
                let view = childs[i].value as! UIView
                let padding = childs[i].layoutModifiers.getPadding()
                fullConstSize += CGFloat(view.sizeThatFits(view.frame.size).width)
                fullConstSize += CGFloat(padding?.start ?? 0)
                fullConstSize += CGFloat(padding?.end ?? 0)
            }
        }
        let fullWeightSize = view.frame.size.width - fullConstSize
        left = CGFloat(0)
        for i in 0..<childs.count{
            let weight = childs[i].layoutModifiers.getWeight()
            let view = childs[i].value as! UIView
            let padding = childs[i].layoutModifiers.getPadding()
            if(weight != nil){
                let childWidth = fullWeightSize * CGFloat((weight ?? 0) / fullWeight)
                let childSize = view.sizeThatFits(CGSize(width: childWidth, height: view.frame.height))
                view.frame = CGRect(x: left+CGFloat(padding?.start ?? 0), y: CGFloat(padding?.top ?? 0), width: childWidth - CGFloat(padding?.start ?? 0) - CGFloat(padding?.end ?? 0), height: childSize.height)
                left += childWidth
            }else{
                let childSize = view.sizeThatFits(view.frame.size)
                view.frame = CGRect(x: left+CGFloat(padding?.start ?? 0), y: +CGFloat(padding?.top ?? 0), width: childSize.width, height: childSize.height)
                left += childSize.width + CGFloat(padding?.start ?? 0) + CGFloat(padding?.end ?? 0)
            }
        }
    }
     */
    
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

