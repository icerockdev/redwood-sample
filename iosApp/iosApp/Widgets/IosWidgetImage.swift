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
 
    private let root: UIImageView = {
        let view = UIImageView()
        view.translatesAutoresizingMaskIntoConstraints = true
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        return view
    }()
    
    var _placeholder: UIImage? = nil
    var _width: CGFloat? = nil
    var _height: CGFloat? = nil
    
    func url(url: String?) {
        if(url != nil){
            root.load(url: URL(string:url!)!)
        }
    }
    
    func placeholder(placeholder: ImageResource?) {
        var image = placeholder?.toUIImage()
         _placeholder = image
        if(_width != nil && _height != nil){
            image = _placeholder?.resizeImageTo(size: CGSize(width: _width!, height: _height!))
        }
        if(image != nil){
            root.layer.cornerRadius = image!.size.width / 2;
            root.layer.masksToBounds = true
        }
        root.image = image
    }
    
    func width(width: KotlinInt?) {
        _width = CGFloat(width?.intValue ?? 10)
    }
    
    func height(height: KotlinInt?) {
        _height = CGFloat(height?.intValue ?? 10)
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
