//
//  IosWidgetImage.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetImage: WidgetImage {
  
    
    
    private let root: FillImage = {
        let view = FillImage()
        view.translatesAutoresizingMaskIntoConstraints = true
        view.contentMode = .scaleAspectFit
        view.clipsToBounds = true
        return view
    }()
    
    var _placeholder: UIImage? = nil
    var _width: EntitySize? = nil
    var _height: EntitySize? = nil
    var _aspectRation: Float? = nil
    
    func url(url: String?) {
        if(url != nil){
            root.load(url: URL(string:url!)!)
        }
    }
    
    func placeholder(placeholder: ImageResource?) {
        var image = placeholder?.toUIImage()
         _placeholder = image
        let constWidth : Int32? = (_width as? EntitySize.Const)?.value
        let constHeight = (_height as? EntitySize.Const)?.value
        if(constWidth != nil && constHeight != nil){
            image = _placeholder?.resizeImageTo(size: CGSize(width: CGFloat(constWidth!), height: CGFloat(constHeight!)))
        }
        if(image != nil){
            root.layer.cornerRadius = image!.size.width / 2;
            root.layer.masksToBounds = true
        }
        root.image = image
    }
    
    func width(width_ width: EntitySize?) {
        _width = width
        root.widgetWidth = width ?? EntitySize.Wrap()
    }
    
    func height(height: EntitySize?) {
        _height = height
        root.widgetHeight = height ?? EntitySize.Wrap()
    }
    
    func aspectRatio(aspectRatio: KotlinFloat?) {
        root.aspectRatio = aspectRatio?.floatValue
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    func load(url: URL) {
        DispatchQueue.global().async { [weak self] in
            if let data = try? Data(contentsOf: url) {
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.root.image = image
                    }
                }
            }
        }
    }
    
    class FillImage : UIImageView{
        
        var widgetWidth : EntitySize = EntitySize.Wrap() {
            didSet {
                setNeedsLayout()
            }
        }
        
        var widgetHeight : EntitySize = EntitySize.Wrap() {
            didSet {
                setNeedsLayout()
            }
        }
        
        var aspectRatio : Float? = nil {
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
                if(aspectRatio == nil){
                    contentSize =  CGSize(width:  size.width, height: originalSize.height)
                }else{
                    contentSize =  CGSize(width:  size.width, height: size.width*CGFloat(aspectRatio!))
                }
                return contentSize
          
            }
    }



}

extension UIImageView {
    func load(url: URL) {
        DispatchQueue.global().async { [weak self] in
            if let data = try? Data(contentsOf: url) {
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.image = image
                    }
                }
            }
        }
    }
}


extension UIImage {
    
    func resizeImageTo(size: CGSize) -> UIImage? {
        
        UIGraphicsBeginImageContextWithOptions(size, false, 0.0)
        self.draw(in: CGRect(origin: CGPoint.zero, size: size))
        let resizedImage = UIGraphicsGetImageFromCurrentImageContext()!
        UIGraphicsEndImageContext()
        return resizedImage
    }
}
