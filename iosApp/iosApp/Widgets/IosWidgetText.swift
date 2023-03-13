//
//  IosWidgetText.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetText: WidgetText {
   
    

    private let root: FillLabel = {
        let view = FillLabel()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.numberOfLines = 0
        return view
    }()
    
    var textFont : UIFont = .systemFont(ofSize: 12)
    var originalText : String = ""
    
    func isSingleLine(isSingleLine: Bool) {
        if(isSingleLine){
            root.numberOfLines = 0
        } else{
            root.numberOfLines = 0
        }
        root.setNeedsLayout()
    }
    
    func width(width: EntitySize) {
        root.widgetWidth = width
    }

    func text(text_ text: String) {
        originalText = text
        root.attributedText = text.colorSubstringsBetweenTags(start: "<b>", end: "</b>", font: textFont)
    }

    
    func textType(textType: EntityTextType?) {
        if(textType == EntityTextType.header){
            textFont = .boldSystemFont(ofSize: 25)
        }
        if(textType == EntityTextType.primary){
            textFont = .systemFont(ofSize: 14)
            root.textColor = black
        }
        if(textType == EntityTextType.secondary){
            textFont = .systemFont(ofSize: 12)
            root.textColor = black70
        }
        if(textType == EntityTextType.h2){
            textFont = .boldSystemFont(ofSize: 22)
        }
        if(textType == EntityTextType.primarybold){
            textFont = .boldSystemFont(ofSize: 20)
        }
        text(text_: originalText)
    }
    
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any { root }
    
    class FillLabel : UILabel{
        
        var widgetWidth : EntitySize = EntitySize.Wrap() {
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
                contentSize =  CGSize(width:  size.width, height: originalSize.height)
                return contentSize
          
            }
    }



}

extension String {
    func colorSubstringsBetweenTags(start: String, end: String, font: UIFont? = nil) -> NSAttributedString {
        var string = self
        let attribute = NSMutableAttributedString(string: string)
        let boldFont: UIFont = .boldSystemFont(ofSize: font?.pointSize ?? 15)
        attribute.addAttributes([.font: font], range: NSMakeRange(0, string.count))
        
        while let openedEm = string.range(of: start, range: string.startIndex..<string.endIndex) {
            let substringFrom = openedEm.upperBound
            guard let closedEm = string.range(of: end, range: openedEm.upperBound..<string.endIndex) else { return attribute }
            let substringTo = closedEm.lowerBound
            let nsrange = NSRange(substringFrom..<substringTo, in: string)
        
            if let font = font { attribute.addAttributes([.font: boldFont], range: nsrange) }
            attribute.mutableString.replaceCharacters(in: NSRange(closedEm, in: string), with: "")
            attribute.mutableString.replaceCharacters(in: NSRange(openedEm, in: string), with: "")
            string = attribute.mutableString as String
        }
        
        return attribute
    }
}
