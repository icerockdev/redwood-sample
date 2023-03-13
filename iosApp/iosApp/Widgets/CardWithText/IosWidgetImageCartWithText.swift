//
//  ImageCardWithText.swift
//  iosApp
//
//  Created by alobynya on 22.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetImageCardWithText: ImageCardWithText {

   private let root : CardWithText =  {
        let text = CardWithText()
       text.clipsToBounds = true
       text.layer.cornerRadius = 8
        return text
    }()
    
    func height(height: EntitySize?) {
        root.widgetHeight = height ?? EntitySize.Wrap()
    }
    
    func width(width_ width: EntitySize?) {
        root.widgetWidth = width ?? EntitySize.Fill()
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        
    }
    
    func placeholder(placeholder: ImageResource?) {
        
    }
    
    func text(text: StringDesc) {
        root.setText(text: text.localized())
    }
    
    func textBackgroundColor(textBackgroundColor: GraphicsColor?) {
        
    }
    
    func textColor(textColor: GraphicsColor?) {
        
    }
    
    func url(url: String?) {
        root.setImage(url: url)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {root}
}

class CardWithText: UIView {
    
    lazy var contentView: UIImageView = {
        let contentView = UIImageView()
        contentView.translatesAutoresizingMaskIntoConstraints = false
        contentView.contentMode = .scaleAspectFill
        contentView.clipsToBounds = true
        return contentView
    }()
    
    lazy var headerTitle: UILabel = {
        let headerTitle = UILabel()
        headerTitle.font = UIFont.systemFont(ofSize: 22, weight: .medium)
        headerTitle.text = "Custom View"
        headerTitle.textColor = .white
        headerTitle.textAlignment = .center
        headerTitle.translatesAutoresizingMaskIntoConstraints = false
        return headerTitle
    }()
    
    lazy var headerView: UIView = {
        let headerView = UIView()
        headerView.backgroundColor = UIColor(red: 0/255, green: 0/255, blue: 0/255, alpha: 0.2)
        headerView.layer.shadowColor = UIColor.gray.cgColor
        headerView.layer.shadowOffset = CGSize(width: 0, height: 10)
        headerView.layer.shadowOpacity = 1
        headerView.layer.shadowRadius = 5
        headerView.addSubview(headerTitle)
        headerView.translatesAutoresizingMaskIntoConstraints = false
        return headerView
    }()
    
    func setText(text:String){
        headerTitle.text = text
    }
    
    func setImage(url: String?) {
        if(url != nil){
            DispatchQueue.global().async { [weak self] in
                if let data = try? Data(contentsOf: URL(string: url!)!) {
                    if let image = UIImage(data: data) {
                        DispatchQueue.main.async {
                            self?.contentView.image = image
                        }
                    }
                }
            }
        }
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    private func setupView() {
        backgroundColor = .white
        addSubview(contentView)
        addSubview(headerView)
        setupLayout()
    }
    
    private func setupLayout() {
        NSLayoutConstraint.activate([
            //pin headerTitle to headerView
            headerTitle.topAnchor.constraint(equalTo: headerView.topAnchor),
            headerTitle.bottomAnchor.constraint(equalTo: headerView.bottomAnchor),
            headerTitle.leadingAnchor.constraint(equalTo: headerView.leadingAnchor),
            headerTitle.trailingAnchor.constraint(equalTo: headerView.trailingAnchor),
            
            //pin headerView to top
            headerView.bottomAnchor.constraint(equalTo: bottomAnchor),
            headerView.leadingAnchor.constraint(equalTo: leadingAnchor),
            headerView.trailingAnchor.constraint(equalTo: trailingAnchor),
            headerView.heightAnchor.constraint(equalToConstant: 40),
            
            //layout contentView
            contentView.topAnchor.constraint(equalTo: topAnchor),
            contentView.bottomAnchor.constraint(equalTo: bottomAnchor),
            contentView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentView.trailingAnchor.constraint(equalTo: trailingAnchor)
        ])
    }
    
    //custom views should override this to return true if
    //they cannot layout correctly using autoresizing.
    //from apple docs https://developer.apple.com/documentation/uikit/uiview/1622549-requiresconstraintbasedlayout
    override class var requiresConstraintBasedLayout: Bool {
        return true
    }
    
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
        if( widgetHeight is EntitySize.Wrap){
            contentSize =  CGSize(width:  size.width, height: originalSize.height)
        }else {
            if(widgetHeight is EntitySize.Fill){
                contentSize = size
            }else{
                contentSize = CGSize(width: size.width, height:
                                        CGFloat((widgetHeight as! EntitySize.Const).value))
            }
        }
        return contentSize
        
    }
}
